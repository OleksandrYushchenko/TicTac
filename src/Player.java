import java.util.Objects;

abstract class Player {
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
    }
    public int[] getCoordinates(Integer size, InteractionUtilisateur interView) {
        return interView.get_Coordinates(size, interView);
    }
    public void captureCell(Cell cell) {
        if (Objects.equals(cell.representation, "|   ")) {
            cell.setPlayer(this);
            cell.representation = this.representation;
        }
    }
}
