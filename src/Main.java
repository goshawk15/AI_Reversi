import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Maximum depth?");
        int ans0 = sc.nextInt();

        System.out.println("Play first?(y/n)");
        String ans1 = sc.next();

        Game game = new Game(ans0,( (ans1 == "y") ? true : false));
        game.printBoard();
    }
}