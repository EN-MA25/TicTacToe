
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static Model model = new Model();

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
        allowedPlaces.add("quit");

        Scanner input = new Scanner(System.in);

        int playerTurn = 1;
        int turns = 0;
        while (true) {
            clearScreen();
            printBoard();
            System.out.println("Player " + numberToString(playerTurn) + " turn");
            String text = input.nextLine();

            if (allowedPlaces.contains(text)) {
                if (text.equals("quit")) {
                    System.exit(0);
                }
                Position pos = cordinateToPosition(text);

                if (model.getValue(pos) == 0) {
                    turns++;
                    model.setValue(pos, playerTurn);
                    playerTurn = 3 - playerTurn;
                    int winner = model.checkBoard();

                    if (winner != 0) {
                        clearScreen();
                        printBoard();
                        System.out.println("Congratulations! Player " + numberToString(winner) + " wins!");
                        input.nextLine();
                        model.clearBoard();
                        turns = 0;
                    } else if (turns == 9) {
                        clearScreen();
                        printBoard();
                        System.out.println("It is a tie!");
                        input.nextLine();
                        model.clearBoard();
                        turns = 0;
                    }
                }
            }
        }

    }

    public static void printBoard() {
        System.out.println("   A   B   C ");
        for (int i = 0; i < model.board.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < model.board[i].length; j++) {
                System.out.print(" " + numberToString(model.board[i][j]) + ((j == 2) ? "" : " |"));

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

    public static Position cordinateToPosition(String coordinate) {
        char[] charArray = coordinate.toCharArray();
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
        return new Position(row, column);

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }







}