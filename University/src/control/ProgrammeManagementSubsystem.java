package control;

import adt.SortedLinkedList;
import adt.SortedListInterface;
import boundary.ProgrammeUI;
import boundary.Parts;
import dao.ProgrammeDAO;
import entity.Programme;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Khor Zhi Ying
 */
public class ProgrammeManagementSubsystem {

    private ProgrammeUI progUI = new ProgrammeUI();
    private SortedListInterface<Programme> progSortedList = new SortedLinkedList<>();
    private final String fileName = "programme.txt";

    public ProgrammeManagementSubsystem() {
        progSortedList = ProgrammeDAO.readProgFromFile(fileName);
    }

    public static void main(String[] args) {
        ProgrammeManagementSubsystem p = new ProgrammeManagementSubsystem();
        p.ProgMenu();
    }

    public void ProgMenu() {
        int selection;

        do {
            selection = progUI.getProgrammeMenuSelection();
            switch (selection) {
                case 1:
                    addNewProgramme();
                    break;
                case 3:
                    findProgramme();
                    break;
                case 4:
                    editProgramme();
                    break;
                case 5:
                    listAllProg();
                    break;
            }

        } while (selection != 0);
    }

    public void addNewProgramme() {

        Programme newProg = new Programme();
        char next;
        // String code;

        do {
            Seperate.clearScreen();
            Parts.header("Add new Programme");

            newProg.setProgCode(progUI.inputProgCode());
            newProg.setProgName(progUI.inputProgName());
            newProg.setProgDurationYear(progUI.inputProgDurationYear());

            Seperate.clearScreen();
            Parts.header("Add new Programme");
            Parts.sectionHeader("New Programme details");
            System.out.println("\n" + newProg.toString() + "\n");

            char confirm = Validate.yesNoInput("Comfirm to add this programme? (Y)es/(N)o > ", "Invalid input! Please enter Y or N only.");

            if (confirm == 'Y') {
                // Add new programme to Sorted List
                if (progSortedList.add(newProg)) {
                    System.out.println("New programme added successfully!");
                }
            } else {
                System.out.println("No new programme added.");
            }

            // ask for continue
            next = Validate.yesNoInput("Add another new programme? (Y)es/(N)o > ", "Invalid input! Please enter Y or N only.");
        } while (next == 'Y');

        // write entire sorted list into file
        ProgrammeDAO.writeProgToFile(fileName, progSortedList);
        System.out.println("\n\nSaving data...");

        Seperate.systemPause();
    }

    public void editProgramme() {
        Programme prog;
        int editField;
        int resultIndex;
        char confirmEdit;

        Parts.header("Edit A Programme");
        String code = progUI.inputProgCode();           // ask for input progCode
        prog = searchResult(code);                      // store search result into a object
        resultIndex = searchResultIndex(code);          // store search result entries index

        if (prog != null) {
            System.out.println("\n");
            Parts.sectionHeader("Programme Details");
            System.out.println(prog.toString());                    // print the search result
            System.out.println("\n\nSelect a field to edit:");      // ask for select a field to edit
            editField = progUI.editField();

            if (editField != 0) {
                Seperate.clearScreen();
                Parts.header("Edit Programme Details");             

                Parts.sectionHeader("Current Programme Details");       // show again the current programme details
                System.out.println(prog.toString() + "\n");                            
                
                // ask for input based on selected edit field
                switch (editField) {
                    case 1:
                        prog.setProgCode(progUI.inputProgCode());
                        break;
                    case 2:
                        prog.setProgName(progUI.inputProgName());
                        break;
                    case 3:
                        prog.setProgDurationYear(progUI.inputProgDurationYear());
                        break;
                }

                Seperate.clearScreen();
                Parts.header("Edit Programme Details"); 
                
                // show the edited details
                Parts.sectionHeader("Programme Details");             
                System.out.println(prog.toString() + "\n");
                confirmEdit = Validate.yesNoInput("Confirm to save? (Y)es/(N)o > ", "Invalid input! Please enter Y or N only.");

                if (confirmEdit == 'Y') {
                    for (int i = 1; i <= progSortedList.getNumberOfEntries(); i++) {
                        if (i == resultIndex) {
                            progSortedList.getEntry(i).setProgCode(prog.getProgCode());
                            progSortedList.getEntry(i).setProgName(prog.getProgName());
                            progSortedList.getEntry(i).setProgDurationYear(prog.getProgDurationYear());

                            // write entire sorted list into file
                            ProgrammeDAO.writeProgToFile(fileName, progSortedList);
                            
                            System.out.println("\nEdited programme saved successfully!\n");
                        }
                    }
                }

            }

        } else {
            System.out.println("No such programme found.");
        }
        Seperate.systemPause();
    }

    public void listAllProg() {
        Parts.header("List All Programme");
        Parts.sectionHeader("Programme List");
        if (!progSortedList.isEmpty()) {
            Iterator<Programme> it = progSortedList.getIterator();
            System.out.println("");
            System.out.println("   Code  Name                              Duration Year");
            System.out.println("  -------------------------------------------------------");
            while (it.hasNext()) {
                Programme prog = it.next();
                System.out.printf("   %-5s %-40s %-5d\n", prog.getProgCode(), prog.getProgName(), prog.getProgDurationYear());
            }

        } else {
            System.out.println("No programme found.");
        }
        System.out.println("");
        Seperate.systemPause();
    }

    public void findProgramme() {
        Programme prog;
        char next;

        do {
            Parts.header("Find A Programme");
            String code = progUI.inputProgCode();
            prog = searchResult(code);

            if (prog != null) {
                System.out.println("");
                Parts.sectionHeader("Search Result");
                System.out.println(prog.toString());
            } else {
                System.out.println("No such programme found.");
            }
            System.out.println("");
            next = Validate.yesNoInput("Continue to search another programme? (Y)es/(N)o > ", "  Character input only.");
        } while (next == 'Y');
    }

    public Programme searchResult(String code) {
        Programme result;

        for (int i = 1; i <= progSortedList.getNumberOfEntries(); i++) {
            result = progSortedList.getEntry(i);

            if ((result.getProgCode().equalsIgnoreCase(code))) {
                // return programme if found
                return result;
            }
        }
        return null;
    }

    public int searchResultIndex(String code) {

        for (int i = 1; i <= progSortedList.getNumberOfEntries(); i++) {
            if ((progSortedList.getEntry(i).getProgCode().equalsIgnoreCase(code))) {
                // return index if found
                return i;
            }
        }
        return -1;
    }

}
