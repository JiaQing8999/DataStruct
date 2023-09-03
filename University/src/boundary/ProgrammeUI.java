package boundary;

import utility.Validate;

/**
 *
 * @author Khor Zhi Ying
 */
public class ProgrammeUI {

    public int getProgrammeMenuSelection() {
        String[] menuOptions = {
            "Add new programme",
            "Remove programme",
            "Find programme",
            "List all programme",
            "Add tutorial group to a programme",
            "Remove tutorial group from a programme",
            "List all tutorial group for a programme",
            "Generate report"
        };
        Parts.header("Programme Management");
        return Parts.menu(menuOptions, "Close");
    }

    public String inputProgCode() {
        String code = "";

        do {
            code = Validate.stringNullCheckingInput("Enter programme code (eg. RSW) : ", "  Programme code cannot be empty.");
            
            if(code.length() != 3){
                System.out.println("Invalid programme code!");
            }

        } while (code.length() != 3);
        
        return code;
    }
    
    public String inputprogName() {
        String code = "";

        do {
            code = Validate.stringNullCheckingInput("Enter programme code (eg. RSW) : ", "  Programme code cannot be empty.");

            if (code.length() != 3) {
                System.out.println("Invalid programme code!");
            }

        } while (code.length() != 3);

        return code;
    }
    
    public static void main(String[] args) {
        ProgrammeUI ui = new ProgrammeUI();
        
        ui.getProgrammeMenuSelection();
        ui.inputProgCode();
    }
}
