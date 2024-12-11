package Entity.Herbivore;

import java.util.HashMap;

import static Setting.Setting.*;

public class Mouse extends Herbivore{
    private final HashMap<String,Integer> mouseChance;{
        mouseChance = new HashMap<>();
        mouseChance.put(CATERPILLAR_NAME,CHANCE_MOUSE_EAT_CATERPILLAR);
        mouseChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return mouseChance;
    }

    public Mouse(){
        super(WEIGHT_MOUSE, MAX_SPEED_MOUSE, MAX_SATIETY_MOUSE,
                MAX_COUNT_MOUSE, MOUSE_NAME,MOUSE_EMOJI);
    }

}
