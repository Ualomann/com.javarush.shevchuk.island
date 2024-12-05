package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Horse extends Herbivore{
    private final HashMap<String,Integer> horseChance;{
        horseChance = new HashMap<>();
        horseChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return horseChance;
    }

    public Horse(){
        super(WEIGHT_HORSE, MAX_SPEED_HORSE, MAX_SATIETY_HORSE,
                MAX_COUNT_HORSE, HORSE_NAME,HORSE_EMOJI);
    }
}
