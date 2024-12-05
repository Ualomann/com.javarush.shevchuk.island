package Entity.Herbivore;

import Entity.Animal;
import Entity.LivingOrganism;
import Entity.Plant;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Herbivore extends Animal {
    ReentrantLock lock = new ReentrantLock();
    public void eat(LivingOrganism prey){
        lock.lock();
        try {
            setEat(false);
            if (prey == null || prey.getIsDead() || this.getActualSatiety() == this.getMaxSatiety() || this.isEat()) {
                return;
            }

            if (!(prey instanceof Plant)) {
                super.eat(prey);
                return;
            } else {
                this.setActualSatiety(this.getActualSatiety() + (prey.getWeight()));
                if (this.getActualSatiety() >= getMaxSatiety()) {
                    this.setActualSatiety(getMaxSatiety());
                }
            }
            setEat(true);
            die(prey);
        }finally{
            lock.unlock();
        }
    }

    Herbivore(double weight, int maxSpeed, double maxSatiety,int maxCountOfCell,String name,String EMOJI){
        super(weight, maxSpeed, maxSatiety,maxCountOfCell, name,EMOJI);
    }
}