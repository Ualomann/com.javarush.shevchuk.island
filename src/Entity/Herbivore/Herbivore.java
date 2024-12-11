package Entity.Herbivore;

import Entity.Animal;
import Entity.LivingOrganism;
import Entity.Plant;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Herbivore extends Animal {
    Herbivore(double weight, int maxSpeed, double maxSatiety,int maxCountOfCell,String name,String EMOJI){
        super(weight, maxSpeed, maxSatiety,maxCountOfCell, name,EMOJI);
    }
}