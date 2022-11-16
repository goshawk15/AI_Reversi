public class Game implements Cloneable {
    char[][] board = new char[8][8];
    int maxDepth;
    boolean playerFirst;
    boolean player;

    Game(int maxD,boolean playerans){
        maxDepth = maxD;
        playerFirst = playerans;

        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '#';
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
    int getDisksbyChar(char x){
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
    void printBoard(boolean player){
        for(int i = 0;i <8;i++){
            for(int j = 0;j <8;j++){
                System.out.print(board[i][j]+"  ");
            }
            System.out.print("\n");
        }
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
                if (this.board[x][y] == '#') // not consecutive
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
                if (this.board[i][j] == '#')
                    return false;
            }
        }
        return true;
    }
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
        }  


}
