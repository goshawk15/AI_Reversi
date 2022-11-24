import java.util.ArrayList;
import java.util.Random;

public class Minimax {
    int maxDepth;
    Minimax(int d){
        maxDepth = d;
    }
    Move run(Game game){
        return min(new Game(game), 0);
    }

    Move max(Game game, int depth){
        Random r = new Random();

        if(game.checkGame() || depth == maxDepth){
            return new Move(game.getLastMove().getRow(), game.getLastMove().getCol(), game.getValue(game.getLastMove().getRow(),game.getLastMove().getCol(),game.player));
        }

        Move maxMove = new Move(Integer.MIN_VALUE);
        ArrayList<Game> games = game.getGames(true);

        for (Game temp: games) {
            Move move = min(temp , depth + 1);
            if(move.getValue() >= maxMove.getValue()){
                if(move.getValue() == maxMove.getValue()){
                    if(r.nextInt(2) == 0)
                    {
                        maxMove.setRow(temp.getLastMove().getRow());
                        maxMove.setCol(temp.getLastMove().getCol());
                        maxMove.setValue(move.getValue());
                    }
                }
                else
                {
                    maxMove.setRow(temp.getLastMove().getRow());
                    maxMove.setCol(temp.getLastMove().getCol());
                    maxMove.setValue(move.getValue());
                }
            }
        }
        return maxMove;
    }

    Move min(Game game, int depth){
        Random r = new Random();

        if(game.checkGame() || depth == maxDepth){
            return new Move(game.getLastMove().getRow(), game.getLastMove().getCol(), game.getValue(game.getLastMove().getRow(),game.getLastMove().getCol(),game.player));
        }

        Move minMove = new Move(Integer.MAX_VALUE);
        ArrayList<Game> games = game.getGames(false);

        for (Game temp: games) {
            Move move = max(temp , depth + 1);

            if(move.getValue() <= minMove.getValue()){
                if(move.getValue() == minMove.getValue()){
                    if(r.nextInt(2) == 0) {
                        minMove.setRow(temp.getLastMove().getRow());
                        minMove.setCol(temp.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                    }
                }
                else {
                    minMove.setRow(temp.getLastMove().getRow());
                    minMove.setCol(temp.getLastMove().getCol());
                    minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
    }
}