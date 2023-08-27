package boundary;

import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class Parts {

    public static void header(String headerName) {
        String headerLength = "";

        Seperate.clearScreen();
        for (int i = 0; i < headerName.length(); i++) {
            headerLength += "=";
        }
        System.out.println("===" + headerLength + "===");
        System.out.println(" | " + headerName + " | ");
        System.out.println("===" + headerLength + "===" + "\n");
    }

    public static int menu(String headerName, String[] selectionArray) {
        int selection = 0;

        header(headerName);

        for (int i = 0; i < selectionArray.length; i++) {
            System.out.println(i + 1 + " - " + selectionArray[i]);
        }

        System.out.println("\n0 - Close\n");

        do {
            selection = Validate.intInput("Selection > ", "  Integer input only.");     //Only integer accepted.
            if (selection > selectionArray.length || selection < 0) {
                System.out.println("  Select only 0 - " + selectionArray.length + ".");
            }
        } while (selection > selectionArray.length || selection < 0);       //Only 0-(array length) accepted.

        return selection;
    }

}
