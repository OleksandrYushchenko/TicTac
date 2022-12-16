import java.util.Scanner;
import java.util.WeakHashMap;

public class InteractionUtilisateur {
    Visualization visualization;
    View view = new View();
    public InteractionUtilisateur(){
        this.visualization = new Visualization();
    }
    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        int number = 1;
        while (number < 3) {
            try {
                System.out.print(visualization.RED_BOLD + "Enter size of game field - " + visualization.ANSI_RESET);
                number = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                scanner.next();
            }
            if (number < 3) {
                System.out.println("Enter number >= 3");
            }
        }
        return number;
    }
    public int get_Type_Of_Game(){
        Scanner scanner = new Scanner(System.in);
        view.get_Type_Of_Game_List();
        view.Enter_number_of_players();
        return scanner.nextInt();
    }
    public int[] getCoordinates(Integer size){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                System.out.print(visualization.RED_BOLD + "Enter coordinate X - " + visualization.ANSI_RESET);
                x = sc.nextInt();
                while (x > size - 1) {
                    System.out.print(visualization.RED_BOLD + "Enter coordinate X " + "(x<=" + size + ") - " + visualization.ANSI_RESET);
                    x = sc.nextInt();
                }
                System.out.print(visualization.GREEN_BOLD + "Enter coordinate Y - " + visualization.ANSI_RESET);
                y = sc.nextInt();
                while (y > size - 1) {
                    System.out.print(visualization.GREEN_BOLD + "Enter coordinate Y " + "(y<=" + size + ") - " + visualization.ANSI_RESET);
                    y = sc.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                sc.next();
            }
        }
        return new int[]{ x, y };
    }
}
