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
    private final String fileName = "tutor.txt";

    public ManageTutor() {
        tutorSortedList = TutorDAO.readTutorsFromFile(fileName);
    }

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
        // Generate Tutor ID
        newTutor.setTutorID(newTutor.generateNewTutorID(tutorSortedList));
        
        newTutor.setName(tutorUI.inputName());
        newTutor.setGender(tutorUI.inputGender());
        newTutor.setIc(tutorUI.inputIC());
        newTutor.setContactNum(tutorUI.inputContactNum());
        newTutor.setFaculty(tutorUI.selectFaculty());
        char next;
        do {
            Seperate.clearScreen();
            Parts.header("Add a tutor");
            System.out.println("  New tutor details");
            System.out.println("  -----------------");
            System.out.println(newTutor + "\n");

            char confirm = Validate.yesNoInput("Comfirm to add " + newTutor.getTutorID() + "? (Y)es/(N)o > ", "  Character input only.");

            if (confirm == 'Y') {
                // Add new entry to Sorted List
                if (tutorSortedList.add(newTutor)) {
                    System.out.println("New tutor added.");
                }
            } else {
                System.out.println("No new tutor added.");
            }
            //Ask whether have another entry
            next = Validate.yesNoInput("Add another tutor? (Y)es/(N)o > ", "  Character input only.");
        } while (next == 'Y');

        //write entire sorted list into file
        TutorDAO.writeTutorsToFile(fileName, tutorSortedList);
        System.out.println("\n\nSaving data...");
        Seperate.systemPause();
    }
}
