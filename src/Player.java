import java.util.Scanner;
public class Player  {
    InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur();
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory

        this.representation = pRepresentation;
    }
    public int[] getCoordinates(){
        return interactionUtilisateur.getCoordinates();
    }
    public void captureCell(Cell cell) {
        if (cell.representation == "|   ") {
            cell.representation = this.representation;
        }
    }
}

