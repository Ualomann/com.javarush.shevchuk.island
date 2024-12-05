package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;
import static Setting.Setting.MAX_COUNT_CATERPILLAR;

public class Caterpillar extends Herbivore{
    private final HashMap<String,Integer> caterpillarChance;{
        caterpillarChance = new HashMap<>();
        caterpillarChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return caterpillarChance;
    }

    public Caterpillar(){
        super(WEIGHT_CATERPILLAR, MAX_SPEED_CATERPILLAR, MAX_SATIETY_CATERPILLAR,
                MAX_COUNT_CATERPILLAR, CATERPILLAR_NAME,CATERPILLAR_EMOJI);
    }

}
