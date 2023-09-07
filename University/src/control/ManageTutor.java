package control;

import adt.*;
import boundary.*;
import dao.*;
import entity.Tutor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class ManageTutor {

    private TutorManagementUI tutorUI = new TutorManagementUI();
    private ListInterface<Tutor> tutorSortedList = new SortedLinkedList<>();

    private final String fileName = "tutor.txt";

    int listCategory = 1;
    int listOrderBy = 1;
    String[] listSortedCategory = new String[]{"Tutor ID", "Name", "Gender", "Faculty"};
    String[] listOrderCatgory = new String[]{"Ascending", "Descending"};

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
                    listAllTutor(false);
                    break;
                case 5:
                    //Remove tutor
                    findTutor("Remove");
                    break;
                case 6:
                    //Generate reports
                    tutorReport();
                    break;
                case 0:
                    System.out.println("Saving Data...");
                    //Close
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);
    }

    private void addNewTutor() {

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

    private void findTutor(String operation) {
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
            ListInterface<Tutor> searchResults = new SortedLinkedList<>();

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

    private boolean removeTutor(ListInterface<Tutor> searchResults) {
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

    private void displayTutors(ListInterface<Tutor> searchResults) {
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

    private boolean editTutor(ListInterface<Tutor> searchResults) {
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
            ListInterface<Tutor> tutorDisplay = new SortedLinkedList<>();
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

    private void listAllTutor(boolean isReport) {
        ListInterface<Tutor> showList = new LinkedList<>();
        // List copy from the sorted List
        Iterator<Tutor> iteratorSort = tutorSortedList.getIterator();
        while (iteratorSort.hasNext()) {
            showList.add(iteratorSort.next());
        }

        if (showList.isEmpty()) {
            Parts.header("List Tutors");
            Parts.sectionHeader("All Tutors");
            System.out.println("No tutors found.");
            Seperate.systemPause();
        } else {
            PrintTutorList(showList, isReport);
        }
    }

    private void tutorReport() {
        Parts.header("Genarate Reports");
        Parts.sectionHeader("Select report type");
        int option = Parts.menu(new String[]{"Tutor list report (List order adjust in \"Tutor List\")", "Tutor Summary report", "Faculty tutor report"}, "Back to menu");

        switch (option) {
            case 1:
                listAllTutor(true);
                break;
            case 2:
                tutorSummaryReport();
                break;
            case 3:
                facultyTutorReport();
                break;
        }
    }

    // -------------Others Methods-------------
    private String[] splitNameIntoLines(String name) {
        int lineCount = (int) Math.ceil((double) name.length() / 20);
        String[] nameLines = new String[lineCount];
        int startIndex = 0;
        int endIndex;

        for (int i = 0; i < lineCount; i++) {
            endIndex = Math.min(startIndex + 20, name.length());
            nameLines[i] = name.substring(startIndex, endIndex);
            startIndex = endIndex;
        }

        return nameLines;
    }

    private void PrintTutorList(ListInterface<Tutor> showList, boolean isReport) {
        int category = listCategory;
        int orderby = listOrderBy;
        do {
            Iterator<Tutor> iteratorShow = showList.getIterator();

            if (!isReport) {
                Parts.header("List Tutors");
            } else if (isReport) {
                Parts.header("Genarate Tutor List Reports");
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = formatDateTime(now);
                System.out.println("Report Generated at : " + formattedDateTime + "\n");
            }
            Parts.sectionHeader("All Tutors (Sort by : " + listSortedCategory[category - 1] + ", " + listOrderCatgory[orderby - 1] + ")");
            TutorListLayout(iteratorShow, isReport);

            if (!isReport) {
                Parts.header("List Tutors");
                // Select the category to arrange
                Parts.sectionHeader("Category to arrange");
                category = Parts.menu(listSortedCategory, "Close");
                if (category != 0) {
                    System.out.println("");
                    // Select asc / desc
                    Parts.sectionHeader("Arrange by");
                    orderby = Parts.menu(listOrderCatgory, "Close");

                    if (orderby != 0) {
                        mergeSort(showList, category, orderby);
                    }
                }
            } else {
                category = 0;
                orderby = 0;
            }
        } while (!(category == 0 || orderby == 0));     // Loop back
    }

    private void TutorListLayout(Iterator<Tutor> iteratorShow, boolean isReport) {
        System.out.println(" No.  Tutor ID  Name                  Gender  IC            Contact Num  Faculty");
        System.out.println("---------------------------------------------------------------------------------");

        int index = 1;
        while (iteratorShow.hasNext()) {
            Tutor tutor = iteratorShow.next();
            String name = tutor.getName();

            // Split the name into multiple lines every 20 characters
            String[] nameLines = splitNameIntoLines(name);

            for (int i = 0; i < nameLines.length; i++) {
                System.out.printf("%4s  %-8s  %-20s    %-4s  %-12s  %-11s   %-4s%n",
                        i == 0 ? String.valueOf(index) + "." : "",
                        i == 0 ? tutor.getTutorID() : "",
                        nameLines[i],
                        i == 0 ? tutor.getGender() : "",
                        i == 0 ? tutor.getIc() : "",
                        i == 0 ? tutor.getContactNum() : "",
                        i == 0 ? tutor.getFaculty() : ""
                );
            }
            index++;
        }
        if (isReport) {
            System.out.println("===================================Report End===================================");
        }
        System.out.println("");
        Seperate.systemPause();
    }

    private static void mergeSort(ListInterface<Tutor> list, int category, int orderBy) {
        // Check if the list is empty or has only one element
        if (list.isEmpty() || list.getNumberOfEntries() == 1) {
            return;
        }

        // Calculate the midpoint of the list
        int mid = list.getNumberOfEntries() / 2;

        // Split the list into two halves
        ListInterface<Tutor> leftList = new LinkedList<>();
        ListInterface<Tutor> rightList = new LinkedList<>();

        for (int i = 1; i <= mid; i++) {
            leftList.add(list.getEntry(i));
        }

        for (int i = mid + 1; i <= list.getNumberOfEntries(); i++) {
            rightList.add(list.getEntry(i));
        }

        // Recursively sort both halves
        mergeSort(leftList, category, orderBy);
        mergeSort(rightList, category, orderBy);

        // Merge the sorted halves
        merge(list, leftList, rightList, category, orderBy);
    }

    private static void merge(ListInterface<Tutor> result, ListInterface<Tutor> left, ListInterface<Tutor> right, int category, int orderBy) {
        int leftIndex = 1;
        int rightIndex = 1;
        int resultIndex = 1;

        // Compare and merge elements from left and right lists
        while (leftIndex <= left.getNumberOfEntries() && rightIndex <= right.getNumberOfEntries()) {
            Tutor leftTutor = left.getEntry(leftIndex);
            Tutor rightTutor = right.getEntry(rightIndex);

            int comparisonResult = compareTutors(leftTutor, rightTutor, category, orderBy);

            if (comparisonResult <= 0) {
                result.replace(resultIndex, leftTutor);
                leftIndex++;
            } else {
                result.replace(resultIndex, rightTutor);
                rightIndex++;
            }

            resultIndex++;
        }

        // Copy any remaining elements from left and right lists
        while (leftIndex <= left.getNumberOfEntries()) {
            result.replace(resultIndex, left.getEntry(leftIndex));
            leftIndex++;
            resultIndex++;
        }

        while (rightIndex <= right.getNumberOfEntries()) {
            result.replace(resultIndex, right.getEntry(rightIndex));
            rightIndex++;
            resultIndex++;
        }
    }

    private static int compareTutors(Tutor tutor1, Tutor tutor2, int category, int orderBy) {
        int comparisonResult = 0;

        switch (category) {
            case 1: // Tutor ID
                comparisonResult = tutor1.compareTutorID(tutor2);
                break;
            case 2: // Name
                comparisonResult = tutor1.compareName(tutor2);
                break;
            case 3: // Gender
                comparisonResult = tutor1.compareGender(tutor2);
                break;
            case 4: // Faculty
                comparisonResult = tutor1.compareFaculty(tutor2);
                break;
        }

        // Apply ascending or descending order
        if (orderBy == 2) {
            comparisonResult = -comparisonResult;
        }

        return comparisonResult;
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        // Define the desired format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        // Format the LocalDateTime object as a string
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }

    public void tutorSummaryReport() {
        // Initialize counts for total tutors, male, and female tutors for each faculty
        int totalTutors = 0;
        int totalMaleTutors = 0;
        int totalFemaleTutors = 0;
        int[] facultyCounts = new int[6]; // One slot for each faculty

        // Initialize faculty names
        String[] facultyNames = {"FAFB", "FCCI", "FOAS", "FOCS", "FOET", "FSSH"};

        // Iterate through the tutors to calculate counts
        for (int i = 1; i <= tutorSortedList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorSortedList.getEntry(i);
            char gender = tutor.getGender();
            String faculty = tutor.getFaculty();

            // Increment total tutor count
            totalTutors++;

            // Increment gender-specific counts
            if (gender == 'M') {
                totalMaleTutors++;
            } else if (gender == 'F') {
                totalFemaleTutors++;
            }

            // Increment faculty-specific counts
            for (int j = 0; j < facultyNames.length; j++) {
                if (facultyNames[j].equals(faculty)) {
                    facultyCounts[j]++;
                    break; // Exit the loop once the matching faculty is found
                }
            }
        }

        // Display the summary report
        Parts.header("Genarate Tutor Summary Report");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = formatDateTime(now);
        System.out.println("Report Generated at : " + formattedDateTime + "\n");
        System.out.println("Total Tutors: " + totalTutors);
        System.out.println("Male Tutors: " + totalMaleTutors);
        System.out.println("Female Tutors: " + totalFemaleTutors);
        System.out.println("Faculty-wise Tutor Counts:");

        for (int i = 0; i < facultyNames.length; i++) {
            System.out.println("  " + facultyNames[i] + ": " + facultyCounts[i]);
        }
        System.out.println("===============Report End===============");
        System.out.println("");
        Seperate.systemPause();
    }

    private void facultyTutorReport() {
        Parts.header("Faculty Tutor Report");
        Parts.sectionHeader("Select faculty");

        String faculty = new TutorManagementUI().selectFaculty();

        ListInterface<Tutor> selectedFacultyList = new LinkedList<>();

        // Use iterator, Go through the tutorSortedList
        Iterator<Tutor> iteratorShow = tutorSortedList.getIterator();

        // Compare the faculty in each entry
        while (iteratorShow.hasNext()) {
            Tutor tutor = iteratorShow.next();
            if (tutor.getFaculty().equals(faculty)) {
                // Same faculty, add into selectedFacultyList
                selectedFacultyList.add(tutor);
            }
        }

        Iterator<Tutor> iteratorSelectedFaculty = selectedFacultyList.getIterator();
        Parts.header("Faculty Tutor Report");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = formatDateTime(now);
        System.out.println("Report Generated at : " + formattedDateTime + "\n");
        // Print out selectedFacultyList
        TutorListLayout(iteratorSelectedFaculty, true);
    }
}
