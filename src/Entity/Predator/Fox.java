package Entity.Predator;

import java.util.HashMap;

import static Setting.Setting.*;

public class Fox extends Predator{
    private final HashMap<String,Integer> foxChance;{
        foxChance = new HashMap<>();

        foxChance.put(RABBIT_NAME, CHANCE_FOX_EAT_RABBIT);
        foxChance.put(MOUSE_NAME,  CHANCE_FOX_EAT_MOUSE);


        foxChance.put(DUCK_NAME,   CHANCE_FOX_EAT_DUCK);
        foxChance.put(CATERPILLAR_NAME,CHANCE_FOX_EAT_CATERPILLAR);


    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return foxChance;
    }

    public Fox(){
        super(WEIGHT_FOX, MAX_SPEED_FOX, MAX_SATIETY_FOX,
                MAX_COUNT_FOX, FOX_NAME,FOX_EMOJI);
    }
}
