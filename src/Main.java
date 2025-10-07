import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static int[][] board =  {{0, 0, 0},
                                    {0, 0, 0},
                                    {0, 0, 0}};

    public static HashSet<String> allowedPlaces = new HashSet<>();

    public static void main(String[] args) {

        allowedPlaces.add("A1");
        allowedPlaces.add("A2");
        allowedPlaces.add("A3");
        allowedPlaces.add("B1");
        allowedPlaces.add("B2");
        allowedPlaces.add("B3");
        allowedPlaces.add("C1");
        allowedPlaces.add("C2");
        allowedPlaces.add("C3");

        Scanner input = new Scanner(System.in);

        int playerTurn = 1;
        int turns = 0;
        while (true) {
            clearScreen();
            printBoard();
            System.out.println("Player " + numberToString(playerTurn) + " turn");
            String text = input.nextLine();

            if (allowedPlaces.contains(text)) {
                int[] pos = cordinateToPosition(text);

                if (board[pos[0]][pos[1]] == 0) {
                    turns++;
                    board[pos[0]][pos[1]] = playerTurn;
                    playerTurn = 3 - playerTurn;
                    int winner = checkBoard();

                    if (winner != 0) {
                        clearScreen();
                        printBoard();
                        System.out.println("Congratulations! Player " + numberToString(winner) + " wins!");
                        input.nextLine();
                        clearBoard();
                        turns = 0;
                    } else if (turns == 9) {
                        clearScreen();
                        printBoard();
                        System.out.println("It is a tie!");
                        input.nextLine();
                        clearBoard();
                        turns = 0;
                    }
                }
            }
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

    public static int[] cordinateToPosition(String cordinate) {
        char[] charArray = cordinate.toCharArray();
        int column = -1;
        int row = -1;
        char columnChar = charArray[0];
        char rowChar = charArray[1];
        switch (columnChar) {
            case 'A' -> column = 0;
            case 'B' -> column = 1;
            case 'C' -> column = 2;
        }
        switch (rowChar) {
            case '1' -> row = 0;
            case '2' -> row = 1;
            case '3' -> row = 2;
        }
        return new int[] {row, column};

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

}