package Entity.Herbivore;

import Setting.Omnivorous;

import java.util.HashMap;

import static Setting.Setting.*;

public class Duck extends Herbivore implements Omnivorous {
    private final HashMap<String,Integer> duckChance;{
        duckChance = new HashMap<>();
        duckChance.put(CATERPILLAR_NAME,CHANCE_DUCK_EAT_CATERPILLAR);
        duckChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return duckChance;
    }

    public Duck(){
        super(WEIGHT_DUCK, MAX_SPEED_DUCK, MAX_SATIETY_DUCK,
                MAX_COUNT_DUCK, DUCK_NAME,DUCK_EMOJI);
    }

}
