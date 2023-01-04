package game.controller;

import java.util.Scanner;

public class StartMenu {
    private Game game;

    public void game(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        \n1. To Play TicTacToe;
        2. To Play Gomoku;
        """);
        int choose = sc.nextInt();
        System.out.println("\n");
        System.out.println("Tic - Tac - Toe");
        switch (choose) {
            case 1 -> {
                game = new TicTacToe();
                game.play();
            }
            case 2 -> {
                game = new Gomoku();
                game.play();
            }
        }
    }
}
