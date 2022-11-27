import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Reversi game on 8x8 tableau.\n");
        System.out.println("Player 'O' is computer, player 'X' is you");
        //User input of the maximum depth to be used in the minimax function
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer representing the depth of the  searching tree.");
        int ans0 = sc.nextInt();

        //User input of who will start first
        System.out.println("Play first? Enter 'y' for yes or 'n' for no");
        String ans1 = sc.next();

        //game object creation
        Game game = new Game(ans0, (Objects.equals(ans1, "y")));
        char curplayer;

        //minimax object creation
        Minimax minimax = new Minimax(ans0);

        System.out.println("This is starting game arrangement:");
        //while the game ending conditions are not true the game continues
        while (!game.checkGame()) {
            DisplayTableau display = new DisplayTableau();
            System.out.println(display.createTableau(game.board));

            if (game.player)
                curplayer = 'X';
            else {
                curplayer = 'O';
            }
            System.out.println("Its " + curplayer + "' turn");

            if(game.player) {
                System.out.println("Choose tile (i,j)");
                int i = sc.nextInt();
                int j = sc.nextInt();

                while (i < 0 || i > 7 || j < 0 || j > 7) {
                    System.out.println("Tile is out of bounds! Choose another tile");
                    i = sc.nextInt();
                    j = sc.nextInt();
                }
                while (game.board[i][j] != '-') {
                    System.out.println("Tile already captured! Choose another tile");
                    i = sc.nextInt();
                    j = sc.nextInt();
                }
                while (!game.validMove(i, j, game.player)) {
                    System.out.println("Invalid move try another");
                    i = sc.nextInt();
                    j = sc.nextInt();
                }
                game.capture(i, j, true);
            }
            else{
                Move move = minimax.run(game);
                System.out.println("Computer plays: ("+move.getRow()+","+move.getCol()+")");
                game.capture(move.getRow(),move.getCol(),false);
            }
            game.changeCurrentPlayer();

        }

        //endgame
        int Xdisks = game.getDisksbyChar(true);
        int Odisks = game.getDisksbyChar(false);
        int result = Xdisks - Odisks;
        if(result > 0)
            System.out.println("Player wins! with score: "+Xdisks+"-"+Odisks);
        else
            System.out.println("PC wins! with score: "+Odisks+"-"+Xdisks);
    }
}