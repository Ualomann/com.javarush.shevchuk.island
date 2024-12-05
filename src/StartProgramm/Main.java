package StartProgramm;

import Entity.Location.Cell;

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
        Cell cell = new Cell(0,0);
        Simulation sm = new Simulation();
        sm.startSimulation(cell);
    }
}


