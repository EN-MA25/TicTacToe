import java.util.Arrays;

public class Model {

    private Player playerX;
    private Player playerO;
    private int round;

    public int[][] board =  {{0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}};

    public Model() {
        this.round = 1;
    }

    public int getRound() {
        return this.round;
    }
    public void nextRound() {
        this.round++;
    }
    public void clearRound() {
        this.round = 1;
    }

    public Player getPlayerX() {
        return playerX;
    }
    public Player getPlayerO() {
        return playerO;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }
    public void setPlayerO(Player playerO) {
        this.playerO = playerO;

    }

    public void clearBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    public int getValue(Position pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    public void setValue(Position pos, int value) {
        board[pos.getRow()][pos.getColumn()] = value;
    }

    public int checkBoard() {

        for (int winner = 1; winner <= 2; winner++) {
            for (int i = 0; i < board[0].length; i++) {
                if (board[i][0] == winner && board[i][1] == winner  && board[i][2] == winner) {
                    return winner;
                }

            }
            for (int i = 0; i < board.length; i++) {
                if (board[0][i] == winner && board[1][i] == winner  && board[2][i] == winner) {
                    return winner;
                }
            }
            if (board[0][0] == winner && board[1][1] == winner  && board[2][2] == winner) {
                return winner;
            }
            if (board[0][2] == winner && board[1][1] == winner  && board[2][0] == winner) {
                return winner;
            }
        }
        return 0;

    }
}
