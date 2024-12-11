package Entity.Herbivore;
import java.util.HashMap;

import static Setting.Setting.*;

public class Boar extends Herbivore{
    private final HashMap<String,Integer> boarChance;{
        boarChance = new HashMap<>();
        boarChance.put(MOUSE_NAME,  CHANCE_BOAR_EAT_MOUSE);
        boarChance.put(CATERPILLAR_NAME,CHANCE_BOAR_EAT_CATERPILLAR);
        boarChance.put(PLANT_NAME,CHANCE_PLANT_BEING_EATEN);

    }
    @Override
    public HashMap<String, Integer> chanceToEat() {
        return boarChance;
    }

    public Boar(){
        super(WEIGHT_BOAR, MAX_SPEED_BOAR, MAX_SATIETY_BOAR, MAX_COUNT_BOAR, BOAR_NAME, BOAR_EMOJI);
    }

}
