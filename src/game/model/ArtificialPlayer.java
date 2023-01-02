package game.model;

public class ArtificialPlayer extends Player {
    public ArtificialPlayer (String pRepresnetation){
        super(pRepresnetation);
    }
    public boolean isArtificial (){
        return true;
    }
    private int getRandomNumber(int max) {
        return (int) (
                (Math.random() * (max)));
    }

    /**
     * return int[] with 2 values - coordinates of player move
     * @param size int - size of gameBoard
     * @return int[] length 2 - random coordinates
     */
    public int[] playerMove(int[] arr, int size){
        int x;
        int y;
        x = getRandomNumber(size);
        y = getRandomNumber(size);
        return new int[] {x, y};
    }
}
