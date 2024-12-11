package Entity.Predator;

import java.util.HashMap;

import static Setting.Setting.*;

public class Eagle extends Predator{
    private final HashMap<String,Integer> eagleChance;{
        eagleChance = new HashMap<>();

        eagleChance.put(FOX_NAME,    CHANCE_EAGLE_EAT_FOX);

        eagleChance.put(RABBIT_NAME, CHANCE_EAGLE_EAT_RABBIT);
        eagleChance.put(MOUSE_NAME,  CHANCE_EAGLE_EAT_MOUSE);


        eagleChance.put(DUCK_NAME,   CHANCE_EAGLE_EAT_DUCK);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return eagleChance;
    }

    public Eagle(){
        super(WEIGHT_EAGLE, MAX_SPEED_EAGLE, MAX_SATIETY_EAGLE,
                MAX_COUNT_EAGLE, EAGLE_NAME,EAGLE_EMOJI);
    }
}
