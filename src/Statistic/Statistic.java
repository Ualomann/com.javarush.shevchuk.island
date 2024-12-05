package Statistic;

import Entity.LivingOrganism;
import Entity.Location.Cell;

import java.util.HashMap;
import java.util.Map;

public class Statistic implements Runnable{
    Cell cell;
    public Statistic(Cell cell){
        this.cell = cell;
    }
    @Override
    public void run() {
        Map<String, Integer> counts = new HashMap<>();

        // Проходим по всем организмам в клетке
        for (LivingOrganism organism : cell.getCell()) {
            // Получаем эмодзи для каждого организма (можно добавить логику для разных типов)
            String emoji = organism.getEMOJI();
            counts.merge(emoji, 1, Integer::sum);  // Увеличиваем счетчик по каждому эмодзи
        }
        // Выводим результаты подсчёта с эмодзи
        System.out.println("Всего животных:");
        counts.forEach((emoji, count) -> System.out.print(emoji + ": " + count+ "  " ));
        System.out.println();
    }
}
