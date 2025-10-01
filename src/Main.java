public class Main {

    public static int[][] board =  {{0, 0, 0},
                                    {0, 0, 0},
                                    {0, 0, 0}};

    public static void main(String[] args) {

        board[0][0] = 1;
        board[0][1] = 2;
        board[0][2] = 2;
        board[1][0] = 0;
        board[1][1] = 1;
        board[1][2] = 0;
        board[2][0] = 1;
        board[2][1] = 0;
        board[2][2] = 1;

        printBoard();


        int winner = checkBoard();
        if (winner != 0) {
            System.out.println("Congratulations! Player " + numberToString(winner) + " wins!");
        }

    }

    public static void printBoard() {
        System.out.println("   A   B   C ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + numberToString(board[i][j]) + ((j == 2) ? "" : " |"));

            }
            System.out.println();
            System.out.println((i == 2) ? "" : "  ---+---+---");
        }
    }

    public static String numberToString(int number) {
        return switch (number) {
            case 0 -> " ";
            case 1 -> "X";
            case 2 -> "O";
            default -> "";
        };
    }

    public static int checkBoard() {


        for (int winner = 1; winner <= 2; winner++) {
            for (int i = 0; i < board.length; i++) {
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