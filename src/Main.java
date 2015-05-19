package game_of_life;

public class Main {

    public static void main(String[] args) {
        GameOfLife.init(150, 100, 2); // ROWS, COLS, DELAY.
        GameGrid grid = new GameGrid(8);
        Window frame = new Window("Game of Life -- By Israel Gilyadov", GameOfLife.gameGrid.getWIDTH()*grid.getCellSize(), GameOfLife.gameGrid.getHEIGHT()*grid.getCellSize());
        
        frame.add(grid);
        grid.requestGridFocus();
    }

}
  