package Entity.Herbivore;

import Setting.Omnivorous;

import java.util.HashMap;

import static Setting.Setting.*;

public class Mouse extends Herbivore implements Omnivorous {
    private final HashMap<String,Integer> mouseChance;{
        mouseChance = new HashMap<>();
        mouseChance.put(CATERPILLAR_NAME,CHANCE_MOUSE_EAT_CATERPILLAR);
        mouseChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return mouseChance;
    }

    public Mouse(){
        super(WEIGHT_MOUSE, MAX_SPEED_MOUSE, MAX_SATIETY_MOUSE,
                MAX_COUNT_MOUSE, MOUSE_NAME,MOUSE_EMOJI);
    }

}
