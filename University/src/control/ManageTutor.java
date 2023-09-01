package control;

import adt.*;
import boundary.*;
import dao.*;
import entity.Tutor;
import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class ManageTutor {

    private TutorManagementUI tutorUI = new TutorManagementUI();
    private SortedListInterface<Tutor> tutorSortedList = new SortedLinkedList<>();

    public static void main(String[] args) {
        ManageTutor manageTutor = new ManageTutor();
        manageTutor.runManageTutor();
    }

    public void runManageTutor() {

        int selection;

        do {
            selection = tutorUI.getTutorMenuSelection();
            switch (selection) {
                case 1:
                    //Add tutor
                    addNewTutor();
                    break;
                case 2:
                    //Find tutor
                    break;
                case 3:
                    //Amend tutor details
                    break;
                case 4:
                    //Tutors list
                    break;
                case 5:
                    //Remove tutor
                    break;
                case 6:
                    //Generate reports
                    break;
                case 0:
                    System.out.println("Saving Data...");
                    //Close
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);
    }

    public void addNewTutor() {

        Parts.header("Add a tutor");

        Tutor newTutor = new Tutor();
        newTutor.setName(tutorUI.inputName());
        newTutor.setGender(tutorUI.inputGender());
        newTutor.setIc(tutorUI.inputIC());
        newTutor.setContactNum(tutorUI.inputContactNum());
        newTutor.setFaculty(tutorUI.selectFaculty());
        char another;
        do {
            Seperate.clearScreen();
            Parts.header("Add a tutor");
            System.out.println("  New tutor details");
            System.out.println("  -----------------");
            System.out.println(newTutor);

            char confirm = Validate.yesNoInput("Comfirm to add " + newTutor.getTutorID() + "? (Y)es/(N)o > ", "  Character input only.");

            if (confirm == 'Y') {
                if (tutorSortedList.add(newTutor)) {
                    System.out.println("New tutor added.");
                }
            } else {
                System.out.println("No new tutor added.");
            }
            //Ask for another entries
            another = Validate.yesNoInput("Add another tutor? (Y)es/(N)o > ", "  Character input only.");
        } while (another == 'Y');
        
        //write entire sorted list into file
        TutorDAO.writeTutorsToFile("tutor.txt", tutorSortedList);
    }
}
