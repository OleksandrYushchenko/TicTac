import java.util.ArrayList;

public class TicTacToe {
    InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur();
    View view = new View();
    static int size = 3;
    int typeOfGame = interactionUtilisateur.get_Type_Of_Game();
    Cell[][] cells;
    Player playerX;
    Player playerO;
    Boolean end = false;
    public TicTacToe(){
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
        System.out.println("Player_" + player.representation);

        int[] coordinates = player.getCoordinates();
        int x = coordinates[0];
        int y = coordinates[1];
        player.captureCell( this.cells[x][y] );
    }
    // Show the TicTacToe
    public void player_Step(Player player1, Player player2){
        this.get_Move_From_Player(player1);
        view.display_Game_Field(this.cells, size);
        System.out.println(test_For_Win());
        if (!end) {
            this.get_Move_From_Player(player2);
            view.display_Game_Field(this.cells, size);
            System.out.println(test_For_Win());
        }
    }
    public void play () {
        view.display_Game_Field(this.cells, size);
        while (!end){
            player_Step(playerX, playerO);
        }
    }
    public String test_For_Win() {

        // initialization test array
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            ArrayList<String> item = new ArrayList<>();
            for (int j = 0; j < cells[i].length; j++) {
                item.add(cells[i][j].cell_Print());
            }
            test.add(item);
        }


        //initialization testing
        for (int i = 0; i < test.size(); i++) {
            // horizontal
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| X ")) {
                end = true;
                return "Player O Win by horizontal!!!";
            }
            if (!test.get(i).contains("|   ") && !test.get(i).contains("| O ")) {
                end = true;
                return "Player X Win by horizontal!!!";
            }
            // vertical

            ArrayList<String> testVertical = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testVertical.add(test.get(j).get(i));
            }
            if (!testVertical.contains("|   ") && !testVertical.contains("| X ")) {
                end = true;
                return "Player O Win by !!!";
            }
            if (!testVertical.contains("|   ") && !testVertical.contains("| O ")) {
                end = true;
                return "Player X Win by vertical!!!";
            }

            // diagonal

            ArrayList<String> testDiagonal = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testDiagonal.add(test.get(j).get(j));
            }
            if (!testDiagonal.contains("|   ") && !testDiagonal.contains("| X ")) {
                end = true;
                return "Player O Win by diagonal \"\\\"!!!";
            }
            if (!testDiagonal.contains("|   ") && !testDiagonal.contains("| O ")) {
                end = true;
                return "Player X Win by diagonal \"\\\" !!!";
            }

            // diagonal2

            ArrayList<String> testDiagonal2 = new ArrayList<>();
            for (int j = 0; j < test.get(i).size(); j++) {
                testDiagonal2.add(test.get(j).get((size - 1) - j));
            }
            if (!testDiagonal2.contains("|   ") && !testDiagonal2.contains("| X ")) {
                end = true;
                return "Player O Win by diagonal \"/\" !!!";
            }
            if (!testDiagonal2.contains("|   ") && !testDiagonal2.contains("| O ")) {
                end = true;
                return "Player X Win by diagonal \"/\" !!!";
            }
        }
        return "Make choose!!!";
    }
}
