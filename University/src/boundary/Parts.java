package boundary;

import utility.Validate;

/**
 *
 * @author Lim Jia Qing
 */

public class Parts {

    public static void header(String headerName) {
        String headerLength = "";
        
        Validate.clearScreen();
        for (int i = 0; i < headerName.length(); i++) {
            headerLength += "=";
        }
        System.out.println("===" + headerLength + "===");
        System.out.println(" | " + headerName + " | ");
        System.out.println("===" + headerLength + "===" + "\n");
    }
    
    public static void main(String[] args) {
        header("Tutor");
    }
}
