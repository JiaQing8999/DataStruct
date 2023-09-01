package control;

import adt.*;
import boundary.*;
import dao.*;
import entity.Tutor;
import java.util.Iterator;
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
                    findTutor(false);
                    break;
                case 3:
                    //Amend tutor details
                    break;
                case 4:
                    //Tutors list
                    break;
                case 5:
                    //Remove tutor
                    findTutor(true);
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

        Parts.header("Add A Tutor");

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

    public void findTutor(boolean hasDelete) {
        int searchOption;
        boolean deletePerformed = false;

        do {
            if (hasDelete) {
                Parts.header("Remove a Tutor");
            } else {
                Parts.header("Find a Tutor");
            }

            String[] searchOptions = {"Search by Tutor ID", "Search by Name"};
            searchOption = Parts.menu(searchOptions, "Back to Menu");

            if (searchOption == 0) {
                break; // User selected to go back to the main menu
            }

            Parts.header("Find Tutor");

            String searchCriteria = Validate.stringInput("Enter the search criteria: ", "Invalid input. Please enter a valid search criteria.");

            SortedListInterface<Tutor> searchResults = new SortedLinkedList<>();

            for (int i = 1; i <= tutorSortedList.getNumberOfEntries(); i++) {
                Tutor currentTutor = tutorSortedList.getEntry(i);

                if ((searchOption == 1 && currentTutor.getTutorID().equalsIgnoreCase(searchCriteria))
                        || (searchOption == 2 && currentTutor.getName().equalsIgnoreCase(searchCriteria))) {
                    // Add the tutor to the search results if there is a match
                    searchResults.add(currentTutor);
                }
            }

            if (searchResults.isEmpty()) {
                System.out.println("No tutors found matching the criteria.");
            } else {
                System.out.println("Tutors found: " + searchResults.getNumberOfEntries());

                if (hasDelete) {
                    String[] deleteOptions = new String[searchResults.getNumberOfEntries()];
                    for (int i = 1; i <= searchResults.getNumberOfEntries(); i++) {
                        Tutor tutor = searchResults.getEntry(i);
                        deleteOptions[i - 1] = tutor.getTutorID() + ", " + tutor.getName();
                    }
                    int deleteSelection = Parts.menu(deleteOptions, "Cancel Deletion");
                    if (deleteSelection == 0) {
                        System.out.println("Deletion canceled.");
                    } else {
                        deletePerformed = true;
                        // Delete the selected tutor
                        tutorSortedList.remove(searchResults.getEntry(deleteSelection));
                        System.out.println("Tutor removed.");
                    }
                } else {
                    // Use an iterator to print out tutor details
                    Iterator<Tutor> iterator = searchResults.getIterator();
                    int resultIndex = 1;
                    while (iterator.hasNext()) {
                        System.out.println("\nResult " + resultIndex + ":");
                        Tutor tutor = iterator.next();
                        System.out.println(tutor);
                        resultIndex++;
                        System.out.println(); // Add a newline for readability
                    }
                }
            }

            Seperate.systemPause();
        } while (searchOption != 0);

        if (deletePerformed) {
            //write entire sorted list into file
            TutorDAO.writeTutorsToFile(fileName, tutorSortedList);
            System.out.println("\n\nSaving data...");
            Seperate.systemPause();
        }
    }
}
