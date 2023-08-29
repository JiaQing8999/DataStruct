package boundary;

/**
 *
 * @author meikee
 */
public class tutorialGroupUI {

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
        return Parts.menu("Tutorial Group", menuOptions);
    }
}
