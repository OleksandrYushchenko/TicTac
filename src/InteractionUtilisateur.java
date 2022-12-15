import java.util.Scanner;

public class InteractionUtilisateur {
    View view = new View();
    public void InteractionUtilisateur(){

    }
    public int get_Size_Of_Game_Field(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of game field - ");
        return scanner.nextInt();
    }
    public int get_Type_Of_Game(){
        view.get_Type_Of_Game_List();
        Scanner scanner = new Scanner(System.in);
        view.Enter_number_of_players();
        return scanner.nextInt();
    }
    public int[] getCoordinates(){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter coordinate X - ");
        int x = sc.nextInt();
        while (x > TicTacToe.size) {
            System.out.print("Enter coordinate X " + "(x<=" + TicTacToe.size + ") - " );
            x = sc.nextInt();
        }
        System.out.print("Enter coordinate Y - ");
        int y = sc.nextInt();
        while (y > TicTacToe.size) {
            System.out.print("Enter coordinate Y " + "(y<=" + TicTacToe.size + ") - " );
            y = sc.nextInt();
        }

        return new int[]{ x, y };
    }
}
