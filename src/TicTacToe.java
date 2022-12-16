import java.util.ArrayList;

public class TicTacToe {
    Visualization visualization;
    InteractionUtilisateur interactionUtilisateur;
    View view = new View();
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
        this.visualization = new Visualization();
    }
    // Ask for coordinates and capture the cell if possible
    public void get_Move_From_Player(Player player) {
        System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);

        int[] coordinates = player.getCoordinates(size);
        int x = coordinates[0];
        int y = coordinates[1];
        player.captureCell( this.cells[x][y], size );
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
    public void colorHorizontal (String rep, Integer i) {
        for (int h = 0; h < size; h++) {
            System.out.print(rep);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                if (h == i) {
                    cells[i][j].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                } else {
                    cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                }
            }
        }
    }
    public void colorVertical (String rep, Integer i) {
        for (int h = 0; h < size; h++) {
            System.out.print(rep);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                if (j == i) {
                    cells[h][j].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                } else {
                    cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                }
            }
        }
    }
    public void colorDiagonal (String rep) {
        for (int h = 0; h < size; h++) {
            System.out.print(rep);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                if (j == h) {
                    cells[j][h].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                } else {
                    cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                }
            }
        }
    }
    public String test_For_Win() {
        // initialization test array
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            ArrayList<String> item = new ArrayList<>();
            for (int j = 0; j < cells[i].length; j++) {
                item.add(cells[i][j].cell_Print("test", visualization.BLACK_BOLD, visualization.ANSI_RESET));
            }
            test.add(item);
        }
        //initialization testing

        String representation = "";
        representation += "\n";
        for (int k = 0; k < size ; k++) {
            representation += "----";
        }
        for (int i = 0; i < test.size(); i++) {
            // horizontal
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| X ")) {
                end = true;
                colorHorizontal(representation, i);
                return "\nPlayer O Win by horizontal!!!";
            }
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| O ")) {
                end = true;
                colorHorizontal(representation, i);
                return "\nPlayer X Win by horizontal!!!";
            }
            // vertical
            ArrayList<String> testVertical = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testVertical.add(test.get(j).get(i));
            }
            if (!testVertical.contains("|   ") && !testVertical.contains("| X ")) {
                end = true;
                colorVertical(representation, i);
                return "\nPlayer O Win by vertical!!!";
            }
            if (!testVertical.contains("|   ") && !testVertical.contains("| O ")) {
                end = true;
                colorVertical(representation, i);
                return "\nPlayer X Win by vertical!!!";
            }
            // diagonal
            ArrayList<String> testDiagonal = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testDiagonal.add(test.get(j).get(j));
            }
            if (!testDiagonal.contains("|   ") && !testDiagonal.contains("| X ")) {
                end = true;
                colorDiagonal(representation);
                return "\nPlayer O Win by diagonal \"\\\"!!!";
            }
            if (!testDiagonal.contains("|   ") && !testDiagonal.contains("| O ")) {
                end = true;
                colorDiagonal(representation);
                return "\nPlayer X Win by diagonal \"\\\" !!!";
            }
            // diagonal2
            ArrayList<String> testDiagonal2 = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testDiagonal2.add(test.get(j).get((size - 1) - j));
            }
            if (!testDiagonal2.contains("|   ") && !testDiagonal2.contains("| X ")) {
                end = true;
                for (int h = 0; h < size; h++) {
                    System.out.print(representation);
                    for (int j = 0; j < size; j++) {
                        if (j % size == 0) {
                            System.out.print("\n");
                        }
                        if (j == (size - 1) - h) {
                            cells[h][(size - 1) - h].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                    }
                }
                return "\nPlayer O Win by diagonal \"/\" !!!";
            }
            if (!testDiagonal2.contains("|   ") && !testDiagonal2.contains("| O ")) {
                end = true;
                for (int h = 0; h < size; h++) {
                    System.out.print(representation);
                    for (int j = 0; j < size; j++) {
                        if (j % size == 0) {
                            System.out.print("\n");
                        }
                        if (j == (size - 1) - h) {
                            cells[h][(size - 1) - h].cell_Print("printTest", visualization.RED_BOLD, visualization.ANSI_RESET);
                        } else {
                            cells[h][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
                        }
                    }
                }
                return "\nPlayer X Win by diagonal \"/\" !!!";
            }

        }
        view.display_Game_Field(this.cells, size);
        // No win
        ArrayList<String> testNoWin = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                testNoWin.add(cells[i][j].cell_Print("test", visualization.BLACK_BOLD, visualization.ANSI_RESET));
            }
        }
        if (!testNoWin.contains("|   ")) {
            end = true;
            return "\nNo winner!!!";
        }
        return "\nMake choose!!!";
    }
}
