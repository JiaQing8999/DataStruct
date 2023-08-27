package boundary;

import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class TutorManagementUI {

    //Main menu of tutor module
    public int getTutorMenuSelection() {
        String[] menuOptions = {"Add tutor", "Find tutor", "Amend tutor details", "Tutor list", "Remove tutor", "Generate reports"};
        return Parts.menu("Tutor", menuOptions);
    }

    public String inputName() {
        String name = Validate.stringNullCheckingInput("Enter Name : ", "Name cannot be empty.");
        return name;
    }

    public String inputIC() {
        String ic = Validate.icInput("Enter IC number : ");
        return ic;
    }

    public String inputContactNum() {
        String phoneNum = Validate.phoneInput("Enter Contact number (without \"-\") : ");
        return phoneNum;
    }

    //TEST HERE
    public static void main(String[] args) {
        TutorManagementUI t = new TutorManagementUI();
        t.getTutorMenuSelection();
    }
}
