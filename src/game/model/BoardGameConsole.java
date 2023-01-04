package game.model;

public class BoardGameConsole implements BoardGame{
    private Cell[][] cells;
    public Player playerX;
    public Player playerO;
    public BoardGameConsole (int size, int typeOfGame){
        // Instanciate the player
        switch (typeOfGame) {
            case 1 -> {
                this.playerX = new HumanPlayer("| X ");
                this.playerO = new HumanPlayer("| O ");
            }
            case 2 -> {
                this.playerX = new HumanPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
            default -> {
                this.playerX = new ArtificialPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
        }
        // Instanciate the cells
        this.cells = new Cell[size][size];
        // Populate the cells (initialization)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
    public Player getPlayerX (){
        return playerX;
    }
    public Player getPlayerO (){
        return playerO;
    }
    @Override
    public Cell[][] getCells () {
        return cells;
    }
    @Override
    public Cell getCell (Integer i, int j) {
        return cells[i][j];
    }
    @Override
    public void resetBoard (int size, int typeOfGame) {
        switch (typeOfGame) {
            case 1 -> {
                this.playerX = new HumanPlayer("| X ");
                this.playerO = new HumanPlayer("| O ");
            }
            case 2 -> {
                this.playerX = new HumanPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
            default -> {
                this.playerX = new ArtificialPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
        }
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
