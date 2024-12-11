package Entity.Location;

import Entity.Animal;
import Entity.LivingOrganism;
import Entity.Plant;
import Fabric.StartingValue;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Cell implements Runnable{

    private final int x;
    private final int y;
    private final ReentrantLock lock = new ReentrantLock();
    private List<LivingOrganism> cell;

    public long getAnimalCountOnCell(Animal animal){
        return cell.stream().filter(element -> animal.getClass().isInstance(element)).count();
    }

    public List <Animal> getAnimalOnCell(){
        return cell.stream().filter(element-> element instanceof Animal)
                .map(element -> (Animal)element)
                .toList();
    }

    public List <Plant> getPlantOnCell(){
        return cell.stream().filter(element-> element instanceof Plant)
                .map(element -> (Plant)element)
                .toList();
    }


    public List<LivingOrganism> getCellOffspring() {
        return cellOffspring;
    }

    private final List<LivingOrganism> cellOffspring = new CopyOnWriteArrayList<>();


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentCell = this;
        this.cell = new CopyOnWriteArrayList<>();
    }
    private Cell currentCell;

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
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
    public void setCell(List<LivingOrganism> cell){
        this.cell = cell;
    }

    public ReentrantLock getLock() {
        return lock;
    }




    public void addOrganisms() {
        List<LivingOrganism> temporarily = cell;
        List<LivingOrganism> organisms = StartingValue.populatingOneCell();
        temporarily.addAll(organisms);
        setCell(temporarily);
    }

    public void addOrganism(LivingOrganism animal) {
            cell.add(animal);
    }


    public void removeOrganism(LivingOrganism animal) {
            cell.remove(animal);
    }

    public void processWorking(){
        List<Animal> animals = getAnimalOnCell();
        List<Plant> plants = getPlantOnCell();
        currentCell.getLock().lock();
        try {
            plants.forEach(plant -> plant.grow(currentCell));
            animals.forEach(animal -> {

                animal.reproduceOnCell(currentCell);

            });
            animals.forEach(animal -> animal.eatOnCell(currentCell));

            animals.forEach(animal -> animal.deathFromHunger(currentCell));



        }finally{
            currentCell.getLock().unlock();
        }
    }

    @Override
    public void run() {
        getLock().lock();
        try{
            processWorking();
        }
        finally{
            getLock().unlock();
        }
    }
}

    

