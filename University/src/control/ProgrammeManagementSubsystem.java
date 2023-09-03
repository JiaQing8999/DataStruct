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
            }

        } while (selection != 0);
    }

    public void addNewProgramme() {

        Programme newProg = new Programme();
        char next;

        do {
            Seperate.clearScreen();
            Parts.header("Add new Programme");
            
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

        Seperate.systemPause();
    }

}
