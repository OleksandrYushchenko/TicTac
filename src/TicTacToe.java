import java.util.ArrayList;

public class TicTacToe {
    Visualization visualization;
    InteractionUtilisateur interactionUtilisateur;
    View view;
    int size;
    int typeOfGame;
    Cell[][] cells;
    Player playerX;
    Player playerO;
    Boolean end = false;
    public TicTacToe(){
        this.visualization = new Visualization();
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.view  = new View();
        this.size = interactionUtilisateur.get_Size_Of_Game_Field();
        this.typeOfGame = interactionUtilisateur.get_Type_Of_Game();
        // Instanciate the player
        switch (typeOfGame) {
            case 1:
                this.playerX = new Player("| X ");
                this.playerO = new Player("| O ");
                break;
            case 2:
                this.playerX = new Player("| X ");
                this.playerO = new ArtificialPlayer("| O ");
                break;
            default:
                this.playerX = new ArtificialPlayer("| X ");
                this.playerO = new ArtificialPlayer("| O ");
                break;
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
        System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);

        int[] coordinates = player.getCoordinates(size);
        int x = coordinates[0];
        int y = coordinates[1];
        while (this.cells[x][y].representation == "| X " || this.cells[x][y].representation == "| O "){
            System.out.println(visualization.RED_BOLD + "\nCell is already captured!!!" + visualization.ANSI_RESET);
            System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);
            coordinates = player.getCoordinates(size);
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell( this.cells[x][y]);
    }
    // Show the TicTacToe
    public void player_Step(Player player1, Player player2){
        this.get_Move_From_Player(player1);
        System.out.println(visualization.GREEN_BOLD + test_For_Win() + visualization.ANSI_RESET);
        if (!end) {
            this.get_Move_From_Player(player2);
            System.out.println(visualization.GREEN_BOLD + test_For_Win() + visualization.ANSI_RESET);
        }
    }
    public void play () {
        view.display_Game_Field(this.cells, size);
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
                            cells[i][j].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                        break;
                    case "V":
                        if (j == i) {
                            cells[h][j].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                        break;
                    case "D":
                        if (j == h) {
                            cells[j][h].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                        break;
                    default:
                        if (j == (size - 1) - h) {
                            cells[h][(size - 1) - h].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                }

            }
        }
    }
    public ArrayList<ArrayList<String>> test_Array_Initialization() {
        // initialization test array
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            ArrayList<String> item = new ArrayList<>();
            for (int j = 0; j < cells[i].length; j++) {
                item.add(cells[i][j].cell_Print("test", visualization.BLACK_BOLD, visualization.ANSI_RESET));
            }
            test.add(item);
        }
        return test;
    }
    public String testing_V_D_D2 (ArrayList<ArrayList<String>> test, int i, String version, String representation, String by) {
        ArrayList<String> testArray = new ArrayList<>();
        String result = "";
        for (int j = 0; j < test.get(i).size(); j++) {
            switch (version) {
                case "V":
                    testArray.add(test.get(j).get(i));
                    break;
                case "D":
                    testArray.add(test.get(j).get(j));
                    break;
                case "D2":
                    testArray.add(test.get(j).get((size - 1) - j));
                    break;
                default:
                    System.out.println("error!!!");
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
        ArrayList<ArrayList<String>> test = test_Array_Initialization();
        String representation = "";
        representation += "\n";
        for (int k = 0; k < size ; k++) {
            representation += "----";
        }
        String result = "";
        // testing initialization
        for (int i = 0; i < test.size(); i++) {
            // horizontal
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| X ")) {
                end = true;
                color_Checkout(representation, i, "H");
                return "\nPlayer O Win by horizontal!!!";
            }
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| O ")) {
                end = true;
                color_Checkout(representation, i, "H");
                return "\nPlayer X Win by horizontal!!!";
            }
            // vertical
            result = testing_V_D_D2(test, i, "V", representation, "vertical");
            // diagonal
            if (result == "") {
                result = testing_V_D_D2(test, i, "D", representation, "diagonal \"\\\"");
            }
            // diagonal2
            if (result == "") {
                result = testing_V_D_D2(test, i, "D2", representation, "diagonal \"/\"");
            }
            if (result != "") {
                return result;
            }
        }
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                testNoWin.add(cells[i][j].cell_Print("test", visualization.BLACK_BOLD, visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            end = true;
            view.display_Game_Field(cells, size);
            return "\nNo winner!!!";
        }
        view.display_Game_Field(cells, size);
        return "\nMake choose!!!";
    }
}
