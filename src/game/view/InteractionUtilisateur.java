package game.view;
import game.model.*;

import java.util.Objects;
import java.util.Scanner;

public class InteractionUtilisateur {
    public Visualization visualization;
    public View view;
    public InteractionUtilisateur(){
        this.visualization = new Visualization();
        this.view = new View();
    }
    /**
     * Method which used for get game field size
     * @return int - field size
     */
    public int getSizeOfGameField(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (number < 3) {
            try {
                view.displayText(visualization, "\nEnter size of game field - ");
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

    /**
     * Method which used for get type of game(Human - Human, Human - Machine, Machine - Machine)
     * @return in - type of game
     */
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

    /**
     * Method which used for capture cell after player step
     * @param player player
     * @param size int - size of game field
     * @param cells game.model.Cell[][] cells - game field
     */
    public void getMoveFromPlayer(Player player, int size, Cell[][] cells) {
        view.displayPlayerTurnName(visualization, player.representation);
        int[] coordinates = player.getCoordinates(size, this);
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")){
            view.displayText(visualization, "\nCell is already captured!!!");
            view.displayPlayerTurnName(visualization, player.representation);
            coordinates = player.getCoordinates(size, this);
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells[x][y]);
    }

    /**
     * Method which used for get coordinates from Human player(Scanner...)
     * @param size int game field size
     * @param interView object instance of game.view.InteractionUtilisateur class
     * @return int[] with 2 values - coordinates of player step
     */
    public int[] getCoordinates(Integer size, InteractionUtilisateur interView){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                interView.view.displayText(interView.visualization, "\nEnter coordinate X - ");
                x = sc.nextInt();
                while (x > size - 1) {
                    interView.view.displayEnterCorrectX(interView.visualization, size);
                    x = sc.nextInt();
                }
                interView.view.displayText(interView.visualization, "\nEnter coordinate Y - ");
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
