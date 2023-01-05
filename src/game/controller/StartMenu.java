package game.controller;

import java.util.Scanner;

public class StartMenu {
    private Game game;

    public void game(){
        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            try {
                System.out.println("""
                \n1. To Play TicTacToe;
                2. To Play Gomoku;
                3. Quit;
                """);
                int choose = sc.nextInt(4);
                condition = choose < 1;
                if (condition) {
                    System.out.println("Enter number between 1 2");
                }
                switch (choose) {
                    case 1 -> {
                        System.out.println("\n");
                        System.out.println("Tic - Tac - Toe");
                        game = new TicTacToe();
                    }
                    case 2 -> game = new Gomoku();
                    case 3 -> System.out.println("Bye");
                }

            } catch (Exception e) {
                System.out.println("Entered data is not valid");
                sc.next();
            }
        }
        if (this.game != null) {
            game.play();
        }
    }
}
