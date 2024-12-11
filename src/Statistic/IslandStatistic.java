package Statistic;


import Entity.LivingOrganism;
import Entity.Location.Cell;

import java.util.HashMap;
import java.util.Map;

public class IslandStatistic implements Runnable {
    private Cell[][] island;

    public IslandStatistic(Cell[][] island) {
        this.island = island;
    }

    @Override
    public void run() {
        Map<String, Integer> counts = new HashMap<>();


        for (Cell[] row : island) {
            for (Cell cell : row) {

                for (LivingOrganism organism : cell.getCell()) {
                    // Получаем эмодзи для каждого организма (можно добавить логику для разных типов)
                    String emoji = organism.getEMOJI();
                    counts.merge(emoji, 1, Integer::sum);  // Увеличиваем счетчик по каждому эмодзи
                }
            }
        }


        System.out.println("Общее количество животных и растений на острове:");
        counts.forEach((emoji, count) -> System.out.println(emoji + ": " + count));
        System.out.println();
    }
}