import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimax {
    char[][] copyBoard(char[][] board) {
        char[][] result = new char[8][8];
        for (int i = 0; i < 8; i++) {
            result[i] = Arrays.copyOf(board[i], 8);
        }
        return result;
    }

    int run(Game g, int depth, boolean playerturn) throws CloneNotSupportedException {

        List<Integer> Validi = new ArrayList<Integer>();
        List<Integer> Validj = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (g.validMove(i, j, g.player)) {
                    Validi.add(i);
                    Validj.add(j);
                }
            }
        }
        int available = Validi.size();

        if (depth == 0)
            return -1;

        int MAX = Integer.MAX_VALUE;
        int MIN = Integer.MIN_VALUE;
        Game temp = (Game) g.clone();

        for (int i = 0; i < available; i++) {
            int x = Validi.get(i);
            int y = Validj.get(i);

        }

        return 0;

    }
}
