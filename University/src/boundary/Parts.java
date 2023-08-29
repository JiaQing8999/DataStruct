package boundary;

import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class Parts {

    public static void header(String headerName) {
        String headerSeparator = "";

        Seperate.clearScreen();     // Clear screen
        for (int i = 0; i < headerName.length(); i++) {
            headerSeparator += "=";
        }

        System.out.println("===" + headerSeparator + "===");
        System.out.println(" | " + headerName + " | ");
        System.out.println("===" + headerSeparator + "===");
    }

    public static int menu(String[] menuOptions, String closeOperation) {
        // Initialize variables
        int minSelection;
        String operationLabel;
        int selection;

        // Define minimum selection and operation label
        if (closeOperation == null) {
            minSelection = 1;       // Minimum selection when closeOperation is null
            operationLabel = "";    // No close operation
        } else {
            minSelection = 0;       // Minimum selection when closeOperation is provided
            operationLabel = "\n" + "0 - " + closeOperation + "\n";     // Close operation label with a newline
        }

        // Print menu options
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + " - " + menuOptions[i]);
        }

        // Print operationLabel with/without selection 0
        System.out.println(operationLabel);

        // Prompt for and validate selection
        do {
            selection = Validate.intInput("Selection > ", "  Please enter an integer.");

            // Check if selection is outside the valid range
            if (selection < minSelection || selection > menuOptions.length) {
                System.out.println("  Select only " + minSelection + " - " + menuOptions.length + ".");
            }
        } while (selection < minSelection || selection > menuOptions.length);

        return selection;
    }
}
