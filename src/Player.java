public class Player  {
    TicTacToe ticTacToe;
    InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur();
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.ticTacToe = new TicTacToe();
        this.representation = pRepresentation;
    }
    public int[] getCoordinates(){
        return interactionUtilisateur.getCoordinates(ticTacToe.size);
    }
    public void captureCell(Cell cell) {
        if (cell.representation == "|   ") {
            cell.representation = this.representation;
        }
    }
}

