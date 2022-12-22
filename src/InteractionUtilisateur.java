import java.util.Objects;
import java.util.Scanner;

public class InteractionUtilisateur {
    Visualization visualization;
    View view;
    public InteractionUtilisateur(){
        this.visualization = new Visualization();
        this.view = new View();
    }
    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (number < 3) {
            try {
                System.out.print(visualization.RED_BOLD + "Enter size of game field - " + visualization.ANSI_RESET);
                number = scanner.nextInt();
                if (number < 3) {
                    System.out.println("Enter number >= 3");
                }
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                scanner.next();
            }

        }
        return number;
    }
    public int get_Type_Of_Game(){
        Scanner scanner = new Scanner(System.in);
        int number = 4;
        boolean condition = true;
        while (condition) {

            view.get_Type_Of_Game_List(visualization);
            try {
                System.out.print(visualization.RED_BOLD + "Enter type of game - " + visualization.ANSI_RESET);
                number = scanner.nextInt();
                condition = number > 3 || number < 1;
                if (condition) {
                    System.out.println("Enter number between 1 2 3");
                }
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                scanner.next();
            }
        }
        return number;
    }
    public void get_Move_From_Player(Player player, int size, Cell cells[][]) {
        System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);
        int[] coordinates = player.getCoordinates(size, this);
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")){
            System.out.println(visualization.RED_BOLD + "\nCell is already captured!!!" + visualization.ANSI_RESET);
            System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);
            coordinates = player.getCoordinates(size, this);
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells[x][y]);
    }
    public int[] get_Coordinates(Integer size, InteractionUtilisateur interView){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                interView.view.display_Enter_X(interView.visualization);
                x = sc.nextInt();
                while (x > size - 1) {
                    interView.view.display_Enter_correct_X(interView.visualization, size);
                    x = sc.nextInt();
                }
                interView.view.display_Enter_Y(interView.visualization);
                y = sc.nextInt();
                while (y > size - 1) {
                    interView.view.display_Enter_correct_Y(interView.visualization, size);
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
