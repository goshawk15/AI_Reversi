public class Game {
    char[][] board = new char[8][8];
    int maxDepth;
    boolean playerFirst;
    boolean player;

    Game(int maxD,boolean playerF){
        maxDepth = maxD;
        playerFirst = playerF;
        if(playerF)
            player = true;
        else
            player = false;

        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }
    }
    void changeCurrentPlayer(){
        this.player = !this.player;
    }
    void printBoard(){
        for(int i = 0;i <8;i++){
            for(int j = 0;j <8;j++){
                System.out.print(board[i][j]+"  ");
            }
            System.out.print("\n");
        }
    }
    void capture(int i,int j,boolean player){
        if(player)
            this.board[i][j] = 'X';
        else
            this.board[i][j] = 'O';
        changeCurrentPlayer();
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
}
