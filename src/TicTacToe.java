import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicTacToe {
    InteractionUtilisateur interactionUtilisateur;
    BoardGame boardGame;
    int size;
    int typeOfGame;
    Boolean end = false;
    public TicTacToe(){
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.size = interactionUtilisateur.get_Size_Of_Game_Field();
        this.typeOfGame = interactionUtilisateur.get_Type_Of_Game();
        this.boardGame = new BoardGame(size, typeOfGame);
    }
    public void player_Step(Player player1, Player player2){
        interactionUtilisateur.get_Move_From_Player(player1, size, boardGame.cells);
        System.out.println(interactionUtilisateur.visualization.GREEN_BOLD + test() + interactionUtilisateur.visualization.ANSI_RESET);
        if (!end) {
            interactionUtilisateur.get_Move_From_Player(player2, size, boardGame.cells);
            System.out.println(interactionUtilisateur.visualization.GREEN_BOLD + test() + interactionUtilisateur.visualization.ANSI_RESET);
        }
    }
    public void play () {
        interactionUtilisateur.view.display_Game_Field(boardGame.cells, size, interactionUtilisateur.visualization);
        while (!end){
            player_Step(boardGame.playerX, boardGame.playerO);
        }
    }
    public void color_Checkout(String rep, Integer i, String checkout) {
        for (int h = 0; h < size; h++) {
            System.out.print(rep);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                switch (checkout) {
                    case "H":
                        if (h == i) {
                            boardGame.cells[i][j].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "V":
                        if (j == i) {
                            boardGame.cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "D":
                        if (j == h) {
                            boardGame.cells[j][h].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    default:
                        if (j == (size - 1) - h) {
                            boardGame.cells[h][(size - 1) - h].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            boardGame.cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                }

            }
        }
    }
    public String test_Method(int i, String version, String representation, String by) {
        List<String> testArray = new ArrayList<>();
        String result = "";
        for (int j = 0; j < boardGame.cells[i].length; j++) {
            switch (version) {
                case "V" -> testArray.add(boardGame.cells[i][j].representation);
                case "D" -> testArray.add(boardGame.cells[j][j].representation);
                case "D2" -> testArray.add(boardGame.cells[j][size - 1 - j].representation);
                default -> System.out.println("error!!!");
            }
        }
        if (!testArray.contains("|   ") && !testArray.contains("| X ")) {
            end = true;
            color_Checkout(representation, i, version);
            result = "\nPlayer O Win by " + by + "!!!";
        }
        if (!testArray.contains("|   ") && !testArray.contains("| O ")) {
            end = true;
            color_Checkout(representation, i, version);
            result = "\nPlayer X Win by " + by + "!!!";
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
            if (Arrays.stream(boardGame.cells[i]).filter(el -> el.representation == "| O ").count() == boardGame.cells.length) {
                end = true;
                color_Checkout(representation.toString(), i, "H");
                return "\nPlayer O Win by horizontal!!!";
            }
            if (Arrays.stream(boardGame.cells[i]).filter(el -> el.representation == "| X ").count() == boardGame.cells.length) {
                end = true;
                color_Checkout(representation.toString(), i, "H");
                return "\nPlayer X Win by horizontal!!!";
            }
            // vertical
            result = test_Method(i, "V", representation.toString(), "vertical");
            // diagonal
            if (Objects.equals(result, "")) {
                result = test_Method(i, "D", representation.toString(), "diagonal \"\\\"");
            }
            // diagonal2
            if (result.equals("")) {
                result = test_Method(i, "D2", representation.toString(), "diagonal \"/\"");
            }
            if (!result.equals("")) {
                return result;
            }
        }
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (Cell[] cell : boardGame.cells) {
            for (Cell value : cell) {
                testNoWin.add(value.cell_Print("test", interactionUtilisateur.visualization.BLACK_BOLD, interactionUtilisateur.visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            end = true;
            interactionUtilisateur.view.display_Game_Field(boardGame.cells, size, interactionUtilisateur.visualization);
            return "\nNo winner!!!";
        }
        interactionUtilisateur.view.display_Game_Field(boardGame.cells, size, interactionUtilisateur.visualization);
        return "\nMake choose!!!";
    }
}
