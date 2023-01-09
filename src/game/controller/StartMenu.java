package game.controller;

import java.io.File;
import java.util.Scanner;

public class StartMenu {
    private Persistence gameSerialization = new GameSerialization();
    private Game game;
    public void game(){
        Scanner sc = new Scanner(System.in);
        File fGomoku = new File("Serialize/game.controller.Gomoku.ser");
        File fTicTacToe = new File("Serialize/game.controller.TicTacToe.ser");
        boolean condition = true;
        while (condition) {
            try {
                if (fTicTacToe.exists() || fGomoku.exists()) {
                    System.out.println("""
                    \n1. To Play TicTacToe;
                    2. To Play Gomoku;
                    3. To RePlay last TicTactToe;
                    4. To RePlay last Gomoku;
                    5. Quit;
                    """);
                } else {
                    System.out.println("""
                    \n1. To Play TicTacToe;
                    2. To Play Gomoku;
                    3. Quit;
                    """);
                }

                int choose = sc.nextInt(6);
                condition = choose < 1;
                if (condition) {
                    System.out.println("Enter number between 1 2");
                }
                switch (choose) {
                    case 1 -> {
                        System.out.println("\n");
                        System.out.println("Tic - Tac - Toe");
                        game = new TicTacToe();
                        game.play();
                        gameSerialization.create(game);
                    }
                    case 2 -> {
                        game = new Gomoku();
                        game.play();
                        gameSerialization.create(game);
                    }
                    case 3 -> game = gameSerialization.read(TicTacToe.class);
                    case 4 -> game = gameSerialization.read(Gomoku.class);
                    case 5 -> System.out.println("Bye");
                }
            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                sc.next();
            }
        }
    }
}
