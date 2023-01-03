package game.conroller;

import game.model.BoardGame;
import game.model.Cell;
import game.model.Player;
import game.view.InteractionUtilisateur;
import game.view.Visualization;

import java.util.*;

abstract public class GameController {
    private final InteractionUtilisateur interactionUtilisateur; // view
    private final BoardGame boardGame;
    private int size;
    private int typeOfGame;
    public GameState gs;
    public GameController(){
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.boardGame = new BoardGame(size, typeOfGame);
    }
    /**
     * Display game field
     * @param cells game field
     * @param size size of game field
     * @param visualization styles for System out print(ln)
     */
    public void displayGameField(Cell[][] cells, int size, Visualization visualization){
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int i = 0; i < size; i++) {
            System.out.print(representation);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                cells[i][j].cellPrint("Win", visualization.WHITE_BOLD, visualization.ANSI_RESET);
            }
        }
    }
    /**
     * Method which used for capture cell after player step
     * @param player player
     * @param size int - size of game field
     * @param cells game.model.Cell[][] cells - game field
     */
    public void playerMove(Player player, int size, Cell[][] cells) {
        interactionUtilisateur.view.displayPlayerTurnName(interactionUtilisateur.visualization, player.representation);
        int[] coordinates;
        if (!player.isArtificial()) {
            coordinates = player.playerMove(interactionUtilisateur.askCoordinates(size), size);
        } else {
            coordinates = player.playerMove(new int[2], size);
        }
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")){
            interactionUtilisateur.view.displayText(interactionUtilisateur.visualization, "\nCell is already captured!!!");
            interactionUtilisateur.view.displayPlayerTurnName(interactionUtilisateur.visualization, player.representation);
            if (!player.isArtificial()) {
                coordinates = player.playerMove(interactionUtilisateur.askCoordinates(size), size);
            } else {
                coordinates = player.playerMove(new int[2], size);
            }
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells, x, y);
    }
    enum GameState {
        Init,
        Play,
        Win,
        NoWin,
        Quit
    }
    enum ConfirmReplay{
        Y,
        N
    }
    private GameState confirmReplay(GameState gs) {
        Scanner sc = new Scanner(System.in);
        String line;
        try {
            line = sc.nextLine();
            ConfirmReplay c = ConfirmReplay.valueOf(line.toUpperCase());
            switch (c) {
                case Y -> gs = GameState.Init;
                case N -> gs = GameState.Quit;
            }
        } catch (Exception e) {
            interactionUtilisateur.view.displayText(interactionUtilisateur.visualization, "Entered Data invalid. Enter y/n");
        }
        return gs;
    }
    /**
     * Call method displayGameField & while not end of game calling stepOfGame
     */
    public void play () {
        gs = GameState.Init;
        while (gs != GameState.Quit){
            switch (gs) {
                case Init -> {
                    size = interactionUtilisateur.askSizeOfGame();
                    typeOfGame = interactionUtilisateur.askTypeOfGame();
                    boardGame.resetBoard(size, typeOfGame);
                    displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
                    gs = GameState.Play;
                }
                case Play -> {
                        playerMove(boardGame.playerX, size, boardGame.cells);
                        interactionUtilisateur.view.displayTest(interactionUtilisateur.visualization, testForWin());
                    if (gs == GameState.Play) {
                        playerMove(boardGame.playerO, size, boardGame.cells);
                        interactionUtilisateur.view.displayTest(interactionUtilisateur.visualization, testForWin());
                    }
                }
                case Win, NoWin -> {
                    interactionUtilisateur.view.displayText(interactionUtilisateur.visualization, "\nReplay? y/n\n");
                    gs = confirmReplay(gs);
                }
                default -> System.out.println("Error");
            }
        }
        System.out.println("Bye!!");
    }
    /**
     *
     * @param i int parameter passing as step of iteration from loop where that method will be called
     * @param checkout String - type of winner direction
     */
    private void colorCheckout(Integer i, String checkout) {
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int h = 0; h < size; h++) {
            System.out.print(representation);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                switch (checkout) {
                    case "H" -> {
                        if (h == i) {
                            boardGame.cells[i][j].cellPrint("Win", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("Win", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    case "V" -> {
                        if (j == i) {
                            boardGame.cells[h][j].cellPrint("Win", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("Win", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    case "D" -> {
                        if (j == h) {
                            boardGame.cells[j][h].cellPrint("Win", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("Win", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    default -> {
                        if (j == (size - 1) - h) {
                            boardGame.cells[h][(size - 1) - h].cellPrint("Win", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("Win", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                }

            }
        }
    }

    /**
     * test method which includes all action to check board on Win
     * @param i int  - step of iteration from loop where this method will be called
     * @param version String - version of testing H - horizontal, V - vertical...
     * @param by String - text which will be placed inside Winner result line
     * @return String - Winner result line
     */
    private String testMethod(int i, String version, String by) {
        List<String> testArray = new ArrayList<>();
        String result = "";
        if (Objects.equals(version, "H")) {
            if (Arrays.stream(boardGame.cells[i]).filter(el -> Objects.equals(el.representation, "| O ")).count() == boardGame.cells.length) {
                colorCheckout(i, "H");
                return "\nPlayer O Win by horizontal!!!";
            }
            if (Arrays.stream(boardGame.cells[i]).filter(el -> Objects.equals(el.representation, "| X ")).count() == boardGame.cells.length) {
                colorCheckout(i, "H");
                return "\nPlayer X Win by horizontal!!!";
            }
        } else {
            for (int j = 0; j < boardGame.cells.length; j++) {
                switch (version) {
                    case "V" -> testArray.add(boardGame.cells[j][i].representation);
                    case "D" -> testArray.add(boardGame.cells[j][j].representation);
                    case "D2" -> testArray.add(boardGame.cells[j][size - 1 - j].representation);
                    default -> System.out.println("error!!!");
                }
            }
            if (!testArray.contains("|   ") && !testArray.contains("| X ")) {
                colorCheckout(i, version);
                result = "\nPlayer O Win by " + by + "!!!";
            }
            if (!testArray.contains("|   ") && !testArray.contains("| O ")) {
                colorCheckout(i, version);
                result = "\nPlayer X Win by " + by + "!!!";
            }
        }
        return result;
    }
    /**
     * Method which call testMethod while there is no Winner or Game finished
     * @return result testMethod || "No winner!!!" || "Make choose!!!"
     */
    private String testForWin() {
        String result;
        // testing initialization
        for (int i = 0; i < boardGame.cells.length; i++) {
            // horizontal
            result = testMethod(i, "H", "horizontal");

            // vertical
            if (result.equals("")) {
                result = testMethod(i, "V", "vertical");
            }
            // diagonal
            if (result.equals("")) {
                result = testMethod(i, "D", "diagonal \"\\\"");
            }
            // diagonal2
            if (result.equals("")) {
                result = testMethod(i, "D2", "diagonal \"/\"");
            }
            if (!result.equals("")) {
                gs = GameState.Win;
                return result;
            }
        }
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (Cell[] cell : boardGame.cells) {
            for (Cell value : cell) {
                testNoWin.add(value.cellPrint("noWin", interactionUtilisateur.visualization.BLACK_BOLD, interactionUtilisateur.visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            gs = GameState.NoWin;
            displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
            return "\nNo winner!!!";
        }
        displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
        return "\nMake choose!!!";
    }
}
