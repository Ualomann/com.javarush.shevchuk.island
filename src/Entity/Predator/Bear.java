package Entity.Predator;

import java.util.HashMap;

import static Setting.Setting.*;

public class Bear extends Predator{
    private final HashMap<String,Integer> bearChance;{
        bearChance = new HashMap<>();

        bearChance.put(BOA_NAME,    CHANCE_BEAR_EAT_BOA);


        bearChance.put(HORSE_NAME,  CHANCE_BEAR_EAT_HORSE);
        bearChance.put(DEER_NAME,   CHANCE_BEAR_EAT_DEER);
        bearChance.put(RABBIT_NAME, CHANCE_BEAR_EAT_RABBIT);
        bearChance.put(MOUSE_NAME,  CHANCE_BEAR_EAT_MOUSE);
        bearChance.put(GOAT_NAME,   CHANCE_BEAR_EAT_GOAT);
        bearChance.put(SHEEP_NAME,  CHANCE_BEAR_EAT_SHEEP);
        bearChance.put(BOAR_NAME,   CHANCE_BEAR_EAT_BOAR);
        bearChance.put(BUFFALO_NAME,CHANCE_BEAR_EAT_BUFFALO);
        bearChance.put(DUCK_NAME,   CHANCE_BEAR_EAT_DUCK);

    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return bearChance;
    }

    public Bear(){
        super(WEIGHT_BEAR, MAX_SPEED_BEAR, MAX_SATIETY_BEAR,
                MAX_COUNT_BEAR, BEAR_NAME,BEAR_EMOJI);
    }
}
