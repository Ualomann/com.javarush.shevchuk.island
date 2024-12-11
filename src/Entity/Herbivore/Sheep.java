package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Sheep extends Herbivore{
    private final HashMap<String,Integer> sheepChance;{
        sheepChance = new HashMap<>();
        sheepChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return sheepChance;
    }

    public Sheep(){
        super(WEIGHT_SHEEP, MAX_SPEED_SHEEP, MAX_SATIETY_SHEEP,
                MAX_COUNT_SHEEP, SHEEP_NAME,SHEEP_EMOJI);
    }
}
