import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    static int size = 3;
    int typeOfGame = getTypeOfGame();
    Cell[][] cells;
    Player playerX;
    Player playerO;
    ArtificialPlayer ArtificialPlayerX;
    ArtificialPlayer ArtificialPlayerO;

    Boolean end = false;

    public TicTacToe(){

        // Instanciate the player
        this.playerX = new Player("| X ");
        this.playerO = new Player("| O ");
        this.ArtificialPlayerX = new ArtificialPlayer("| X ");
        this.ArtificialPlayerO = new ArtificialPlayer("| O ");

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
    public void geMoveFromPlayer(Player player) {
        System.out.println("Player_" + player.representation);

        int[] coordinates = player.getCoordinates();
        int x = coordinates[0];
        int y = coordinates[1];

        player.captureCell( this.cells[x][y] );
    }

    // Show the TicTacToe
    public void display_new(){
        String representation = "";
        for (int i = 0; i < size; i++) {
            representation += "\n";
            for (int k = 0; k < size ; k++) {
                representation += "-----";
            }
            representation += "\n";
            for (int j = 0; j < size; j++) {
                representation += cells[i][j].cell_Print();
            }
        }
        System.out.println(representation);
    }
    public int getTypeOfGame (){
        System.out.println("""
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players");
        return scanner.nextInt();
    }
    public void playerStep (Player player1, Player player2){
        this.geMoveFromPlayer(player1);
        this.display_new();
        System.out.println(test());
        if (!end) {
            this.geMoveFromPlayer(player2);
            this.display_new();
            System.out.println(test());
        }
    }
    public void play () {

        while (!end){
            System.out.println(typeOfGame);
            this.display_new();
            System.out.println(test());
            if (typeOfGame == 1) {
                playerStep(playerX, playerO);
            } else if (typeOfGame == 2) {
                playerStep(playerX, ArtificialPlayerO);
            } else {
                playerStep(ArtificialPlayerX, ArtificialPlayerO);
            }
//            switch (typeOfGame) {
//                case 1:
//                    this.geMoveFromPlayer(playerX);
//                    this.display_new();
//                    System.out.println(test());
//                    this.geMoveFromPlayer(playerO);
//                    this.display_new();
//                    System.out.println(test());
//                case 2:
//                    this.geMoveFromPlayer(playerX);
//                    this.display_new();
//                    System.out.println(test());
//                    this.geMoveFromPlayer(ArtificialPlayerO);
//                    this.display_new();
//                    System.out.println(test());
//                case 3:
//                    this.geMoveFromPlayer(ArtificialPlayerX);
//                    this.display_new();
//                    System.out.println(test());
//                    this.geMoveFromPlayer(ArtificialPlayerO);
//                    this.display_new();
//                    System.out.println(test());
//                default:
//                    System.out.println("Error" + typeOfGame);
//            }
        }
    }

    public String test () {

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
                return "Player O Win by vertical!!!";
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
                testDiagonal2.add(test.get(j).get((TicTacToe.size - 1) - j));
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
