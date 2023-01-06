package game.controller;

import game.model.BoardGame;
import game.model.BoardGameConsole;
import game.model.Cell;
import game.model.Player;
import game.view.Interaction;
import game.view.InteractionConsole;
import game.view.Visualization;

import java.util.*;

abstract class GameController implements Game{
    private Interaction interactionConsole;
    private int size;
    private int typeOfGame;
    private GameState gs;
    private final BoardGame boardGame;
    public GameController () {
        this.interactionConsole = new InteractionConsole();
        boardGame = new BoardGameConsole(size, typeOfGame);
        interactionConsole = new InteractionConsole();
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
            interactionConsole.getView().displayText(interactionConsole.getVisualization(), "Entered Data invalid. Enter y/n");
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
        interactionConsole.getView().displayPlayerTurnName(interactionConsole.getVisualization(), player.getRepresentation());
        int[] coordinates;
        if (!player.isArtificial()) {
            coordinates = player.playerMove(interactionConsole.askCoordinates(size), size);
        } else {
            coordinates = player.playerMove(new int[2], size);
        }
        int x = coordinates[0];
        int y = coordinates[1];
        while (Objects.equals(cells[x][y].getRepresentation(), "| X ") || Objects.equals(cells[x][y].getRepresentation(), "| O ")) {
            interactionConsole.getView().displayText(interactionConsole.getVisualization(), "\nCell is already captured!!!");
            interactionConsole.getView().displayPlayerTurnName(interactionConsole.getVisualization(), player.getRepresentation());
            if (!player.isArtificial()) {
                coordinates = player.playerMove(interactionConsole.askCoordinates(size), size);
            } else {
                coordinates = player.playerMove(new int[2], size);
            }
            x = coordinates[0];
            y = coordinates[1];
        }
        player.captureCell(cells, x, y);
    }
    public Interaction getInteractionConsole () {
        return interactionConsole;
    }
    public void setSize (int size) {
        this.size = size;
    }
    public int getSize () {
        return size;
    }
    public void setTypeOfGame (int typeOfGame) {
        this.typeOfGame = typeOfGame;
    }
    public int getTypeOfGame () {
        return typeOfGame;
    }
    public void setGs (GameState gs) {
        this.gs = gs;
    }
    public GameState getGs () {
        return gs;
    }
    public BoardGame getBoardGame () {
        return boardGame;
    }
}
