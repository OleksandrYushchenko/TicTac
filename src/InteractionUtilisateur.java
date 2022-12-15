import java.util.Scanner;

public class InteractionUtilisateur {
    public void InteractionUtilisateur(){

    }
    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of game field - ");
        return scanner.nextInt();
    }
    public int get_Type_Of_Game(){
        System.out.println("""
        1. To Play with 2 human players;
        2. To Play against the machine;
        3. To watch two machines play together;
        """);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players");
        return scanner.nextInt();
    }

}
