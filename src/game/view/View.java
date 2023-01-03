package game.view;
public class View {
    public View () {}
    /**
     * Display list of game types
     * @param visualization styles for list
     */
    public void getTypeOfGameList(Visualization visualization){
        System.out.println(visualization.GREEN_BOLD + """
        \n1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """ + visualization.ANSI_RESET);
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
    public void displayPlayerTurnName(Visualization visualization, String playerRepresentation) {
        System.out.println(visualization.BLUE_UNDERLINED + "\nPlayer_" + visualization.ANSI_RESET + playerRepresentation);
    }
    public String displayTest(Visualization visualization, String test) {
        System.out.println(visualization.GREEN_BOLD + test + visualization.ANSI_RESET);
        return test;
    }
}

