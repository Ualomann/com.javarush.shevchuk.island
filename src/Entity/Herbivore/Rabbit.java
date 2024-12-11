package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Rabbit extends Herbivore{
    private final HashMap<String,Integer> rabbitChance;{
        rabbitChance = new HashMap<>();
        rabbitChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return rabbitChance;
    }

    public Rabbit(){
        super(WEIGHT_RABBIT, MAX_SPEED_RABBIT, MAX_SATIETY_RABBIT,
                MAX_COUNT_RABBIT, RABBIT_NAME,RABBIT_EMOJI);
    }
}
