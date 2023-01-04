package game.model;

public interface BoardGame {
    void resetBoard (int size, int typeOfGame);
    Player getPlayerX ();
    Player getPlayerO ();
    Cell[][] getCells ();
    Cell getCell (Integer i, int j);
}
