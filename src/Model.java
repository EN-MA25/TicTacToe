import java.util.Arrays;

public class Model {

    // The game model that holds the 2 players. Which round it is and the state of the board

    private Player playerX;
    private Player playerO;
    private int round;

    // The board state
    // 0 means free square
    // 1 means X and is always used by a player
    // 3 means O and can be used by computer
    public int[][] board = {{0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}};

    // Init method
    public Model() {
        this.round = 1;
    }

    // Setters and getters
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
        return board[pos.row()][pos.column()];
    }

    public void setValue(Position pos, int value) {
        board[pos.row()][pos.column()] = value;
    }

    // Method to check if we have a winner
    public int checkBoard(int winner) {

        for (int i = 0; i < board[0].length; i++) {
            if (board[i][0] == winner && board[i][1] == winner && board[i][2] == winner) {
                return winner;
            }

        }
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == winner && board[1][i] == winner && board[2][i] == winner) {
                return winner;
            }
        }
        if (board[0][0] == winner && board[1][1] == winner && board[2][2] == winner) {
            return winner;
        }
        if (board[0][2] == winner && board[1][1] == winner && board[2][0] == winner) {
            return winner;
        }

        return 0;
    }

    // The think method is used when computer should make a move
    public Position think(boolean isHardMode) {

        Position pos; // = new Position(0, 0);

        // Check if computer can win
        pos = checkValue(6);
        if (pos != null) {
            return pos;
        }

        // Check if computer must block
        pos = checkValue(2);
        if (pos != null) {
            return pos;
        }

        // Place in middle if free
        if (isHardMode) {
            if (board[1][1] == 0) {
                return new Position(1, 1);
            }
        }

        // Check if 2 free squares
        pos = checkValue(3);
        if (pos != null) {
            return pos;
        }

        // Check if 3 free squares
        pos = checkValue(0);
        if (pos != null) {
            return pos;
        }

        // If all fails. Place on any random position.
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] == 0) {
                    return new Position(i, j);
                }
            }
        }

        return null;
    }

    // Method to see the values in rows, columns and the 2 diagonals
    // If found the position will be returned, null if not.
    public Position checkValue(int value) {

        // Each row
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][0] + board[i][1] + board[i][2] == value) {

                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        return new Position(i, j);
                    }
                }
            }
        }

        // Each Column
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == value) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[j][i] == 0) {
                        return new Position(j, i);
                    }
                }
            }
        }

        // One diagonal
        if (board[0][0] + board[1][1] + board[2][2] == value) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][j] == 0) {
                    return new Position(j, j);
                }
            }
        }

        // The other diagonal
        if (board[0][2] + board[1][1] + board[2][0] == value) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[2 - j][j] == 0) {
                    return new Position(2 - j, j);
                }
            }
        }
        return null;
    }
}
