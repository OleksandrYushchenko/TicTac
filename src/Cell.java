public class  Cell {
    String representation;

    public Cell(){

        this.representation = "|   ";
    }
    public String cell_Print (String t, String u, String r) {
        if (t == "printTest") {
            System.out.print(u + this.representation + r);
        }

        return this.representation;
    }
}
