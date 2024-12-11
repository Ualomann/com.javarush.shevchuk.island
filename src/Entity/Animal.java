package Entity;

import Entity.Location.Cell;
import Entity.Location.Island;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends LivingOrganism{

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
    private boolean isReproduce = false;

    private Cell currentCell;

    public Cell getCurrentCell() {
        return currentCell;
    }
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public boolean getIsReproduce(){
        return isReproduce;
    }


    public Animal(double weight, int maxSpeed, double maxSatiety, int maxCountOfCell, String name,String EMOJI) {
        super(weight, maxCountOfCell, name, EMOJI);
        this.maxSpeed = maxSpeed;
        this.maxSatiety = maxSatiety;
        actualSatiety = maxSatiety  - ((maxSatiety * 20) / 100);
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
    public abstract HashMap<String, Integer> chanceToEat();
    public void eat(LivingOrganism prey) {
            this.isEat = false;
            if (prey == null || prey.getIsDead() || this.actualSatiety == this.getMaxSatiety() || this.isEat
            || !this.chanceToEat().containsKey(prey.getName())) {
                return;
            }
                int random = ThreadLocalRandom.current().nextInt(0, 101);
            int animalChanceToEat;
                try{
                    animalChanceToEat = this.chanceToEat().get(prey.getName());
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


    public void eatOnCell(Cell cell){
        List<LivingOrganism> temporarily = cell.getCell();
        if(!this.getIsDead() && !this.isEat){
            for (LivingOrganism organism : cell.getCell()) {
                List<LivingOrganism> targetEatAnimal = new ArrayList<>();
                if(!organism.getIsDead() && this.chanceToEat().containsKey(organism.getName())){
                    targetEatAnimal.add(organism);
                    LivingOrganism first = targetEatAnimal.get(0);
                    this.eat(first);
                }
            }
        }
        List <LivingOrganism> devouredList = new ArrayList<>();
        for (int i = 0; i < cell.getCell().size(); i++) {
            if(cell.getCell().get(i).getIsDead()){
                devouredList.add(cell.getCell().get(i));
            }
        }
        temporarily.removeAll(devouredList);
        cell.setCell(temporarily);
    }




    public Animal reproduce(Animal partner) {
            if (this.getClass().equals(partner.getClass())
                    && !this.getIsDead() && !partner.getIsDead()
                    ){
                try {   this.isReproduce = true;
                        Animal children = getClass().getConstructor().newInstance();
                        return children;

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
        return this; // Если размножение невозможно, возвращаем null
    }

    public void reproduceOnCell(Cell cell){
        List<LivingOrganism> temporarily = cell.getCell();
        List<Animal> childList = new ArrayList<>();
        if(!this.getIsDead() && !this.getIsReproduce()){
            long countAnimalOnCell = cell.getAnimalCountOnCell(this);
            long maxCountInCell = this.getMaxCountOfCell();

            for (Animal animal : cell.getAnimalOnCell()) {
                if(countAnimalOnCell >= 2 && countAnimalOnCell < maxCountInCell &&this != animal){
                    Animal child = this.reproduce(animal);
                        if(this != child){
                            childList.add(child);
                        }
                }
            }
            temporarily.addAll(childList);
            cell.setCell(temporarily);

        }
    }


    public boolean canMoveOrganism(Cell cell){
        long countAnimalOnCell = cell.getAnimalCountOnCell(this);
        long maxCountInCell = this.getMaxCountOfCell();
        if (countAnimalOnCell < maxCountInCell && !this.getIsDead() && !this.isMoved()){
            return true;
        }
        return false;
    }

    private int[] chooseDirection(Cell currentCell, Cell[][] island) {
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}  // Вверх, вниз, влево, вправо
        };

        // Случайный выбор направления
        int directionIndex = ThreadLocalRandom.current().nextInt(directions.length);

        // Выбор расстояния хода от 1 до максимальной скорости включительно
        int step = this.maxSpeed > 1
                ? ThreadLocalRandom.current().nextInt(1, this.maxSpeed + 1)  // Шаг от 1 до maxSpeed
                : 1;  // Если maxSpeed == 1, шаг всегда 1

        // Возвращаем направление с учётом шага
        return new int[]{directions[directionIndex][0] * step, directions[directionIndex][1] * step};
    }

    public void move(Cell currentCell, Cell[][] island) {
        // Если животное уже двигалось или мёртвое, выход из метода
        if (this.isMoved() || this.getIsDead()) {
            return;
        }

        // Блокируем текущую клетку
        currentCell.getLock().lock();
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

                // Блокируем новую клетку
                newCell.getLock().lock();
                try {
                    // Проверяем, можно ли переместиться в новую клетку
                    if (this.canMoveOrganism(newCell)) {
                        // Перемещаем животное
                        currentCell.removeOrganism(this); // Убираем из текущей клетки
                        newCell.getCell().add(this); // Добавляем в новую клетку
                        this.setMoved(true);
                        this.setCurrentCell(newCell);

//                         Логирование перемещения (если нужно)
//                         System.out.println(this + " переместился на (" + newX + "," + newY + ").");
                    } else {
                        // Логирование невозможности перемещения (если нужно)
//                         System.out.println(this + " не может переместиться в клетку (" + newX + "," + newY + ").");
                    }
                } finally {
                    // Разблокируем новую клетку
                    newCell.getLock().unlock();
                }
            } else {
                // Логирование выхода за пределы острова (если нужно)
                // System.out.println(this + " не может выйти за пределы острова.");
            }
        } finally {
            // Разблокируем текущую клетку
            currentCell.getLock().unlock();
        }
    }

    public void deathFromHunger(Cell cell){
        List<LivingOrganism> temporarily = cell.getCell();
        List<Animal> deadList = new ArrayList<>();
        this.worker();
        if(this.getIsDead()){
            deadList.add(this);
        }
        temporarily.removeAll(deadList);
        cell.setCell(temporarily);
    }


    public void worker() {
        this.actualSatiety = this.actualSatiety - ((this.maxSatiety * 15) / 100);
        if(this.actualSatiety<=0){
            die(this);
        }
    }
}
