package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Deer extends Herbivore{
    private final HashMap<String,Integer> deerChance;{
        deerChance = new HashMap<>();
        deerChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return deerChance;
    }

    public Deer(){
        super(WEIGHT_DEER, MAX_SPEED_DEER, MAX_SATIETY_DEER,
                MAX_COUNT_DEER, DEER_NAME, DEER_EMOJI);
    }

}
