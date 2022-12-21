import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicTacToe {
    InteractionUtilisateur interactionUtilisateur;
    int size;
    int typeOfGame;
    Cell[][] cells;
    Player playerX;
    Player playerO;
    Boolean end = false;
    public TicTacToe(){
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.size = interactionUtilisateur.get_Size_Of_Game_Field();
        this.typeOfGame = interactionUtilisateur.get_Type_Of_Game();
        // Instanciate the player
        switch (typeOfGame) {
            case 1 -> {
                this.playerX = new Player("| X ");
                this.playerO = new Player("| O ");
            }
            case 2 -> {
                this.playerX = new Player("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
            default -> {
                this.playerX = new ArtificialPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
            }
        }
        // Instanciate the cells
        this.cells = new Cell[size][size];
        // Populate the cells (initialization)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
    // Ask for coordinates and capture the cell if possible
    public void get_Move_From_Player(Player player) {
        System.out.println(interactionUtilisateur.visualization.BLUE_UNDERLINED + "\nPlayer_" + interactionUtilisateur.visualization.ANSI_RESET + player.representation);
        int[] coordinates = player.getCoordinates(size, interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(this.cells[x][y].representation, "| X ") || Objects.equals(this.cells[x][y].representation, "| O ")){
            System.out.println(interactionUtilisateur.visualization.RED_BOLD + "\nCell is already captured!!!" + interactionUtilisateur.visualization.ANSI_RESET);
            System.out.println(interactionUtilisateur.visualization.BLUE_UNDERLINED + "\nPlayer_" + interactionUtilisateur.visualization.ANSI_RESET + player.representation);
            coordinates = player.getCoordinates(size, interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell( this.cells[x][y]);
    }
    // Show the TicTacToe
    public void player_Step(Player player1, Player player2){
        this.get_Move_From_Player(player1);
        System.out.println(interactionUtilisateur.visualization.GREEN_BOLD + test_For_Win() + interactionUtilisateur.visualization.ANSI_RESET);
        if (!end) {
            this.get_Move_From_Player(player2);
            System.out.println(interactionUtilisateur.visualization.GREEN_BOLD + test_For_Win() + interactionUtilisateur.visualization.ANSI_RESET);
        }
    }
    public void play () {
        interactionUtilisateur.view.display_Game_Field(cells, size, interactionUtilisateur.visualization);
        while (!end){
            player_Step(playerX, playerO);
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
                            cells[i][j].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "V":
                        if (j == i) {
                            cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    case "D":
                        if (j == h) {
                            cells[j][h].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                        break;
                    default:
                        if (j == (size - 1) - h) {
                            cells[h][(size - 1) - h].cell_Print("printTest", interactionUtilisateur.visualization.RED_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", interactionUtilisateur.visualization.WHITE_BOLD, interactionUtilisateur.visualization.ANSI_RESET);
                        }
                }

            }
        }
    }
    public List<List<String>> test_Array_Initialization() {
        // initialization test array
        List<List<String>> test = new ArrayList<>();
        for (Cell[] cell : cells) {
            List<String> item = new ArrayList<>();
            for (Cell value : cell) {
                item.add(value.cell_Print("test", interactionUtilisateur.visualization.BLACK_BOLD, interactionUtilisateur.visualization.ANSI_RESET));
            }
            test.add(item);
        }
        return test;
    }
    public String testing_V_D_D2 (List<List<String>> test, int i, String version, String representation, String by) {
        List<String> testArray = new ArrayList<>();
        String result = "";
        for (int j = 0; j < test.get(i).size(); j++) {
            switch (version) {
                case "V" -> testArray.add(test.get(j).get(i));
                case "D" -> testArray.add(test.get(j).get(j));
                case "D2" -> testArray.add(test.get(j).get((size - 1) - j));
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
    public String test_For_Win() {
        //initialization testing
        List<List<String>> test = test_Array_Initialization();
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        String result;
        // testing initialization
        for (int i = 0; i < test.size(); i++) {
            // horizontal
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| X ")) {
                end = true;
                color_Checkout(representation.toString(), i, "H");
                return "\nPlayer O Win by horizontal!!!";
            }
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| O ")) {
                end = true;
                color_Checkout(representation.toString(), i, "H");
                return "\nPlayer X Win by horizontal!!!";
            }
            // vertical
            result = testing_V_D_D2(test, i, "V", representation.toString(), "vertical");
            // diagonal
            if (Objects.equals(result, "")) {
                result = testing_V_D_D2(test, i, "D", representation.toString(), "diagonal \"\\\"");
            }
            // diagonal2
            if (result.equals("")) {
                result = testing_V_D_D2(test, i, "D2", representation.toString(), "diagonal \"/\"");
            }
            if (!result.equals("")) {
                return result;
            }
        }
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                testNoWin.add(value.cell_Print("test", interactionUtilisateur.visualization.BLACK_BOLD, interactionUtilisateur.visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            end = true;
            interactionUtilisateur.view.display_Game_Field(cells, size, interactionUtilisateur.visualization);
            return "\nNo winner!!!";
        }
        interactionUtilisateur.view.display_Game_Field(cells, size, interactionUtilisateur.visualization);
        return "\nMake choose!!!";
    }
}
