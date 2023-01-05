package game.model;

public class  Cell {
    private String representation;
    private Player player;
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
    public String getRepresentation () {
        return representation;
    }
    public void setRepresentation (String representation) {
        this.representation = representation;
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
