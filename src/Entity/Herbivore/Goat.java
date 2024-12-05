package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Goat extends Herbivore{
    private final HashMap<String,Integer> goatChance;{
        goatChance = new HashMap<>();
        goatChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return goatChance;
    }

    public Goat(){
        super(WEIGHT_GOAT, MAX_SPEED_GOAT, MAX_SATIETY_GOAT,
                MAX_COUNT_GOAT, GOAT_NAME,GOAT_EMOJI);
    }

}
