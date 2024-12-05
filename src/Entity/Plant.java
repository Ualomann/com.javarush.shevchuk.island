package Entity;

import Entity.Location.Cell;

import java.util.ArrayList;

import static Setting.PlantSetting.PLANT_COUNT;
import static Setting.Setting.*;


public class Plant extends LivingOrganism{
    Cell cell;
    public boolean isDead;


    @Override
    public boolean getIsDead() {
        return super.getIsDead();
    }

    public void die(LivingOrganism prey) {
        super.die(prey);
    }


    public Plant(){
        super(WEIGHT_PLANT,MAX_COUNT_PLANT,PLANT_NAME,PLANT_EMOJI);
    }

    @Override
    public LivingOrganism reproduce(LivingOrganism partner) {
        return this;
    }
    public void move(Cell currentCell, Cell[][] island){
        return;
    }
    public void eat(LivingOrganism prey){
        return;
    }
    public void worker() {return;}

    public static void canGrow(ArrayList<LivingOrganism> organisms){

    }

    public static void grow(Cell cell){

        for (int i = 0; i < PLANT_COUNT; i++) {
            cell.addOrganism(new Plant());
        }
    }

}
