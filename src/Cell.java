import java.util.Objects;

public class  Cell {
    String representation;
    Player player;
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Cell(){
        this.representation = "|   ";
    }
    public String cellPrint(String type, String startVisualisation, String endVisualisation) {
        if (Objects.equals(type, "printTest")) {
            System.out.print(startVisualisation + this.representation + endVisualisation);
        }
        return this.representation;
    }
}
