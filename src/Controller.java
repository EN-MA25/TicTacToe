public class Controller {

    // An instance of the Game Model that holds the current state
    static Model model;

    // Holds whos Turn it is.
    // 1 is X or the player and 3 is second player or computer.
    // Reason for 1 and 3 is for the logic of the computer brain.
    static int playerTurn;

    // Who many places has been set.
    // If 9 is reach without any winner.
    // The board is full and it is a tie
    static int turns;

    // input handler to cover user inputs
    static InputHandler inputHandler;


    public Controller() {
        // Init methods and starts the game
        model = new Model();
        playerTurn = 1;
        turns = 0;
        inputHandler = new InputHandler();
        run();
    }
    private void run(){

        while (true) {

            int input = menu();
            inputHandler.nextLine();

            switch (input) {
                case 1: {
                    // Player Vs Player
                    choosePlayerVsPlayerNames();
                    clearScreen();
                    playerVsPlayer();
                    break;
                }
                case 2: {
                    // Player Vs Computer Easy mode
                    choosePlayerVsComputerName();
                    clearScreen();
                    playerVsComputer(false);
                    break;
                }
                case 3: {
                    // Player Vs Computer Hard Mode
                    choosePlayerVsComputerName();
                    clearScreen();
                    playerVsComputer(true);
                    break;

                }
                case 4: {
                    // Quits the app
                    System.exit(0);
                    break;

                }
            }
        }
    }

    // Shows the menu and ask for input
    // Is run until 1-4 is chosen
    public static int menu() {
        while (true) {
            clearScreen();
            System.out.println("Welcome to the Game!");
            System.out.println("Menu:");
            System.out.println("1. Player Vs Player");
            System.out.println("2. Player Vs Computer Easy!");
            System.out.println("3. Player Vs Computer Hard!");
            System.out.println("4. Quit");
            int input = inputHandler.getInt();
            if (input != 0) {
                return input;
            }
        }
    }

    // Method to get names of Players when 2 humans meet
    public static void choosePlayerVsPlayerNames() {
        System.out.println("Please enter name of player X:");
        String nameX = inputHandler.getName();
        System.out.println("Please enter name of player O:");
        String nameO = inputHandler.getName();

        Player playerX = new Player(nameX);
        Player playerO = new Player(nameO);

        model.setPlayerX(playerX);
        model.setPlayerO(playerO);

    }

    // Method to get names of Players when 2 humans meeting the computer
    public static void choosePlayerVsComputerName() {
        System.out.println("Please enter your name:");
        String nameX = inputHandler.getName();

        Player playerX = new Player(nameX);
        Player playerO = new Player("Computer");

        model.setPlayerX(playerX);
        model.setPlayerO(playerO);

    }

    // Logic for Player Vs Player
    public static void playerVsPlayer() {
        while (true) {
            clearScreen();
            printBoard();
            if (playerTurn()) {
                return;
            }
        }
    }

    // Logic for Player Vs Player
    public static void playerVsComputer(boolean isHardMode) {
        while (true) {
            clearScreen();
            printBoard();


            if (playerTurn == 1) {
                if (playerTurn()) {
                    return;
                }

            } else {
                computerTurn(isHardMode);
            }
        }
    }

    // Method for a Player to put X or O
    private static boolean playerTurn() {
        System.out.println(numberToPlayer(playerTurn).getName() + ", chose a free square to place your " + numberToString(playerTurn));
        System.out.println("(Write \"quit\" to go back to menu)");
        String text = inputHandler.getString();

        if (text != null) {

            if (text.equals("QUIT")) {
                model.clearRound();
                model.clearBoard();
                return true;
            }

            Position pos = cordinateToPosition(text);

            // If the position the player wants to put X or O on is free
            if (model.getValue(pos) == 0) {
                turns++;
                model.setValue(pos, playerTurn);
                int winner = model.checkBoard(playerTurn);
                playerTurn = (playerTurn == 1) ? 3 : 1;


                if (winner != 0) {
                    // If we have a Winner
                    Player playerWhoWon = numberToPlayer(winner);
                    playerWhoWon.getPoint();
                    clearScreen();
                    printBoard();
                    System.out.println("Congratulations! " + playerWhoWon.getName() + " wins!");
                    newRound();

                } else if (turns == 9) {
                    // If the board is full with no winner
                    clearScreen();
                    printBoard();
                    System.out.println("It is a tie!");
                    newRound();

                }
            }
        }
        return false;
    }

    // Method for the computer to put X or O
    private static void computerTurn(boolean isHardMode) {
        System.out.print("Computer is thinking");

        // Method to make it look like computer is thinking to make the player experience better
        for (int i=0; i<3; i++) {
            sleep();
            System.out.print(".");
        }

        // Call the models think method to know where the computer should make his move
        Position pos = model.think(isHardMode);

        if (model.getValue(pos) == 0) {
            turns++;
            model.setValue(pos, playerTurn);
            int winner = model.checkBoard(playerTurn);
            playerTurn = (playerTurn == 1) ? 3 : 1;

            if (winner != 0) {
                // The Computer won
                Player playerWhoWon = numberToPlayer(winner);
                playerWhoWon.getPoint();
                clearScreen();
                printBoard();
                System.out.println("You lose sucker!!!!");
                newRound();
            } else if (turns == 9) {
                // If the board is full with no winner
                clearScreen();
                printBoard();
                System.out.println("It is a tie!");
                newRound();
            }
        }
    }

    // Method to start a new round
    // Board is clear and variables is reset
    public static void newRound() {
        inputHandler.nextLine();
        model.clearBoard();
        turns = 0;
        model.nextRound();
        playerTurn = (model.getRound() % 2 == 0) ? 3 : 1;
    }

    // A sleep method that sleeps 400 milliseconds
    private static void sleep() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to print the board with it current state
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

    // Help method to convert the state of a position to either blank, X or O
    public static String numberToString(int number) {
        return switch (number) {
            case 0 -> " ";
            case 1 -> "X";
            case 3 -> "O";
            default -> "";
        };
    }

    // Help method to get the Player
    public static Player numberToPlayer(int number) {
        if  (number == 1) {
            return model.getPlayerX();
        }
        return model.getPlayerO();
    }

    // Help method to convert a cordinate to a position
    // For instance B3 is converted to column 1 row 2
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

    // Method to clear the entire console.
    // Does not work when played in IntelliJ but works when played in Terminal
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
