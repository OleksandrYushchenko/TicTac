import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicTacToe {
    InteractionUtilisateur interactionUtilisateur; // view
    BoardGame boardGame;
    int size;
    int typeOfGame;
    Boolean end = false;
    public TicTacToe(){
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.size = interactionUtilisateur.getSizeOfGameField();
        this.typeOfGame = interactionUtilisateur.getTypeOfGame();
        this.boardGame = new BoardGame(size, typeOfGame);
    }
    public void playerStep(Player player1, Player player2){
        interactionUtilisateur.getMoveFromPlayer(player1, size, boardGame.cells);
        interactionUtilisateur.view.displayTest(interactionUtilisateur.visualization, test());
        if (!end) {
            interactionUtilisateur.getMoveFromPlayer(player2, size, boardGame.cells);
            interactionUtilisateur.view.displayTest(interactionUtilisateur.visualization, test());
        }
    }
    public void play () {
        interactionUtilisateur.view.displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
        while (!end){
            playerStep(boardGame.playerX, boardGame.playerO);
        }
    }
    public void colorCheckout(String rep, Integer i, String checkout) {
        for (int h = 0; h < size; h++) {
            System.out.print(rep);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                switch (checkout) {
                    case "H":
                        if (h == i) {
                            boardGame.cells[i][j].cellPrint("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "V":
                        if (j == i) {
                            boardGame.cells[h][j].cellPrint("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "D":
                        if (j == h) {
                            boardGame.cells[j][h].cellPrint("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    default:
                        if (j == (size - 1) - h) {
                            boardGame.cells[h][(size - 1) - h].cellPrint("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cellPrint("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                }

            }
        }
    }
    public String testMethod(int i, String version, String representation, String by) {
        List<String> testArray = new ArrayList<>();
        String result = "";
        if (Objects.equals(version, "H")) {
            if (Arrays.stream(boardGame.cells[i]).filter(el -> Objects.equals(el.representation, "| O ")).count() == boardGame.cells.length) {
                end = true;
                colorCheckout(representation, i, "H");
                return "\nPlayer O Win by horizontal!!!";
            }
            if (Arrays.stream(boardGame.cells[i]).filter(el -> Objects.equals(el.representation, "| X ")).count() == boardGame.cells.length) {
                end = true;
                colorCheckout(representation, i, "H");
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
                end = true;
                colorCheckout(representation, i, version);
                result = "\nPlayer O Win by " + by + "!!!";
            }
            if (!testArray.contains("|   ") && !testArray.contains("| O ")) {
                end = true;
                colorCheckout(representation, i, version);
                result = "\nPlayer X Win by " + by + "!!!";
            }
        }
        return result;
    }
    public String test() {
        //initialization testing
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        String result;
        // testing initialization
        for (int i = 0; i < boardGame.cells.length; i++) {
            // horizontal
            result = testMethod(i, "H", representation.toString(), "horizontal");

            // vertical
            if (result.equals("")) {
                result = testMethod(i, "V", representation.toString(), "vertical");
            }
            // diagonal
            if (result.equals("")) {
                result = testMethod(i, "D", representation.toString(), "diagonal \"\\\"");
            }
            // diagonal2
            if (result.equals("")) {
                result = testMethod(i, "D2", representation.toString(), "diagonal \"/\"");
            }
            if (!result.equals("")) {
                return result;
            }
        }
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (Cell[] cell : boardGame.cells) {
            for (Cell value : cell) {
                testNoWin.add(value.cellPrint("test", interactionUtilisateur.visualization.BLACK_BOLD, interactionUtilisateur.visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            end = true;
            interactionUtilisateur.view.displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
            return "\nNo winner!!!";
        }
        interactionUtilisateur.view.displayGameField(boardGame.cells, size, interactionUtilisateur.visualization);
        return "\nMake choose!!!";
    }
}
