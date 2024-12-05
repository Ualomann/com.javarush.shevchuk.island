package Entity;

import Entity.Location.Cell;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Animal extends LivingOrganism{
    ReentrantLock lock = new ReentrantLock();

    private int maxSpeed;
    private final double maxSatiety;

    public void setActualSatiety(double actualSatiety) {
        this.actualSatiety = actualSatiety;
    }

    public double getActualSatiety() {
        return actualSatiety;
    }

    private double actualSatiety;

    private boolean isEat;

    private boolean isMoved = false;

    private Cell currentCell;

    public Cell getCurrentCell() {
        return currentCell;
    }
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }


    public Animal(double weight, int maxSpeed, double maxSatiety, int maxCountOfCell, String name,String EMOJI) {
        super(weight, maxCountOfCell, name, EMOJI);
        this.maxSpeed = maxSpeed;
        this.maxSatiety = maxSatiety;
        actualSatiety = maxSatiety /* - ((maxSatiety * 20) / 100) */;
    }
    public boolean isEat() {
        return isEat;
    }

    public boolean isMoved(){
        return isMoved;
    }


    public void setMoved(boolean moved){
        isMoved = moved;
    }
    public void setEat(boolean eat) {
        isEat = eat;
    }

    public double getMaxSatiety(){
        return maxSatiety;
    }
    public abstract HashMap<String, Integer> ChanceToEat();
    public void eat(LivingOrganism prey) {
        lock.lock();
        try{
            this.isEat = false;
            if (prey == null || prey.getIsDead() || this.actualSatiety == this.getMaxSatiety() || this.isEat
            || !this.ChanceToEat().containsKey(prey.getName())) {
                return;
            }
                int random = ThreadLocalRandom.current().nextInt(0, 100);
            int animalChanceToEat;
                try{
                    animalChanceToEat = this.ChanceToEat().get(prey.getName());
                    }
                catch (NullPointerException e){
                    return;
                }
                if (random <= animalChanceToEat) {
                    this.actualSatiety += (prey.getWeight() );
                    if (this.actualSatiety > maxSatiety) {
                        this.actualSatiety = maxSatiety;
                    }
                } else {
                    return;
                }

            this.isEat = true;
            die(prey);}
        finally {
            lock.unlock();
        }
    }


    public boolean canReproduceInCell(Cell cell) {
        lock.lock();
        long currentPopulation = 0;
        try {
            if (cell == null || cell.getCell() == null) {
                return false;
            }

            currentPopulation = cell.getCell().stream()
                    .filter(organism -> this.getClass().isAssignableFrom(organism.getClass())) // Считаем только данный вид
                    .count();
        }finally {
            lock.unlock();
        }
        return currentPopulation < this.getMaxCountOfCell();
    }

    public LivingOrganism reproduce(LivingOrganism partner) {
        lock.lock(); // Блокируем для синхронизации
        try {
            // Проверяем, что организмы одного вида, оба живы, и лимит не превышен
            if (this.getClass().equals(partner.getClass())
                    && !this.getIsDead() && !partner.getIsDead()
                    && canReproduceInCell(getCurrentCell())) {
                try {
                    if(partner instanceof Animal) {
                        Animal privedenie = (Animal) partner;

                        Animal children = privedenie.getClass().getConstructor().newInstance();
                        currentCell.addReproduce(children);
                        // Создаём потомка через рефлексию
                        return children;
                    }
                } catch (InstantiationException e) {
                    throw new RuntimeException("Ошибка при создании объекта потомка", e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Нет доступа к конструктору потомка", e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Ошибка при вызове конструктора потомка", e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Конструктор потомка не найден", e);
                }
            }
        } finally {
            lock.unlock(); // Освобождаем блокировку
        }
        return this; // Если размножение невозможно, возвращаем null
    }

    // Проверяем, можно ли размножаться


    private int[] chooseDirection(Cell currentCell, Cell[][] island) {
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}  // Вверх, вниз, влево, вправо
        };

        // Случайный выбор направления
        int directionIndex = ThreadLocalRandom.current().nextInt(directions.length);

        // Выбор расстояния хода от 1 до максимальной скорости включительно
        int step = ThreadLocalRandom.current().nextInt(1, this.maxSpeed + 1);

        // Возвращаем направление с учётом шага
        return new int[]{directions[directionIndex][0] * step, directions[directionIndex][1] * step};
    }

    public void move(Cell currentCell, Cell[][] island) {
        if(this.isMoved()){
            return;
        }
        if(this.getIsDead()){
            return;
        }
        currentCell.getLock().lock(); // Блокируем текущую клетку
        try {
            // Получаем текущие координаты клетки
            int currentX = currentCell.getX();
            int currentY = currentCell.getY();

            // Выбираем направление движения
            int[] direction = chooseDirection(currentCell, island);
            int newX = currentX + direction[0];
            int newY = currentY + direction[1];

            // Проверяем границы острова
            if (newX >= 0 && newY >= 0 && newX < island.length && newY < island[0].length) {
                Cell newCell = island[newX][newY];
                newCell.getLock().lock(); // Блокируем новую клетку
                try {
                    // Проверяем, можно ли переместиться
                    if (newCell.canMoveOrganism(this)) {
                        currentCell.removeOrganism(this); // Убираем из текущей клетки
                        newCell.getCellMoved().add(this); // Добавляем в новую клетку
                        this.setMoved(true);
//                        this.setCurrentCell(newCell);
//                        System.out.println(this + " переместился на (" + newX + "," + newY + ").");
                    } else {
//                        System.out.println(this + " не может переместиться в клетку (" + newX + "," + newY + ").");
                    }
                } finally {
                    newCell.getLock().unlock();
                }
            } else {
//                System.out.println(this + " не может выйти за пределы острова.");
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    public void worker() {
        actualSatiety = actualSatiety - ((maxSatiety * 5) / 100);
        if(actualSatiety<=0){
            die(this);
//            System.out.println(this.getClass().getSimpleName() + "Умер от голода");
        }
    }



}
