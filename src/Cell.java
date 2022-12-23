import java.util.Objects;

public class  Cell {
    String representation;
    Player player;

    public Cell(){
        this.representation = "|   ";
    }

    /**
     * Sets owner of Cell after player move
     * @param player player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * print cell representation
     * @param type String - type of Print "Win" || "NoWin"
     * @param startVisualisation String - visual effects for System out print(ln)
     * @param endVisualisation String - visual effects for System out print(ln)
     * @return cell representation
     */
    public String cellPrint(String type, String startVisualisation, String endVisualisation) {
        if (Objects.equals(type, "Win")) {
            System.out.print(startVisualisation + this.representation + endVisualisation);
        }
        return this.representation;
    }
}
