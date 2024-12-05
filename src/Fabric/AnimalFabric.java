package Fabric;

import Entity.Herbivore.*;
import Entity.LivingOrganism;
import Entity.Plant;
import Entity.Predator.*;
import Entity.Location.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFabric {
    public enum LiveOrgNames{
        WOLF(), BOA(), FOX(), BEAR(), EAGLE(),

        HORSE(), DEER(), RABBIT(), MOUSE(), GOAT(),
        SHEEP(), BOAR(), BUFFALO(), DUCK(), CATERPILLAR(),
        PLANT
    }
    private final Cell[][] island;

    public AnimalFabric(Cell[][] island) {
        this.island = island;
    }

    private static List <LivingOrganism> fabricList = new ArrayList<>();

    public static List<LivingOrganism> getFabricList(){
        return fabricList;
    }

    public static void createCountLiveOrganism(LiveOrgNames liveOrgNames,Integer count){
        for (int i = 0; i < count; i++) {
            fabricList.add(createLiveOrganism(liveOrgNames));
        }
    }

    public void populate(LiveOrgNames liveOrgName, int count) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < count; i++) {
            int x = random.nextInt(island.length);
            int y = random.nextInt(island[0].length);

            LivingOrganism organism = createLiveOrganism(liveOrgName);
            if (organism != null) {
                island[x][y].addOrganism(organism); // Добавляем в клетку
                organism.setCurrentCell(island[x][y]); // Устанавливаем текущую клетку
            }
        }
    }


    public static LivingOrganism createLiveOrganism (LiveOrgNames names){

        LivingOrganism livingOrganism = switch (names){
            case WOLF -> new Wolf();
            case BOA -> new Boa();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();
            case RABBIT -> new Rabbit();
            case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case BOAR -> new Boar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();
            case PLANT -> new Plant();
            default -> null;
        };
        return livingOrganism;
    }

}
