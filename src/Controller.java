public class Controller {

    static Model model; // = new Model();

    static int playerTurn;
    static int turns;
    static InputHandler inputHandler;

    public Controller() {
        model = new Model();

        playerTurn = 1;
        turns = 0;
        inputHandler = new InputHandler();
        run();
    }
    private void run(){


        //while (true) {
           // clearScreen();
        //    printBoard();
        while (true) {


            int input = menu();
            inputHandler.nextLine();
            if (input == 1) {
                choosePlayerNames();
                clearScreen();
                game();
            } else if (input == 2) {
                System.out.println("2");
            } else {
                System.out.println("0");
            }

        }
        //}
    }


    public static int menu() {
        while (true) {

            clearScreen();
            System.out.println("Welcome to the Game!");
            System.out.println("Menu:");
            System.out.println("1. Player Vs Player");
            System.out.println("2. Player Vs Computer");
            System.out.println("3. Quit");
            int input = inputHandler.getInt();

            switch (input) {
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    System.exit(0);
                }
            }
        }
    }

    public static void choosePlayerNames() {
        System.out.println("Please enter name of player X:");
        String nameX = inputHandler.getName();
        System.out.println("Please enter name of player O:");
        String nameO = inputHandler.getName();

        Player playerX = new Player(nameX);
        Player playerO = new Player(nameO);

        model.setPlayerX(playerX);
        model.setPlayerO(playerO);

    }


    public static void game() {
        while (true) {
            clearScreen();
            printBoard();
            System.out.println(numberToPlayer(playerTurn).getName() + ", chose a free square to place your " + numberToString(playerTurn));
            String text = inputHandler.getString();


            if (text != null) {

                if (text.equals("QUIT")) {
                    model.clearRound();
                    model.clearBoard();
                    return;
                }

                Position pos = cordinateToPosition(text);

                if (model.getValue(pos) == 0) {
                    turns++;
                    model.setValue(pos, playerTurn);
                    playerTurn = 3 - playerTurn;
                    int winner = model.checkBoard();

                    if (winner != 0) {
                        Player playerWhoWon = numberToPlayer(winner);
                        playerWhoWon.getPoint();
                        clearScreen();
                        printBoard();


                        System.out.println("Congratulations! Player " + numberToPlayer(winner).getName() + " wins!");
                        newRound();
                    } else if (turns == 9) {
                        clearScreen();
                        printBoard();
                        System.out.println("It is a tie!");
                        newRound();
                    }
                }
            }
        }
    }

    public static void newRound() {
        inputHandler.nextLine();
        model.clearBoard();
        turns = 0;
        model.nextRound();
        playerTurn = 2 - model.getRound() % 2;
    }

    public static void printBoard() {
        System.out.println("Round: " + model.getRound());
        System.out.println("Score:");
        System.out.println(model.getPlayerX().getName() + ": " + model.getPlayerX().getScore() + " - " + model.getPlayerO().getName() + ": " + model.getPlayerO().getScore());
        System.out.println();
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

    public static Player numberToPlayer(int number) {
        if  (number == 1) {
            return model.getPlayerX();
        }
        return model.getPlayerO();

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
