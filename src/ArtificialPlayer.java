public class ArtificialPlayer extends Player{
    public ArtificialPlayer (String pRepresnetation){
        super(pRepresnetation);
    }
    public int getRandomNumber(int min, int max) {
        return (int) (
                (Math.random() * (max - min)) + min);
    }
    public int[] getCoordinates(Integer size, InteractionUtilisateur interactionUtilisateur){
        int x;
        int y;
        x = getRandomNumber(0, size);
        y = getRandomNumber(0, size);
        return new int[] {x, y};
    }
}
