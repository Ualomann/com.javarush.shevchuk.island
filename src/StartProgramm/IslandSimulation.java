package StartProgramm;

import Entity.Animal;
import Entity.LivingOrganism;
import Entity.Location.Cell;
import Entity.Location.Island;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static Setting.SettingOfCell.countOfThreads;

public class IslandSimulation {
    private final Island island;
    private final ScheduledExecutorService growPlants = Executors.newScheduledThreadPool(1);
    private final ExecutorService cellProcessing;
    private final ReentrantLock lock = new ReentrantLock();

    public IslandSimulation(Island island) {
        this.island = island;
        this.cellProcessing = Executors.newFixedThreadPool(countOfThreads);
    }
    public void startSimulation() {
        for (int day = 0; day < 100; day++) {
            System.out.println("День: " + day);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int g = 0; g < 1; g++) {
                for (int i = 0; i < island.getIsland().length; i++) {
                    for (int j = 0; j < island.getIsland()[i].length; j++) {
                        Cell cell = island.getIsland()[i][j];
                        if (cell.getCell().isEmpty()) {
                            continue;
                        }

                           cellProcessing.execute(() -> {
                               lock.lock();
                               try {
                                   cell.run();
                               } finally {
                                   lock.unlock();
                               }
                           });

                        cellProcessing.execute(() -> {
                            lock.lock();
                            try {
                                for (LivingOrganism organism : cell.getCell()) {
                                    if (organism instanceof Animal) {
                                        Animal animal = (Animal) organism;
                                        if(!animal.isMoved() && animal.getIsReproduce()) {
                                            animal.move(cell, island.getIsland());
                                        }
                                    }
                                }
                            } finally {
                                lock.unlock();
                            }
                        });

                    }
                }
            }
            cellProcessing.submit(() -> {
                Statistic.IslandStatistic ct = new Statistic.IslandStatistic(island.getIsland());
                ct.run();
            });


            growPlants.scheduleAtFixedRate(() -> {
                lock.lock();
                try {
                    island.growPlantIsland();
                } finally {
                    lock.unlock();
                }
            }, 0, 3, TimeUnit.SECONDS);
        }
        cellProcessing.shutdown();
        growPlants.shutdown();
    }
}