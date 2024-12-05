package Entity.Location;

import static Setting.SettingOfCell.HEIGHT;
import static Setting.SettingOfCell.WIDTH;


public class Island {
    private final Cell [][] island;
    public Island (){
        island = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Cell cell = new Cell(i,j);
                if(i < WIDTH / 2 && j < HEIGHT / 2) {
                    cell.addOrganisms();
                }
                island[i][j] = cell;
            }
        }
    }
    public Cell[][] getIsland(){
        return island;
    }
    public Cell getCell(int x, int y){
        return island[x][y];
    }

//    public void removeDeadOrganisms() {
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//                getCell(x, y).removeDeadOrganisms();
//            }
//        }
//    }

}
