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
        Parts.header("Tutor");
        return Parts.menu(menuOptions, null);
    }

    public String inputName() {
        String name = Validate.stringNullCheckingInput("Enter Name : ", "  Name cannot be empty.");
        return name;
    }
    
    public char inputGender(){
        char gender = Validate.genderInput("Enter gender, (M)ale/(F)emale : ", "Character input only.");
        return gender;
    }

    public String inputIC() {
        String ic = Validate.icInput("Enter IC number : ");
        return ic;
    }

    public String inputContactNum() {
        String phoneNum = Validate.phoneInput("Enter Contact number (without \"-\") : ");
        return phoneNum;
    }

    public String selectFaculty() {
        String[] menuOptions = {"FAFB", "FCCI", "FOAS", "FOCS", "FOET", "FSSH"};
        System.out.println("Select Faculty :");
        return menuOptions[Parts.menu(menuOptions, null) - 1];
    }
}
