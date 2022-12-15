public class ArtificialPlayer extends Player{
    public ArtificialPlayer (String pRepresnetation){
        super(pRepresnetation);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public int[] getCoordinates(Integer size){
        int x = getRandomNumber(0, size);
        int y = getRandomNumber(0, size);
        return new int[]{ x, y };
    }
}
