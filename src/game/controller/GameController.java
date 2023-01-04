package game.controller;

import game.model.BoardGame;
import game.model.Cell;
import game.model.Player;
import game.view.InteractionUtilisateur;
import game.view.Visualization;

import java.util.*;

abstract class GameController implements  Game{
    public final InteractionUtilisateur interactionUtilisateur;
    public final BoardGame boardGame;
    public int size;
    public int typeOfGame;
    public GameState gs;

    public GameController () {
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.boardGame = new BoardGame(size, typeOfGame);
    }
    enum GameState {
        Init,
        Play,
        Win,
        NoWin,
        Quit
    }
    enum ConfirmReplay {
        Y,
        N
    }
    public GameState confirmReplay (GameState gs) {
        Scanner sc = new Scanner(System.in);
        String line;
        try {
            line = sc.nextLine();
            ConfirmReplay c = ConfirmReplay.valueOf(line.toUpperCase());
            switch (c) {
                case Y -> gs = GameState.Init;
                case N -> gs = GameState.Quit;
            }
        } catch (Exception e) {
            interactionUtilisateur.view.displayText(interactionUtilisateur.visualization, "Entered Data invalid. Enter y/n");
        }
        return gs;
    }
    /**
     * Display game field
     * @param cells         game field
     * @param size          size of game field
     * @param visualization styles for System out print(ln)
     */
    public void displayGameField (Cell[][] cells, int size, Visualization visualization) {
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int i = 0 ; i < size ; i++) {
            System.out.print(representation);
            for (int j = 0 ; j < size ; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                cells[i][j].cellPrint(visualization.WHITE_BOLD, visualization.ANSI_RESET);
            }
        }
    }
    /**
     * Method which used for capture cell after player step
     * @param player player
     * @param size   int - size of game field
     * @param cells  game.model.Cell[][] cells - game field
     */
    public void playerMove (Player player, int size, Cell[][] cells) {
        interactionUtilisateur.view.displayPlayerTurnName(interactionUtilisateur.visualization, player.representation);
        int[] coordinates;
        if (!player.isArtificial()) {
            coordinates = player.playerMove(interactionUtilisateur.askCoordinates(size), size);
        } else {
            coordinates = player.playerMove(new int[2], size);
        }
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].representation, "| X ") || Objects.equals(cells[x][y].representation, "| O ")) {
            interactionUtilisateur.view.displayText(interactionUtilisateur.visualization, "\nCell is already captured!!!");
            interactionUtilisateur.view.displayPlayerTurnName(interactionUtilisateur.visualization, player.representation);
            if (!player.isArtificial()) {
                coordinates = player.playerMove(interactionUtilisateur.askCoordinates(size), size);
            } else {
                coordinates = player.playerMove(new int[2], size);
            }
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells, x, y);
    }
}
