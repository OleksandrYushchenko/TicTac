import java.util.Scanner;

public class InteractionUtilisateur {
    Visualization visualization;
    View view = new View();
    public void InteractionUtilisateur(){
        this.visualization = new Visualization();
    }


    // Declaring the color
    // Custom declaration

    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(visualization.RED_BOLD + "Enter size of game field - " + visualization.ANSI_RESET);
        return scanner.nextInt();
    }
    public int get_Type_Of_Game(){
        view.get_Type_Of_Game_List();
        Scanner scanner = new Scanner(System.in);
        view.Enter_number_of_players();
        return scanner.nextInt();
    }
    public int[] getCoordinates(Integer size){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print(visualization.RED_BOLD + "Enter coordinate X - " + visualization.ANSI_RESET);
        int x = sc.nextInt();
        while (x > size) {
            System.out.print(visualization.RED_BOLD + "Enter coordinate X " + "(x<=" + size + ") - " + visualization.ANSI_RESET);
            x = sc.nextInt();
        }
        System.out.print(visualization.GREEN_BOLD + "Enter coordinate Y - " + visualization.ANSI_RESET);
        int y = sc.nextInt();
        while (y > size) {
            System.out.print(visualization.GREEN_BOLD + "Enter coordinate Y " + "(y<=" + size + ") - " + visualization.ANSI_RESET);
            y = sc.nextInt();
        }

        return new int[]{ x, y };
    }
}
