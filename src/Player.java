public class Player  {
    InteractionUtilisateur interactionUtilisateur;
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
        this.interactionUtilisateur = new InteractionUtilisateur();
    }
    public int[] getCoordinates(Integer size){
        return interactionUtilisateur.getCoordinates(size);
    }
    public void captureCell(Cell cell) {
        if (cell.representation == "|   ") {
            cell.representation = this.representation;
        }
    }
}
