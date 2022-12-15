import java.util.Scanner;
public class Player  {
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory

        this.representation = pRepresentation;
    }
    public int[] getCoordinates(){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter coordinate X - ");
        int x = sc.nextInt();
        while (x > TicTacToe.size) {
            System.out.print("Enter coordinate X " + "(x<=" + TicTacToe.size + ") - " );
            x = sc.nextInt();
        }
        System.out.print("Enter coordinate Y - ");
        int y = sc.nextInt();
        while (y > TicTacToe.size) {
            System.out.print("Enter coordinate Y " + "(y<=" + TicTacToe.size + ") - " );
            y = sc.nextInt();
        }

        return new int[]{ x, y };
    }
    public void captureCell(Cell cell) {
        if (cell.representation == "|   ") {
            cell.representation = this.representation;
        }
//        if (this.representation == "| X " && cell.representation != "| O ") {
//            cell.representation = "| X ";
//        } else if (this.representation == "| O " && cell.representation != "| X ") {
//            cell.representation = "| O ";
//        }
    }
}

