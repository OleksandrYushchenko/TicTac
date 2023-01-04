package game.model;

import java.util.Objects;

public class  Cell {
    public String representation;
    public Player player;

    public Cell(){
        this.representation = "|   ";
    }

    /**
     * Sets owner of game.model.Cell after player move
     * @param player player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * print cell representation
     * @param startVisualisation String - visual effects for System out print(ln)
     * @param endVisualisation String - visual effects for System out print(ln)
     * @return cell representation
     */
    public String cellPrint(String startVisualisation, String endVisualisation) {
        System.out.print(startVisualisation + this.representation + endVisualisation);
        return this.representation;
    }
}
