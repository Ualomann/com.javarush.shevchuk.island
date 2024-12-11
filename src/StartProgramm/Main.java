package StartProgramm;

import Entity.Location.Cell;
import Entity.Location.Island;

//public class Main {
//    public static void main(String[] args) {
//        Island island = new Island();
//        IslandSimulation islandSimulation = new IslandSimulation(island);
//        islandSimulation.startSimulation();
//    }
//}
//Симуляция для Острова, в данный момент не работает


public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        island.createIsland();
        IslandSimulation sm = new IslandSimulation(island);
        sm.startSimulation();


        }
    }


