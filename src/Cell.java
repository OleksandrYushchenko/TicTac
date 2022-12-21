import java.util.Objects;

public class  Cell {
    String representation;

    public Cell(){
        this.representation = "|   ";
    }
    public String cell_Print (String type, String startVisualisation, String endVisualisation) {
        if (Objects.equals(type, "printTest")) {
            System.out.print(startVisualisation + this.representation + endVisualisation);
        }
        return this.representation;
    }
}
