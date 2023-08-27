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
    
}
