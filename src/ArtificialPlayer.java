import java.util.Scanner;

public class ArtificialPlayer extends Player{
    public ArtificialPlayer (String pRepresnetation){
        super(pRepresnetation);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public int[] getCoordinates(){
        System.out.print("Robot");
        int x = getRandomNumber(0, TicTacToe.size);
        int y = getRandomNumber(0, TicTacToe.size);

        return new int[]{ x, y };
    }
}
