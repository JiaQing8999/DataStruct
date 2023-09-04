package control;

import adt.*;
import boundary.*;
import dao.*;
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
                case 2:
                    listAllProg();
                    break;
            }

        } while (selection != 0);
    }

    public void addNewProgramme() {

        Programme newProg = new Programme();
        char next;
        String code;

        do {
            Seperate.clearScreen();
            Parts.header("Add new Programme");
            
//            do{
//                code = progUI.inputProgCode();
//                
//            }while();
            
            newProg.setProgCode(progUI.inputProgCode());
            newProg.setProgName(progUI.inputProgName());
            newProg.setProgDurationYear(progUI.inputProgDurationYear());
            
            Seperate.clearScreen();
            Parts.header("Add new Programme");
            System.out.println("New Programme details" + "\n---------------------");
            System.out.println(newProg.toString() + "\n");

            char confirm = Validate.yesNoInput("Comfirm to add this programme? (Y)es/(N)o > ", "Invalid input! Please enter Y or N only.");

            if (confirm == 'Y') {
                // Add new entry to Sorted List
                if (progSortedList.add(newProg)) {
                    System.out.println("New programme added successfully!");
                }
            } else {
                System.out.println("No new programme added.");
            }
            //Ask whether have another entry
            next = Validate.yesNoInput("Add another new programme? (Y)es/(N)o > ", "Invalid input! Please enter Y or N only.");
        } while (next == 'Y');
        
        //write entire sorted list into file
        ProgrammeDAO.writeProgToFile(fileName, progSortedList);
        System.out.println("\n\nSaving data...");

        Seperate.systemPause();
    }
    
    public void listAllProg(){
        Parts.header("List All Programme");
        Parts.sectionHeader("Programme List");
        if (progSortedList.isEmpty()) {
            System.out.println("No programme found.");
        } else {
            Iterator<Programme> iterator = progSortedList.getIterator();
            System.out.println(" Code  Name                              Duration Year");
            System.out.println("-------------------------------------------------------");
            while (iterator.hasNext()) {
                Programme prog = iterator.next();
                
                System.out.printf(" %-5s %-40s %-5d\n", prog.getProgCode(), prog.getProgName(), prog.getProgDurationYear());
            }
        }
        System.out.println("");
        Seperate.systemPause();
    }

}
