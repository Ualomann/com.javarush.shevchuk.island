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
    private final ExecutorService obrabotka;
    private final ReentrantLock lock = new ReentrantLock();

    public IslandSimulation(Island island) {
        this.island = island;
        this.obrabotka = Executors.newFixedThreadPool(countOfThreads);
    }

    public void startSimulation() {



        for (int z = 0; z < 1000; z++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }





            for (int g = 0; g < 1; g++) {
                // Проходим по всем клеткам острова
                for (int i = 0; i < island.getIsland().length; i++) {
                    for (int j = 0; j < island.getIsland()[i].length; j++) {
                        Cell cell = island.getIsland()[i][j];

                        // Если клетка пуста, пропускаем её
                        if (cell.getCell().isEmpty()) {
                            continue;
                        }
                        // Выполняем операции с клеткой


                           obrabotka.execute(() -> {
                               lock.lock();
                               try {
                                   cell.run(); // Работает с животными и растениями в клетке
                               } finally {
                                   lock.unlock();
                               }
                           });

                        obrabotka.execute(() -> {
                            lock.lock();
                            try {
                                // Для каждого животного в клетке выполняем перемещение
                                for (LivingOrganism organism : cell.getCell()) {
                                    if (organism instanceof Animal) {
                                        Animal animal = (Animal) organism;
                                        if(!animal.isMoved() && animal.getIsReproduce()) {
                                            // Вызов метода move для каждого животного
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


//             После того как все клетки обработаны, выводим статистику
            obrabotka.submit(() -> {
                // Обновляем статистику только после обработки всего острова
                Statistic.IslandStatistic ct = new Statistic.IslandStatistic(island.getIsland());
                ct.run(); // Запуск статистики

            });


            growPlants.scheduleAtFixedRate(() -> {
                lock.lock();
                try {
                    island.growPlantIsland();
                } finally {
                    lock.unlock();
                }
            }, 0, 1, TimeUnit.SECONDS);


        }
        // Завершаем работу всех потоков
        obrabotka.shutdown();
        growPlants.shutdown();
    }
}