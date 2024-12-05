package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Buffalo extends Herbivore{
    private final HashMap<String,Integer> bufalloChance;{
        bufalloChance = new HashMap<>();
        bufalloChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return bufalloChance;
    }

    public Buffalo(){
        super(WEIGHT_BUFFALO, MAX_SPEED_BUFFALO, MAX_SATIETY_BUFFALO,
                MAX_COUNT_BUFFALO, BUFFALO_NAME,BUFFALO_EMOJI);
    }

}

