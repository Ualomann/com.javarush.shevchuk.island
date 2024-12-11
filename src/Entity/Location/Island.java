package Entity.Location;

import Entity.LivingOrganism;
import Entity.Plant;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static Setting.SettingOfCell.HEIGHT;
import static Setting.SettingOfCell.WIDTH;


public class Island {
    private final Cell [][] island;
//    private final int x;
//    private final int y;
    public Island (){
        island = new Cell[WIDTH][HEIGHT];
    }
    public void createIsland() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Cell cell = new Cell(x,y);
                if (x < WIDTH / 2 && y < HEIGHT / 2) {
                    cell.addOrganisms();
                }
                cell.addPlants();
                island[x][y] = cell;
            }
        }
    }

    public void growPlantIsland() {
        for (int x = 0; x < island.length; x++) {
            for (int y = 0; y < island[x].length; y++) {
                Plant plant = new Plant();
                plant.grow(getCell(x,y));
            }
        }
    }

    public Cell[][] getIsland(){
        return island;
    }
    public Cell getCell(int x, int y){
        return island[x][y];
    }
    private final List<LivingOrganism> cellMoved = new CopyOnWriteArrayList<>();
    public List<LivingOrganism> getCellMoved() {
        return cellMoved;
    }


}
