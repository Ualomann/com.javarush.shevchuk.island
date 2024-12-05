package StartProgramm;

import Entity.Location.Cell;
import Entity.Location.Island;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
        // Проходим по всем клеткам острова
        for (int i = 0; i < island.getIsland().length; i++) {
            for (int j = 0; j < island.getIsland()[i].length; j++) {
                Cell cell = island.getIsland()[i][j];

                // Если клетка пуста, пропускаем её
                if (cell.getCell().isEmpty()) {
                    continue;
                }

                // Выполняем операции с клеткой


//                // Создание травы
//                growPlants.scheduleAtFixedRate(() -> {
//                    lock.lock();
//                    try {
//                        Plant.grow(cell);
//                    } finally {
//                        lock.unlock();
//                    }
//                }, 0, 1, TimeUnit.SECONDS);

                // Обработка клеток (работа, размножение, питание, перемещение)
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
                        // Перемещаем потомков в основную коллекцию
                        cell.getCell().addAll(cell.getCellOffspring());
                        cell.getCellOffspring().clear();
                    } finally {
                        lock.unlock();
                    }
                });

                // Обновление клетки: удаление мертвых и добавление новоприбывших
//                obrabotka.execute(() -> {
//                    lock.lock();
//                    try {
//                        cell.updateMove(); // Обновление клетки
//                    } finally {
//                        lock.unlock();
//                    }
//                });
            }
        }

        // После того как все клетки обработаны, выводим статистику
        obrabotka.submit(() -> {
            // Обновляем статистику только после обработки всего острова
            Statistic.IslandStatistic ct = new Statistic.IslandStatistic(island.getIsland());
            ct.run(); // Запуск статистики
        });

        // Завершаем работу всех потоков
        obrabotka.shutdown();
        growPlants.shutdown();
    }
}