package game.view;

import java.util.Scanner;

public class InteractionConsole implements Interaction {
    private final Visualization visualization;
    private final View view;
    private final Scanner sc;
    public InteractionConsole (){
        this.visualization = new Visualization();
        this.view = new View();
        this.sc = new Scanner(System.in);
    }
    public Visualization getVisualization () {
        return visualization;
    }
    public View getView () {
        return view;
    }
    /**
     * Method which used for get game field size
     *
     * @return int - field size
     */
    public int askSizeOfGame () {
        int number = 0;
        while (number < 3) {
            try {
                view.displayText(visualization, "\nEnter size of game field - ");
                number = sc.nextInt();
                if (number < 3) {
                    view.displayText(visualization, "Enter number >= 3");
                }
            } catch (Exception e) {
                view.displayText(visualization, "Entered data is not valid");
                sc.next();
            }
        }
        return number;
    }
    /**
     * Method which used for get type of game(Human - Human, Human - Machine, Machine - Machine)
     *
     * @return in - type of game
     */
    public int askTypeOfGame () {
        int number = 4;
        boolean condition = true;
        while (condition) {
            view.getTypeOfGameList(visualization);
            try {
                System.out.print(visualization.RED_BOLD + "Enter type of game - " + visualization.ANSI_RESET);
                number = sc.nextInt();
                condition = number > 3 || number < 1;
                if (condition) {
                    view.displayText(visualization, "Enter number between 1 2 3");
                }
            } catch (Exception e) {
                view.displayText(visualization, "Entered data is not valid");
                sc.next();
            }
        }
        return number;
    }
    /**
     * Method which used for get coordinates from Human player(Scanner...)
     *
     * @param size int game field size
     * @return int[] with 2 values - coordinates of player step
     */
    public int[] askCoordinates (int size) {
        int x = size + 1;
        int y = size + 1;
        while (x > size - 1 && y > size - 1) {
            try {
                view.displayText(visualization, "\nEnter coordinate X - ");
                x = sc.nextInt();
                while (x > size - 1) {
                    view.displayEnterCorrectX(visualization, size);
                    x = sc.nextInt();
                }
                view.displayText(visualization, "\nEnter coordinate Y - ");
                y = sc.nextInt();
                while (y > size - 1) {
                    view.displayEnterCorrectY(visualization, size);
                    y = sc.nextInt();
                }
            } catch (Exception e) {
                view.displayText(visualization, "Entered data is not valid");
                sc.next();
            }
        }
        return new int[]{x, y};
    }
}
