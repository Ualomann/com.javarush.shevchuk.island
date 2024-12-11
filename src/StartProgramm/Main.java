package StartProgramm;

import Entity.Location.Island;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        island.createIsland();
        IslandSimulation sm = new IslandSimulation(island);
        sm.startSimulation();
        }
    }


