public class View {
    public View () {}
    public void get_Type_Of_Game_List(Visualization visualization){
        System.out.println(visualization.GREEN_BOLD + """
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """ + visualization.ANSI_RESET);
    }
    public void display_Game_Field(Cell[][] cells, int size, Visualization visualization){
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int i = 0; i < size; i++) {
            System.out.print(representation);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                cells[i][j].cell_Print("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
            }
        }
    }
    public void display_Enter_X (Visualization visualization){
        System.out.print(visualization.RED_BOLD + "Enter coordinate X - " + visualization.ANSI_RESET);
    }
    public void display_Enter_correct_X (Visualization visualization, int size){
        System.out.print(visualization.RED_BOLD  + "Enter coordinate X " + "(x<=" + size + ") - " + visualization.ANSI_RESET);
    }
    public void display_Enter_Y (Visualization visualization){
        System.out.print(visualization.GREEN_BOLD + "Enter coordinate O - " + visualization.ANSI_RESET);
    }
    public void display_Enter_correct_Y (Visualization visualization, int size){
        System.out.print(visualization.RED_BOLD  + "Enter coordinate Y " + "(y<=" + size + ") - " + visualization.ANSI_RESET);
    }
}

