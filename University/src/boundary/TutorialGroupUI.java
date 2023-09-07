package boundary;

import utility.Validate;

/**
 *
 * @author Phua Mei Kee
 */
public class TutorialGroupUI {

    //Main menu of tutorial group module
    public int getTutorialGrpMenuSelection() {
        String[] menuOptions = {
            "Add a student to a tutorial group",
            "Remove a student from a tutorial group",
            "Change the tutorial group for a student",
            "Find a student in a tutorial group",
            "List all students in a tutorial group",
            "Filter tutorial groups",
            "Generate relevant reports"};
        Parts.header("Tutorial Group");
        return Parts.menu(menuOptions,"Close");
    }

    public int getFilterGroupMenuSelection() {
        String[] menuSelection = {
            "Tutorial groups which contains most students",
            "Tutorial groups which contains least students"
        };
        Parts.header("Filter Tutorial Group Menu");
        return Parts.menu( menuSelection,"Close");
    }

    public String inputStudentName() {
        String name = Validate.stringNullCheckingInput("Enter Name : ", "Name cannot be empty.");
        return name;
    }

    public int inputStudentId() {
        int id = Validate.intInput("Enter id : ", "ID can be integer only.");
        return id;
    }

    public int inputCurrentTutorialGroup() {
        int tutorialGrp = Validate.intInput("Enter current tutorial group : ", "Tutorial group can be integer only.");
        return tutorialGrp;
    }

    public int inputNewTutorialGroup() {
        int tutorialGrp = Validate.intInput("Enter new tutorial group : ", "Tutorial group can be integer only.");
        return tutorialGrp;
    }
}
