public class Main {

    public static String[][] board = {{" "," ", " "},{" ", " "," "},{" ", " ", " "}};

    public static void main(String[] args) {

        board[0][0] = "O";
        board[0][1] = "X";
        board[0][2] = " ";
        board[1][0] = " ";
        board[1][1] = "O";
        board[1][2] = " ";
        board[2][0] = " ";
        board[2][1] = " ";
        board[2][2] = "X";

        printBoard();

    }

    public static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + ((j == 2) ? "" : " |"));

            }
            System.out.println();
            System.out.println((i == 2) ? "" : "---+---+---");
        }
    }
}