package game.view;

public interface Interaction {
    Visualization getVisualization ();
    View getView ();
    int askSizeOfGame ();
    int askTypeOfGame ();
    int[] askCoordinates (int size);
}
