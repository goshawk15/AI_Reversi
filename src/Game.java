import java.util.ArrayList;

public class Game implements Cloneable {
    char[][] board = new char[8][8];
    int maxDepth;
    boolean playerFirst;
    boolean player;
    Move lastmove;

    Game(int maxD,boolean playerans){
        maxDepth = maxD;
        playerFirst = playerans;

        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }
        if(playerans) {
            player = true;
            board[3][3] = 'X';
            board[4][4] = 'X';
            board[3][4] = 'O';
            board[4][3] = 'O';
        }
        else {
            player = false;
            board[3][3] = 'O';
            board[4][4] = 'O';
            board[3][4] = 'X';
            board[4][3] = 'X';
        }
    }
    Game(Game game){
        this.board = new char[8][8];
        this.lastmove = game.lastmove;
        this.player = game.player;
        this.maxDepth = game.maxDepth;

        for(int i = 0; i < this.board.length; i++)
        {
            for(int j = 0; j < this.board.length; j++)
            {
                this.board[i][j] = game.board[i][j];
            }
        }

    }
    int getDisksbyChar(boolean player){
        char x = 'O';
        if(player)
            x = 'X';
        int count = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if(this.board[i][j] == x)
                    count++;}}
        return count;
    }
    void changeCurrentPlayer(){
        this.player = !this.player;
    }
    char getPiece(boolean player){
        if(player)
            return 'X';
        return 'O';
    }
    boolean checkFlip(int x, int y, int deltaX, int deltaY, char myPiece, char opponentPiece){
        if(x == 8 || y == 8 || x == -1 || y == -1)
            return false;
        if (board[x][y] == opponentPiece)
        {
            while ((x > 0) && (x < 7) && (y > 0) && (y < 7))
            {
                x += deltaX;
                y += deltaY;
                if (this.board[x][y] == '-') // not consecutive
                    return false;
                if (this.board[x][y] == myPiece)
                    return true; // At least one piece we can flip
                else
                {

                }
            }
        }
        return false; // Either no consecutive opponent pieces or hit the edge
    }
    void flipPieces(int x, int y, int deltaX, int deltaY, char myPiece, char opponentPiece){
        while (this.board[x][y] == opponentPiece)
        {
            this.board[x][y] = myPiece;
            x += deltaX;
            y += deltaY;
        }
    }

    void capture(int i,int j,boolean player){
        this.lastmove = new Move(i,j);

        if(player)
            this.board[i][j] = 'X';
        else
            this.board[i][j] = 'O';

        if (checkFlip(i - 1, j, -1, 0, getPiece(player), getPiece(!player)))
            flipPieces(i - 1, j, -1, 0, getPiece(player), getPiece(!player));

        if (checkFlip(i + 1, j, 1, 0, getPiece(player), getPiece(!player)))
            flipPieces(i + 1, j, 1, 0, getPiece(player), getPiece(!player));

        if (checkFlip(i, j-1, 0, -1, getPiece(player), getPiece(!player)))
            flipPieces(i, j-1, 0, -1, getPiece(player), getPiece(!player));

        if (checkFlip(i, j+1, 0, 1, getPiece(player), getPiece(!player)))
            flipPieces(i, j+1, 0, 1, getPiece(player), getPiece(!player));

        if (checkFlip(i - 1, j-1, -1, -1, getPiece(player), getPiece(!player)))
            flipPieces(i - 1, j-1, -1, -1, getPiece(player), getPiece(!player));

        if (checkFlip(i + 1, j-1, 1, -1, getPiece(player), getPiece(!player)))
            flipPieces(i + 1, j-1, 1, -1, getPiece(player), getPiece(!player));

        if (checkFlip(i - 1, j+1, -1, 1, getPiece(player), getPiece(!player)))
            flipPieces(i - 1, j+1, -1, 1, getPiece(player), getPiece(!player));

        if (checkFlip(i + 1, j+1, 1, 1, getPiece(player), getPiece(!player)))
            flipPieces(i + 1, j+1, 1, 1, getPiece(player), getPiece(!player));

    }
    boolean validMove(int i,int j,boolean player){

        if (checkFlip(i - 1, j, -1, 0, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i + 1, j, 1, 0, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i, j-1, 0, -1, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i, j+1, 0, 1, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i - 1, j-1, -1, -1, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i + 1, j-1, 1, -1, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i - 1, j+1, -1, 1, getPiece(player), getPiece(!player)))
            return true;

        if (checkFlip(i + 1, j+1, 1, 1, getPiece(player), getPiece(!player)))
            return true;

        return false;
    }

    boolean checkGame(){
        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
    int getValue(int x , int y , boolean p){
        int before = this.getDisksbyChar(!p);
        this.capture(x,y,p);
        int after = this.getDisksbyChar(!p);

        return before - after;
    }
    ArrayList<Game> getGames(boolean p){
        this.player = p;
        ArrayList<Game> games = new ArrayList<>();
        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                if (this.validMove(i, j, p) && this.board[i][j] == '-') {
                    Game temp = new Game(this);
                    temp.capture(i, j, p);
                    games.add(temp);
                }
            }
        }
        return games;
    }

    private void setPlayer(boolean p) {
        this.player = p;
    }

    public Move getLastMove() {
        return this.lastmove;
    }
}
