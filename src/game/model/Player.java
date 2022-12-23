package game.model;

import game.view.InteractionUtilisateur;

import java.util.Objects;

public abstract class Player {
    public String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
    }

    /**
     * This is method of abstract class which just call getCoordinates method from game.view.InteractionUtilisateur object instance
     * @param size int - game field size
     * @param interView game.view.InteractionUtilisateur object instance
     * @return getCoordinates method from game.view.InteractionUtilisateur object instance
     */
    public int[] getCoordinates(Integer size, InteractionUtilisateur interView) {
        return interView.getCoordinates(size, interView);
    }

    /**
     * Method which used for capture cell after player step
     * @param cell cell of game field
     */
    public void captureCell(Cell cell) {
        if (Objects.equals(cell.representation, "|   ")) {
            cell.setPlayer(this);
            cell.representation = this.representation;
        }
    }
}
