public class BoardGame {
    Cell[][] cells;

    public BoardGame(int size){
        // Instanciate the cells
        this.cells = new Cell[size][size];
        // Populate the cells (initialization)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
