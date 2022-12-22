public class View {
    public View () {}
    public void getTypeOfGameList(Visualization visualization){
        System.out.println(visualization.GREEN_BOLD + """
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """ + visualization.ANSI_RESET);
    }
    public void displayGameField(Cell[][] cells, int size, Visualization visualization){
        StringBuilder representation = new StringBuilder();
        representation.append("\n");
        representation.append("----".repeat(Math.max(0, size)));
        for (int i = 0; i < size; i++) {
            System.out.print(representation);
            for (int j = 0; j < size; j++) {
                if (j % size == 0) {
                    System.out.print("\n");
                }
                cells[i][j].cellPrint("printTest", visualization.WHITE_BOLD, visualization.ANSI_RESET);
            }
        }
    }
    public void displayText (Visualization visualization, String str) {
        System.out.print(visualization.RED_BOLD + str + visualization.ANSI_RESET);
    }
    public void displayEnterCorrectX(Visualization visualization, int size){
        System.out.print(visualization.RED_BOLD  + "Enter coordinate X " + "(x<=" + size + ") - " + visualization.ANSI_RESET);
    }
    public void displayEnterCorrectY(Visualization visualization, int size){
        System.out.print(visualization.RED_BOLD  + "Enter coordinate Y " + "(y<=" + size + ") - " + visualization.ANSI_RESET);
    }
    public void displayPlayerTurnName(Visualization visualization, Player player) {
        System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + player.representation);
    }
    public void displayTest(Visualization visualization, String test) {
        System.out.println(visualization.GREEN_BOLD + test + visualization.ANSI_RESET);
    }
}

