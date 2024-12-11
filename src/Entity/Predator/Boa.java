package Entity.Predator;

import java.util.HashMap;

import static Setting.Setting.*;

public class Boa extends Predator{
    private final HashMap<String,Integer> boaChance;{
        boaChance = new HashMap<>();

        boaChance.put(FOX_NAME,    CHANCE_BOA_EAT_FOX);

        boaChance.put(HORSE_NAME,  CHANCE_BOA_EAT_HORSE);
        boaChance.put(DEER_NAME,   CHANCE_BOA_EAT_DEER);
        boaChance.put(RABBIT_NAME, CHANCE_BOA_EAT_RABBIT);
        boaChance.put(MOUSE_NAME,  CHANCE_BOA_EAT_MOUSE);


        boaChance.put(DUCK_NAME,   CHANCE_BOA_EAT_DUCK);


    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return boaChance;
    }

    public Boa(){
        super(WEIGHT_BOA, MAX_SPEED_BOA, MAX_SATIETY_BOA,
                MAX_COUNT_BOA, BOA_NAME,BOA_EMOJI);
    }


}
