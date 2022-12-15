import java.util.Scanner;

public class InteractionUtilisateur {
    public void InteractionUtilisateur(){

    }
    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of game field - ");
        return scanner.nextInt();
    }
}
