import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimax{

    private final int MAX=Integer.MAX_VALUE;
    private final int MIN=Integer.MIN_VALUE;

    char[][] copyBoard(char[][] board){
        char[][] result = new char[8][8];
        for(int i = 0;i < 8; i++){
            result[i] = Arrays.copyOf(board[i],8);
        }
        return result;
    }
    
    int run(Game g, int depth , boolean originalPlayer , boolean currentPlayer) throws CloneNotSupportedException{
        List<Integer> Validi = new ArrayList<Integer>();
        List<Integer> Validj = new ArrayList<Integer>();
        boolean opponent = false;
        if(currentPlayer)
            opponent = true;
        
        for(int i = 0; i< 8; i ++){
            for(int j = 0 ; j< 8 ;j++){
                if(g.validMove(i, j, g.player))
                    Validi.add(i);
                    Validj.add(j);
            }
        }
        if(Validi.size() == 0){
            return run(g,depth + 1 ,originalPlayer , opponent);
        }
        else{
            int bestMove = -99999;
            if(originalPlayer != currentPlayer)
                bestMove = 99999;
            
            int size = Validi.size();
            for(int i =0 ; i < size ;i++){            
                Game temp = (Game)g.clone();
                temp.capture(Validi.get(i), Validj.get(i), temp.player);

                int val = run(temp,depth+1,originalPlayer, opponent);
                if(originalPlayer == currentPlayer){
                    if(val > bestMove)
                        bestMove = val;
                }
                else{
                    if(val < bestMove)
                        bestMove = val;
                }
        }
        return bestMove;
    }
    }

}
