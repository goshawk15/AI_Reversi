import java.util.ArrayList;

public class Game {
    char[][] board = new char[8][8];
    int maxDepth;
    boolean playerFirst;
    boolean player; // game's active player ( if true then human else AI)
    Move lastmove;

    //constructor
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

    //copy constructor
    Game(Game game){
        this.board = new char[8][8];
        this.lastmove = game.lastmove;
        this.playerFirst = game.playerFirst;
        this.player = game.player;
        this.maxDepth = game.maxDepth;

        for(int i = 0; i < this.board.length; i++)
        {
            System.arraycopy(game.board[i], 0, this.board[i], 0, this.board.length);
        }

    }

    //returns the number of disk owned by the player or the AI
    int getDisksbyChar(boolean player){
        char x = getPiece(player);
        int count = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if(this.board[i][j] == x)
                    count++;}}
        return count;
    }

    //switches the player variable
    void changeCurrentPlayer(){
        this.player = !this.player;
    }

    //returns the piece of each player
    char getPiece(boolean player){
        if(player)
            return 'X';
        return 'O';
    }

    //returns true if the piece in the coordinates (i,j) can be flipped
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
            }
        }
        return false; // Either no consecutive opponent pieces or hit the edge
    }

    //flips the pieces between the coordinates (x,y) and (x+deltaX,y+deltaY)
    void flipPieces(int x, int y, int deltaX, int deltaY, char myPiece, char opponentPiece){
        while (this.board[x][y] == opponentPiece)
        {
            this.board[x][y] = myPiece;
            x += deltaX;
            y += deltaY;
        }
    }

    //captures the empty piece in the coordinates (i,j) and calls the flipPieces method for each neighboring piece that must be flipped
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

    //returns true if the piece in the coordinates (i,j) can be captured by the current player
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

    //returns true if the game is over
    boolean checkGame(){
        int x = getDisksbyChar(true);
        int y = getDisksbyChar(false);
        if(x+y != 64)
            return false;

        for(int i = 0; i < 8 ; i++)
            for(int j = 0 ; j< 8 ; j++)
                if(this.validMove(i,j,this.player))
                    return false;

        return true;
    }

    //heuristic method that returns t
    int getCorners(){
        int XCorners = 0;
        int OCorners = 0;

        if(this.board[0][0] == 'X')
            XCorners ++;
        if(this.board[0][7] == 'X')
            XCorners ++;
        if(this.board[7][0] == 'X')
            XCorners ++;
        if(this.board[7][7] == 'X')
            XCorners ++;
        if(this.board[0][0] == 'O')
            OCorners ++;
        if(this.board[0][7] == 'O')
            OCorners ++;
        if(this.board[7][0] == 'O')
            OCorners ++;
        if(this.board[7][7] == 'O')
            OCorners ++;
        if(XCorners + OCorners == 0)
            return 0;
        else
            return 100 * (OCorners - XCorners) / (OCorners + XCorners);

    }
    int getMobility() {
        int Xmobility = 0;
        int Omobility = 0;
        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                if(this.validMove(i,j,true))
                    Xmobility++;
                else if(this.validMove(i,j,false))
                    Omobility++;
            }
        }
        if(Xmobility + Omobility == 0)
            return 0;
        else
            return 100 * (Omobility - Xmobility) / (Omobility + Xmobility);

    }
    int getParity(){
        int Xdisks = getDisksbyChar(true);
        int Odisks =getDisksbyChar(false);
        return 100 * (Odisks - Xdisks) / (Odisks + Xdisks);
    }
    int getValue(){
        return getCorners() + getMobility() + getParity();
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
    public Move getLastMove() {
        return this.lastmove;
    }
}