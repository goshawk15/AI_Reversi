public class Game {
    char[][] board = new char[8][8];
    int maxDepth;
    boolean playerFirst;

    Game(int maxD,boolean playerF){
        maxDepth = maxD;
        playerFirst = playerF;
        for(int i = 0;i <8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }
    }

    void printBoard(){
        for(int i = 0;i <8;i++){
            for(int j = 0;j <8;j++){
                System.out.print(board[i][j]+"  ");
            }
            System.out.print("\n");
        }
    }
}
