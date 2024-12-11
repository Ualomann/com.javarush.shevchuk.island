package Entity;

import Entity.Location.Cell;
import Fabric.AnimalFabric;

import java.util.ArrayList;
import java.util.List;

import static Setting.PlantSetting.PLANT_COUNT;
import static Setting.Setting.*;


public class Plant extends LivingOrganism{

    public Plant(){
        super(WEIGHT_PLANT,MAX_COUNT_PLANT,PLANT_NAME,PLANT_EMOJI);
    }


    public void grow(Cell cell) {
        if (cell.getPlantOnCell().size() >= MAX_COUNT_PLANT) {
            List<LivingOrganism> temporarily = cell.getCell();
            for (int i = 0; i < PLANT_COUNT; i++) {
                LivingOrganism organism = AnimalFabric.createLiveOrganism(AnimalFabric.LiveOrgNames.PLANT);
                temporarily.add(organism);
            }
        cell.setCell(temporarily);
        }
    }
}
