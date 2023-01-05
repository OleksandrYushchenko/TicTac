package game.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicTacToe extends GameController {
    public TicTacToe () {}
    public void play () {
        setGs(GameState.Init);
        while (getGs() != GameState.Quit) {
            switch (getGs()) {
                case Init -> {
                    setSize(getInteractionConsole().askSizeOfGame());
                    setTypeOfGame(getInteractionConsole().askTypeOfGame());
                    getBoardGame().resetBoard(getSize(), getTypeOfGame());
                    displayGameField(getBoardGame().getCells(), getSize(), getInteractionConsole().getVisualization());
                    setGs(GameState.Play);
                }
                case Play -> {
                    playerMove(getBoardGame().getPlayerX(), getSize(), getBoardGame().getCells());
                    getInteractionConsole().getView().displayTest(getInteractionConsole().getVisualization(), testForWin());
                    if (getGs() == GameState.Play) {
                        playerMove(getBoardGame().getPlayerO(), getSize(), getBoardGame().getCells());
                        getInteractionConsole().getView().displayTest(getInteractionConsole().getVisualization(), testForWin());
                    }
                }
                case Win, NoWin -> {
                    getInteractionConsole().getView().displayText(getInteractionConsole().getVisualization(), "\nReplay? y/n\n");
                    setGs(confirmReplay(getGs()));
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
    private void coloredWinnerLine (int i, String checkout) {
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, getSize())));
        for (int h = 0 ; h < getSize() ; h++) {
            System.out.print(representation);
            for (int j = 0 ; j < getSize(); j++) {
                if (j % getSize() == 0) {
                    System.out.print("\n");
                }
                switch (checkout) {
                    case "H" -> {
                        if (h == i) {
                            getBoardGame().getCell(i, j).cellPrint(getInteractionConsole().getVisualization().RED_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        } else {
                            getBoardGame().getCell(h, j).cellPrint(getInteractionConsole().getVisualization().WHITE_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        }
                    }
                    case "V" -> {
                        if (j == i) {
                            getBoardGame().getCell(h, j).cellPrint(getInteractionConsole().getVisualization().RED_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        } else {
                            getBoardGame().getCell(h, j).cellPrint(getInteractionConsole().getVisualization().WHITE_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        }
                    }
                    case "D" -> {
                        if (j == h) {
                            getBoardGame().getCell(j, h).cellPrint(getInteractionConsole().getVisualization().RED_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        } else {
                            getBoardGame().getCell(h, j).cellPrint(getInteractionConsole().getVisualization().WHITE_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        }
                    }
                    default -> {
                        if (j == (getSize() - 1) - h) {
                            getBoardGame().getCell(h, (getSize() - 1) - h).cellPrint(getInteractionConsole().getVisualization().RED_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
                        } else {
                            getBoardGame().getCell(h, j).cellPrint(getInteractionConsole().getVisualization().WHITE_BOLD, getInteractionConsole().getVisualization().ANSI_RESET);
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
        for (int i = 0 ; i < getBoardGame().getCells().length ; i++) {
            List<String> testArrayV = new ArrayList<>();
            List<String> testArrayD = new ArrayList<>();
            List<String> testArrayD2 = new ArrayList<>();
            for (int j = 0 ; j < getBoardGame().getCells().length ; j++) {
                testNoWin.add(getBoardGame().getCell(i, j).getRepresentation());
                testArrayV.add(getBoardGame().getCell(j, i).getRepresentation());
                testArrayD.add(getBoardGame().getCell(j, j).getRepresentation());
                testArrayD2.add(getBoardGame().getCell(j, getSize() - 1 - j).getRepresentation());
            }
            // horizontal
            if (Arrays.stream(getBoardGame().getCells()[i]).filter(el -> Objects.equals(el.getRepresentation(), "| O ")).count() == getBoardGame().getCells().length) {
                coloredWinnerLine(i, "H");
                result = "\nPlayer O Win!!!";
            }
            if (Arrays.stream(getBoardGame().getCells()[i]).filter(el -> Objects.equals(el.getRepresentation(), "| X ")).count() == getBoardGame().getCells().length) {
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
                setGs(GameState.Win);
                return result;
            }
        }
        // No win
        if (!testNoWin.contains("|   ")) {
            setGs(GameState.NoWin);
            displayGameField(getBoardGame().getCells(), getSize(), getInteractionConsole().getVisualization());
            return "\nNo winner!!!";
        }
        displayGameField(getBoardGame().getCells(), getSize(), getInteractionConsole().getVisualization());
        return "\nMake choose!!!";
    }
}
