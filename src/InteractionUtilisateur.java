import java.util.Objects;
import java.util.Scanner;

public class InteractionUtilisateur {
    Visualization visualization;
    View view;
    public InteractionUtilisateur(){
        this.visualization = new Visualization();
        this.view = new View();
    }
    public int getSizeOfGameField(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (number < 3) {
            try {
                view.displayText(visualization, "Enter size of game field - ");
                number = scanner.nextInt();
                if (number < 3) {
                    view.displayText(visualization, "Enter number >= 3");
                }
            } catch (Exception e) {
                view.displayText(visualization,"Entered data is not valid");
                scanner.next();
            }
        }
        return number;
    }
    public int getTypeOfGame(){
        Scanner scanner = new Scanner(System.in);
        int number = 4;
        boolean condition = true;
        while (condition) {
            view.getTypeOfGameList(visualization);
            try {
                System.out.print(visualization.RED_BOLD + "Enter type of game - " + visualization.ANSI_RESET);
                number = scanner.nextInt();
                condition = number > 3 || number < 1;
                if (condition) {
                    view.displayText(visualization,"Enter number between 1 2 3");
                }
            } catch (Exception e) {
                view.displayText(visualization, "Entered data is not valid");
                scanner.next();
            }
        }
        return number;
    }
    public void getMoveFromPlayer(Player player, int size, Cell[][] cells) {
        view.displayPlayerTurnName(visualization, player);
        int[] coordinates = player.getCoordinates(size, this);
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")){
            view.displayText(visualization, "\nCell is already captured!!!");
            view.displayPlayerTurnName(visualization, player);
            coordinates = player.getCoordinates(size, this);
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells[x][y]);
    }
    public int[] getCoordinates(Integer size, InteractionUtilisateur interView){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                interView.view.displayText(interView.visualization, "Enter coordinate X - ");
                x = sc.nextInt();
                while (x > size - 1) {
                    interView.view.displayEnterCorrectX(interView.visualization, size);
                    x = sc.nextInt();
                }
                interView.view.displayText(interView.visualization, "Enter coordinate O - ");
                y = sc.nextInt();
                while (y > size - 1) {
                    interView.view.displayEnterCorrectY(interView.visualization, size);
                    y = sc.nextInt();
                }
            } catch (Exception e) {
                view.displayText(visualization, "Entered data is not valid");
                sc.next();
            }
        }
        return new int[]{ x, y };
    }
}
