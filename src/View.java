public class View {
    Visualization visualization;
    public View () {
        this.visualization = new Visualization();
    }
    public void get_Type_Of_Game_List(){
        System.out.println(visualization.GREEN_BOLD + """
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """ + visualization.ANSI_RESET);
    }
    public void Enter_number_of_players (){
        System.out.print(visualization.RED_BOLD + "\nEnter number of players - " + visualization.ANSI_RESET);
    }
    public void display_Game_Field(Cell[][] cells, int size){
        String representation = "";
        representation += "\n";
        for (int k = 0; k < size ; k++) {
            representation += "----";
        }
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
}

