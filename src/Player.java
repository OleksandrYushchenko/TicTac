import java.util.Objects;

abstract class Player {
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
    }

    /**
     * This is method of abstract class which just call getCoordinates method from InteractionUtilisateur object instance
     * @param size int - game field size
     * @param interView InteractionUtilisateur object instance
     * @return getCoordinates method from InteractionUtilisateur object instance
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
