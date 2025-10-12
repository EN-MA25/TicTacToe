import java.util.HashSet;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);
    private final HashSet<String> allowedPlaces = new HashSet<>();

    // Init method to add the allowed inputs in a Set
    public InputHandler() {
        allowedPlaces.add("A1");
        allowedPlaces.add("A2");
        allowedPlaces.add("A3");
        allowedPlaces.add("B1");
        allowedPlaces.add("B2");
        allowedPlaces.add("B3");
        allowedPlaces.add("C1");
        allowedPlaces.add("C2");
        allowedPlaces.add("C3");
        allowedPlaces.add("QUIT");
    }

    // Get a String that exist in the allowedPlaces
    public String getString() {
        String inputText = scanner.nextLine();
        inputText = inputText.toUpperCase();
        if (allowedPlaces.contains(inputText)) {
            return inputText;
        } else  {
            return null;
        }
    }

    // Get an int from 1-4 for the menu.
    // 0 Is returned if it fails
    public int getInt() {
        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            if (input>=1 && input <=4) {
                return input;
            }
        }
        scanner.nextLine();
        return 0;
    }

    // Get name with no error handling
    public String getName() {
        return scanner.nextLine();
    }

    // Flush the scanner
    public void nextLine() {
        scanner.nextLine();
    }
}
