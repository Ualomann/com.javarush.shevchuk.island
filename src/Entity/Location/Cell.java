package Entity.Location;

import Entity.Animal;
import Entity.Herbivore.Herbivore;
import Entity.LivingOrganism;
import Entity.Plant;
import Fabric.StartingValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Cell implements Runnable {

    private final int x;
    private final int y;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<LivingOrganism> cell = new CopyOnWriteArrayList<>();
    private final List<LivingOrganism> cellDied = new CopyOnWriteArrayList<>();
    private final List<LivingOrganism> cellMoved = new CopyOnWriteArrayList<>();


    public List<LivingOrganism> getCellMoved() {
        return cellMoved;
    }

//    private final Island island = new Island();

    public void updateMove(){
        cell.addAll(cellMoved);
        cell.forEach(organism->{
                if(organism instanceof Animal){
                    Animal animal = (Animal) organism;
                    animal.setMoved(false);
                }
                });
        cellMoved.clear();
    }



    public List<LivingOrganism> getCellOffspring() {
        return cellOffspring;
    }

    private final List<LivingOrganism> cellOffspring = new CopyOnWriteArrayList<>();


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<LivingOrganism> getCell() {
        return cell;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public boolean canMoveOrganism(LivingOrganism organism) {
        int countOfType = (int) cell.stream()
                .filter(o -> o.getClass().equals(organism.getClass()))
                .count();
        return countOfType < organism.getMaxCountOfCell();  // Проверяем лимит на этот вид
    }

    public void addOrganisms() {
        List<LivingOrganism> organisms = StartingValue.populatingOneCell();
        for (LivingOrganism organism : organisms) {
            if (organism instanceof Animal) {
                Animal animalObj = (Animal) organism;
                animalObj.setCurrentCell(this);  // Устанавливаем текущую клетку для каждого животного
            }

        }
        cell.addAll(organisms);

    }

    public void addOrganism(LivingOrganism animal) {
        lock.lock();
        try {
            cell.add(animal);
        } finally {
            lock.unlock();
        }
    }

    public void addReproduce(LivingOrganism animal) {
        lock.lock();
        try {
            LivingOrganism lv = (LivingOrganism) animal;
            //  lv.setCurrentCell(this); Устанавливает текущую клетку для животинки
            cellOffspring.add(animal);
        } finally {
            lock.unlock();
        }
    }


    public void removeOrganism(LivingOrganism animal) {
        lock.lock();
        try {
            cell.remove(animal);
        } finally {
            lock.unlock();
        }
    }

    public void removeDeadOrganisms() {
        lock.lock();
        try {
            cell.removeIf(organism -> {
                if (organism.getIsDead()) {
                    return true;     // Удаляем из списка
                }
                return false; // Оставляем живых
            });
        } finally {
            lock.unlock();
        }
    }

//

    @Override
    public void run() {
        lock.lock();

        try {
            // Работать
            for (LivingOrganism organism : new ArrayList<>(cell)) {
                if (organism instanceof Animal) {
                    ((Animal) organism).worker();
                }
            }


//             Размножаться
            for (LivingOrganism organism : new ArrayList<>(cell)) {
                if (organism instanceof Animal) {
                    Animal animal = (Animal) organism;
                    for (LivingOrganism partner : new ArrayList<>(cell)) {
                        animal.reproduce(partner);
                    }
                }
            }

            // Жрать
            for (LivingOrganism organism : new ArrayList<>(cell)) {
                if (organism instanceof Herbivore) {
                    Herbivore herbivore = (Herbivore) organism;
                    for (LivingOrganism target : new ArrayList<>(cell)) {
                        if (target instanceof Plant) {
                            herbivore.eat(target);
                        }
                        if (target.getIsDead()) {
                            break;
                        }
                    }
                } else if (organism instanceof Animal) {
                    Animal animal = (Animal) organism;
                    for (LivingOrganism target : new ArrayList<>(cell)) {
                        animal.eat(target);
                        if (target.getIsDead()) {
                            break;
                        }
                    }
                }
            }

//            //Перемещаться
//            for (LivingOrganism organism : new ArrayList<>(cell)) {
//                if(organism instanceof Animal){
//                    Animal animal = (Animal) organism;
//                    animal.move(animal.getCurrentCell(),island.getIsland());
//                }
//            }


//            Удалить мёртвых из списка
            cell.removeIf(animal -> {
                if (animal.getIsDead()) {
                    return true;
                }
                return false;
            });


//            synchronized (cell) {
//                if (cellOffspring.size() > 0) {
//                    cell.addAll(cellOffspring);
//                }
//                cellOffspring.clear();
//            }
        } finally {
            lock.unlock();
        }
    }
}

    

