package boundary;

import static boundary.Parts.header;
import utility.Validate;

/**
 *
 * @author Lim Jia Qing
 */
public class TutorManagementUI {

    //Main menu of tutor module
    public int getMenuSelection() {
        int selection = 0;

        header("Tutor");
        System.out.println("1 - Add tutor");
        System.out.println("2 - Find tutor");
        System.out.println("3 - Amend tutor details");
        System.out.println("4 - Tutor list");
        System.out.println("5 - Remove tutor");
        System.out.println("6 - Generate reports");

        System.out.println("0 - Close" + "\n");

        do {
            selection = Validate.intInput("Selection > ", "  Integer input only.");     //Only integer accepted.
            if (selection > 6 || selection < 0) {
                System.out.println("  Select only 0 - 6.");
            }
        } while (selection > 6 || selection < 0);       //Only 0-6 accepted.

        return selection;
    }
    
    public String inputName(){
        String name = Validate.stringNullCheckingInput("Enter Name : ", "Name cannot be empty.");
        return name;
    }
    
    //TEST HERE
    public static void main(String[] args) {
        TutorManagementUI t = new TutorManagementUI();
        t.getMenuSelection();
    }
}
