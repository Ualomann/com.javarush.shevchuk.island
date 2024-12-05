package Entity.Predator;

import Entity.Animal;


public abstract class Predator extends Animal {
    Predator(double weight, int maxSpeed, double maxSatiety,int maxCountOfCell,String name, String EMOJI){
        super(weight, maxSpeed, maxSatiety,maxCountOfCell, name, EMOJI);
    }
}
