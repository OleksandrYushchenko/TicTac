package game.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicTacToe extends GameController {
    public TicTacToe () {}
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
     * @return result testMethod || "No winner!!!" || "Make choose!!!"
     */
    public String testForWin () {
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
