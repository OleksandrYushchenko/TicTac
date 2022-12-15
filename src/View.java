import java.lang.reflect.Method;
import java.util.function.Function;

public class View {
    public View () {}
    public void get_Type_Of_Game_List(){
        System.out.println("""
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """);
    }
    public void Enter_number_of_players (){
        System.out.println("Enter number of players");
    }
    public void display_Game_Field(Cell[][] cells, int size){
        String representation = "";
        for (int i = 0; i < size; i++) {
            representation += "\n";
            for (int k = 0; k < size ; k++) {
                representation += "-----";
            }
            representation += "\n";
            for (int j = 0; j < size; j++) {
                representation += cells[i][j].cell_Print();
            }
        }
        System.out.println(representation);
    }
}

