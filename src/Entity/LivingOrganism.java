package Entity;

import Entity.Location.Cell;

import java.util.concurrent.locks.ReentrantLock;

public abstract class LivingOrganism {
    boolean isDead;

    private double weight;
    private int maxCountOfCell;
    private String name;
    private final String EMOJI;


    LivingOrganism(double weight,int maxCountOfCell,String name,String EMOJI){
        this.weight = weight;
        this.maxCountOfCell = maxCountOfCell;
        this.name = name;
        this.EMOJI = EMOJI;
        isDead = false;
    }
    public boolean getIsDead(){
        return isDead;
    }
    public double getWeight(){
        return weight;
    }

    public String getName() {return name;}

    public int getMaxCountOfCell() {return maxCountOfCell;}
    public Cell getCurrentCell() {
        return new Cell(0,0);
    }

    public void die(LivingOrganism animal){
        if (animal == null){
            return;
        }
        if(animal.getIsDead()){
            return;
        }
        isDead = true;
    }

    public String getEMOJI() {
        return this.EMOJI;
    }
}
