import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Maximum depth?");
        int ans0 = sc.nextInt();

        System.out.println("Play first?(y/n)");
        String ans1 = sc.next();

        Game game = new Game(ans0,(Objects.equals(ans1, "y")));
        char curplayer;

        while(true) {
            game.printBoard(game.player);
            if (game.player)
                curplayer = 'X';
            else
                curplayer = 'O';
            System.out.println("Its "+curplayer+"' turn");
            
            System.out.println("Choose tile (i,j)");
            int i = sc.nextInt();
            int j = sc.nextInt();

            while(i<0||i>7||j<0||j>7){
                System.out.println("Tile is out of bounds! Choose another tile");
                i = sc.nextInt();
                j = sc.nextInt();
            }
            while(game.board[i][j] != '#') {
                System.out.println("Tile already captured! Choose another tile");
                i = sc.nextInt();
                j = sc.nextInt();
            }

            while(!game.validMove(i, j , game.player)) {
                System.out.println("Invalid move try another");
                i = sc.nextInt();
                j = sc.nextInt();
            }
            game.capture(i,j,game.player);
            game.changeCurrentPlayer();

            if(game.checkGame())
                break;
        }
    }
}