import java.util.Objects;
import java.util.Scanner;

public class Player {
    String representation;
    public Player(String pRepresentation){ // Constructor is always mandatory
        this.representation = pRepresentation;
    }
    public int[] getCoordinates(Integer size, String startVisualisation, String endVisualisation){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                System.out.print(startVisualisation + "Enter coordinate X - " + endVisualisation);
                x = sc.nextInt();
                while (x > size - 1) {
                    System.out.print(startVisualisation  + "Enter coordinate X " + "(x<=" + size + ") - " + endVisualisation);
                    x = sc.nextInt();
                }
                System.out.print(startVisualisation + "Enter coordinate Y - " + endVisualisation);
                y = sc.nextInt();
                while (y > size - 1) {
                    System.out.print(startVisualisation + "Enter coordinate Y " + "(y<=" + size + ") - " + endVisualisation);
                    y = sc.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                sc.next();
            }
        }
        return new int[]{ x, y };
    }
    public void captureCell(Cell cell) {
        if (Objects.equals(cell.representation, "|   ")) {
            cell.representation = this.representation;
        }
    }
}
