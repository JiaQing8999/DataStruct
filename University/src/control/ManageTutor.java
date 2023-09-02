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
                    findTutor("Search");
                    break;
                case 3:
                    //Amend tutor details
                    findTutor("Edit");
                    break;
                case 4:
                    //Tutors list
                    listAllTutor();
                    break;
                case 5:
                    //Remove tutor
                    findTutor("Remove");
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

    public void findTutor(String operation) {
        int searchOption;
        boolean changePerformed = false;

        do {
            switch (operation) {
                case "Search":
                    Parts.header("Find a Tutor");
                    break;
                case "Edit":
                    Parts.header("Edit Tutor Details");
                    break;
                case "Remove":
                    Parts.header("Remove a Tutor");
                    break;
                default:
                    break;
            }

            String[] searchOptions = {"Search by Tutor ID", "Search by Name"};
            searchOption = Parts.menu(searchOptions, "Back to Menu");

            if (searchOption == 0) {
                break; // User selected to go back to the main menu
            }

            Parts.header("Find Tutor");
            String searchCriteria = null;
            if (searchOption == 1) {
                searchCriteria = Tutor.tutorIdInput("Enter the search Tutor ID: ", "  Invalid input. Please enter a valid tutor id format.");
            } else if (searchOption == 2) {
                searchCriteria = Validate.stringInput("Enter the search name: ", "  Invalid input. Please enter a name.");
            }
            SortedListInterface<Tutor> searchResults = new SortedLinkedList<>();

            for (int i = 1; i <= tutorSortedList.getNumberOfEntries(); i++) {
                Tutor currentTutor = tutorSortedList.getEntry(i);

                if ((searchOption == 1 && currentTutor.getTutorID().equalsIgnoreCase(searchCriteria))
                        || (searchOption == 2 && currentTutor.getName().equalsIgnoreCase(searchCriteria))) {
                    // Add the tutor to the search results if there is a match
                    searchResults.add(currentTutor);
                }
            }

            if (searchResults.isEmpty()) {      // Result Not found
                System.out.println("No tutors found matching the criteria.");
            } else {        // Result found
                System.out.println("Tutors found: " + searchResults.getNumberOfEntries());

                switch (operation) {
                    case "Remove":
                        // Remove tutor
                        changePerformed = removeTutor(searchResults);
                        break;
                    case "Search":
                        displayTutors(searchResults);
                        break;
                    case "Edit":
                        changePerformed = editTutor(searchResults);
                        break;
                    default:
                        break;
                }
            }
            Seperate.systemPause();
        } while (searchOption != 0);

        if (changePerformed) {
            //write entire sorted list into file
            TutorDAO.writeTutorsToFile(fileName, tutorSortedList);
            System.out.println("\n\nSaving data...");
            Seperate.systemPause();
        }
    }

    private boolean removeTutor(SortedListInterface<Tutor> searchResults) {
        String[] deleteOptions = new String[searchResults.getNumberOfEntries()];
        Iterator<Tutor> tutorIterator = searchResults.getIterator();
        int index = 0;
        boolean changePerformed = false;

        while (tutorIterator.hasNext()) {
            Tutor tutor = tutorIterator.next();
            deleteOptions[index] = tutor.getTutorID() + ", " + tutor.getName();
            index++;
        }

        //Print out tutor details
        displayTutors(searchResults);

        Parts.sectionHeader("Delete Selection");
        int deleteSelection = Parts.menu(deleteOptions, "Cancel Deletion");

        if (deleteSelection == 0) {
            System.out.println("Deletion canceled.");
        } else {
            // Delete the selected tutor
            tutorSortedList.remove(searchResults.getEntry(deleteSelection));
            System.out.println("Tutor removed.");
            changePerformed = true;
        }

        return changePerformed;
    }

    private void displayTutors(SortedListInterface<Tutor> searchResults) {
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

    private boolean editTutor(SortedListInterface<Tutor> searchResults) {
        String[] editOptions = new String[searchResults.getNumberOfEntries()];
        Iterator<Tutor> tutorIterator = searchResults.getIterator();
        int index = 0;
        boolean changePerformed = false;

        while (tutorIterator.hasNext()) {
            Tutor tutor = tutorIterator.next();
            editOptions[index] = tutor.getTutorID() + ", " + tutor.getName();
            index++;
        }

        //Print out tutor details here
        displayTutors(searchResults);

        Parts.sectionHeader("Select a tutor to edit");
        int tutorSelection = Parts.menu(editOptions, "Cancel Edit");

        if (tutorSelection == 0) {
            System.out.println("Editing canceled.");
            return false; // Cancel the editing process
        }

        // Get the selected tutor
        Tutor selectedTutor = searchResults.getEntry(tutorSelection);

        int editSelection;
        do {
            // Show again header and details of the tutor in Tutor class
            Parts.header("Edit Tutor Details");

            //Print out tutor details
            SortedListInterface<Tutor> tutorDisplay = new SortedLinkedList<>();
            tutorDisplay.add(selectedTutor);
            displayTutors(tutorDisplay);

            Parts.sectionHeader("Select edit field.");
            // Select the field to edit (0 to Cancel edit, 6 to save edit)
            editSelection = Parts.menu(new String[]{"Name", "Gender", "IC", "Contact Number", "Faculty", "Confirm Edit"}, "Cancel edit");

            switch (editSelection) {
                case 1:
                    selectedTutor.setName(tutorUI.inputName());
                    break;
                case 2:
                    selectedTutor.setGender(tutorUI.inputGender());
                    break;
                case 3:
                    selectedTutor.setIc(tutorUI.inputIC());
                    break;
                case 4:
                    selectedTutor.setContactNum(tutorUI.inputContactNum());
                    break;
                case 5:
                    selectedTutor.setFaculty(tutorUI.selectFaculty());
                    break;
                case 6:
                    tutorSortedList.remove(searchResults.getEntry(tutorSelection));
                    tutorSortedList.add(selectedTutor);
                    System.out.println("Tutor details edited.");
                    changePerformed = true;
                    break;
                case 0:
                    System.out.println("Cancel all edit...");
                    break;
            }
        } while (editSelection > 0 && editSelection < 6);       // not select 6 or 0, loop

        return changePerformed;
    }

    private void listAllTutor() {
        Parts.header("List Tutors");
        Parts.sectionHeader("All Tutors");
        if (tutorSortedList.isEmpty()) {
            System.out.println("No tutors found.");
        } else {
            int index = 1;
            Iterator<Tutor> iterator = tutorSortedList.getIterator();
            System.out.println(" No.  Tutor ID  Name                  Gender  IC            Contact Num  Faculty");
            System.out.println("---------------------------------------------------------------------------------");
            while (iterator.hasNext()) {
                Tutor tutor = iterator.next();
                String name = tutor.getName();

                for (int i = 0; i < Math.ceil((double) name.length() / 20); i++) {
                    int endIndex = Math.min((i + 1) * 20, name.length());
                    String nameLine = name.substring(i * 20, endIndex);
                    System.out.printf("%3d.  %-8s  %-20s    %-4s  %-12s  %-11s   %-4s%n",
                            index++,
                            tutor.getTutorID(),
                            i == 0 ? nameLine : "", // Only print the name on the first line
                            i == 0 ? tutor.getGender() : "", // Gender only on the first line
                            i == 0 ? tutor.getIc() : "", // IC only on the first line
                            i == 0 ? tutor.getContactNum() : "", // Contact Num only on the first line
                            i == 0 ? tutor.getFaculty() : "" // Faculty only on the first line
                    );
                }
            }
        }
        Seperate.systemPause();
        
        //Select the category to arrange
        
        //Select asc / desc
        
        //Loop back
        
    }
}
