package StartProgramm;

import Entity.Location.Cell;
import Entity.Plant;
import Statistic.Statistic;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static Setting.SettingOfCell.countOfThreads;
//
//    public class Simulation {
//        ReentrantLock lock = new ReentrantLock();
//        Cell kletka = new Cell(0,0);
//        ScheduledExecutorService growPlants = Executors.newScheduledThreadPool(1);;
//        ExecutorService obrabotka = Executors.newFixedThreadPool(countOfThreads);
//
//
//        public void startSimulation(Cell cell){
//            kletka = cell;
//            kletka.addOrganisms();
//            kletka.getPlantInCell().addAll(Fabric.StartingValue.creationPlantOnCell());
//            Statistic ct = new Statistic(kletka);
//            for (int j = 0; j < 100; j++) {
//                for (int i = 0; i < 1; i++) {
//                    obrabotka.execute(() -> {
//                        lock.lock();
//                        try {
//                            kletka.run();
//                        }finally{
//                            lock.unlock();
//                        }
//                        ct.run(); // Обновление статистики
//                    });
//
//
//                    obrabotka.execute(()->{
//                        lock.lock();
//                        try{
//                            kletka.getCell().addAll(kletka.getCellOffspring());
//                            kletka.getCellOffspring().clear();
//                        }
//                        finally {
//                            lock.unlock();
//                        }
//                    });
//
//                    growPlants.scheduleAtFixedRate(() -> {
//                        lock.lock();
//                        try {
//                            Plant.grow(cell);
//                        } finally {
//                            lock.unlock();
//                        }
//                    }, 0, 1, TimeUnit.SECONDS);
//                }
//            }
//            obrabotka.shutdown();
//            growPlants.shutdown();
//            try {
//                growPlants.awaitTermination(5,TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
