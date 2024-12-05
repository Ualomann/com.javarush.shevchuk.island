package Entity.Predator;

import java.util.HashMap;

import static Setting.Setting.*;

public class Wolf extends Predator{

    private final HashMap<String,Integer> wolfChance;{
        wolfChance = new HashMap<>();
        wolfChance.put(HORSE_NAME,  CHANCE_WOLF_EAT_HORSE);
        wolfChance.put(DEER_NAME,   CHANCE_WOLF_EAT_DEER);
        wolfChance.put(RABBIT_NAME, CHANCE_WOLF_EAT_RABBIT);
        wolfChance.put(MOUSE_NAME,  CHANCE_WOLF_EAT_MOUSE);
        wolfChance.put(GOAT_NAME,   CHANCE_WOLF_EAT_GOAT);
        wolfChance.put(SHEEP_NAME,  CHANCE_WOLF_EAT_SHEEP);
        wolfChance.put(BOAR_NAME,   CHANCE_WOLF_EAT_BOAR);
        wolfChance.put(BUFFALO_NAME,CHANCE_WOLF_EAT_BUFFALO);
        wolfChance.put(DUCK_NAME,   CHANCE_WOLF_EAT_DUCK);
    }
    @Override
    public HashMap<String, Integer> ChanceToEat() {
        return wolfChance;
    }

    public Wolf(){
        super(WEIGHT_WOLF, MAX_SPEED_WOLF, MAX_SATIETY_WOLF,
                MAX_COUNT_WOLF, WOLF_NAME,WOLF_EMOJI);
    }

}
