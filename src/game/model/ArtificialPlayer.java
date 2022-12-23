package game.model;

import game.view.InteractionUtilisateur;

public class ArtificialPlayer extends Player {
    public ArtificialPlayer (String pRepresnetation){
        super(pRepresnetation);
    }
    private int getRandomNumber(int max) {
        return (int) (
                (Math.random() * (max)));
    }

    /**
     * return int[] with 2 values - coordinates of player move
     * @param size int - size of gameBoard
     * @param interactionUtilisateur - instance of game.view.InteractionUtilisateur class
     * @return int[] length 2 - random coordinates
     */
    public int[] getCoordinates(Integer size, InteractionUtilisateur interactionUtilisateur){
        int x;
        int y;
        x = getRandomNumber(size);
        y = getRandomNumber(size);
        return new int[] {x, y};
    }
}
