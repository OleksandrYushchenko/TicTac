package game.model;

import java.util.Objects;

public abstract class Player {
    private String representation;
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
        if (Objects.equals(cells[x][y].getRepresentation(), "|   ")) {
            cells[x][y].setPlayer(this);
            cells[x][y].setRepresentation(this.representation);
        }
    }

    public String getRepresentation () {
        return representation;
    }
}
