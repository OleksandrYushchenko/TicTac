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

    public GameController () {
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.boardGame = new BoardGame(size, typeOfGame);
    }

    /**
     * Display game field
     *
     * @param cells         game field
     * @param size          size of game field
     * @param visualization styles for System out print(ln)
     */
    public void displayGameField (Cell[][] cells, int size, Visualization visualization) {
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int i = 0 ; i < size ; i++) {
            System.out.print(representation);
            for (int j = 0 ; j < size ; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                cells[i][j].cellPrint(visualization.WHITE_BOLD, visualization.ANSI_RESET);
            }
        }
    }

    /**
     * Method which used for capture cell after player step
     *
     * @param player player
     * @param size   int - size of game field
     * @param cells  game.model.Cell[][] cells - game field
     */
    public void playerMove (Player player, int size, Cell[][] cells) {
        interactionUtilisateur.view.displayPlayerTurnName(interactionUtilisateur.visualization, player.representation);
        int[] coordinates;
        if (!player.isArtificial()) {
            coordinates = player.playerMove(interactionUtilisateur.askCoordinates(size), size);
        } else {
            coordinates = player.playerMove(new int[2], size);
        }
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")) {
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

    enum ConfirmReplay {
        Y,
        N
    }

    private GameState confirmReplay (GameState gs) {
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
        while (gs != GameState.Quit) {
            switch (gs) {
                case Init -> {
                    size = interactionUtilisateur.askSizeOfGame();
                    typeOfGame = interactionUtilisateur.askTypeOfGame();
                    boardGame.resetBoard(size, typeOfGame);
                    displayGameField(boardGame.getCells(), size, interactionUtilisateur.visualization);
                    gs = GameState.Play;
                }
                case Play -> {
                    playerMove(boardGame.playerX, size, boardGame.getCells());
                    interactionUtilisateur.view.displayTest(interactionUtilisateur.visualization, testForWin());
                    if (gs == GameState.Play) {
                        playerMove(boardGame.playerO, size, boardGame.getCells());
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
     * @param i        int parameter passing as step of iteration from loop where that method will be called
     * @param checkout String - type of winner direction
     */
    private void coloredWinnerLine (Integer i, String checkout) {
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int h = 0 ; h < size ; h++) {
            System.out.print(representation);
            for (int j = 0 ; j < size ; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                switch (checkout) {
                    case "H" -> {
                        if (h == i) {
                            boardGame.getCell(i, j).cellPrint(interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.getCell(h, j).cellPrint(interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    case "V" -> {
                        if (j == i) {
                            boardGame.getCell(h, j).cellPrint(interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.getCell(h, j).cellPrint(interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    case "D" -> {
                        if (j == h) {
                            boardGame.getCell(j, h).cellPrint(interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.getCell(h, j).cellPrint(interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                    default -> {
                        if (j == (size - 1) - h) {
                            boardGame.getCell(h, (size - 1) - h).cellPrint(interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.getCell(h, j).cellPrint(interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                    }
                }
            }
        }
    }
    private String startTestingTestArray (List<String> testArray, int i, String V, String result) {
        if (!testArray.contains("|   ") && !testArray.contains("| X ")) {
            coloredWinnerLine(i, V);
            result = "\nPlayer O Win!!!";
        }
        if (!testArray.contains("|   ") && !testArray.contains("| O ")) {
            coloredWinnerLine(i, V);
            result = "\nPlayer X Win!!!";
        }
        return result;
    }
    /**
     * test method which includes all action to check board on Win
     *
     * @return result testMethod || "No winner!!!" || "Make choose!!!"
     */
    private String testForWin () {
        List<String> testNoWin = new ArrayList<>();
        String result = "";
        for (int i = 0 ; i < boardGame.getCells().length ; i++) {
            List<String> testArrayV = new ArrayList<>();
            List<String> testArrayD = new ArrayList<>();
            List<String> testArrayD2 = new ArrayList<>();
            for (int j = 0 ; j < boardGame.getCells().length ; j++) {
                testNoWin.add(boardGame.getCell(i, j).representation);
                testArrayV.add(boardGame.getCell(j, i).representation);
                testArrayD.add(boardGame.getCell(j, j).representation);
                testArrayD2.add(boardGame.getCell(j, size - 1 - j).representation);
            }
            // horizontal
            if (Arrays.stream(boardGame.getCells()[i]).filter(el -> Objects.equals(el.representation, "| O ")).count() == boardGame.getCells().length) {
                coloredWinnerLine(i, "H");
                result = "\nPlayer O Win!!!";
            }
            if (Arrays.stream(boardGame.getCells()[i]).filter(el -> Objects.equals(el.representation, "| X ")).count() == boardGame.getCells().length) {
                coloredWinnerLine(i, "H");
                result = "\nPlayer X Win!!!";
            }
            // vertical
            result = startTestingTestArray(testArrayV, i, "V", result);
            // diagonal
            result = startTestingTestArray(testArrayD, i, "D", result);
            // diagonal2
            result = startTestingTestArray(testArrayD2, i, "D2", result);
            if (!result.equals("")) {
                gs = GameState.Win;
                return result;
            }
        }
        // No win
        if (!testNoWin.contains("|   ")) {
            gs = GameState.NoWin;
            displayGameField(boardGame.getCells(), size, interactionUtilisateur.visualization);
            return "\nNo winner!!!";
        }
        displayGameField(boardGame.getCells(), size, interactionUtilisateur.visualization);
        return "\nMake choose!!!";
    }
}
