package game.model;

import java.util.Objects;

public abstract class Player {
    public String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
    }
    public boolean isArtificial (){
        return false;
    }

    public int[] playerMove(int[] arr, int size) {
        return arr;
    }

    /**
     * Method which used for capture cell after player step
     */
    public void captureCell(Cell[][] cells, int x, int y) {
        if (Objects.equals(cells[x][y].representation, "|   ")) {
            cells[x][y].setPlayer(this);
            cells[x][y].representation = this.representation;
        }
    }
}
