package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author Lim Jia Qing
 */
public class Seperate {

    //Clear screen function
    public static void clearScreen() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
        }
    }

    //System pause function
    public static void systemPause() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press <Enter> to continue...");
        sc.nextLine();
    }
}
