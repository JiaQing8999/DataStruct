package boundary;

import utility.Validate;

/**
 *
 * @author Khor Zhi Ying
 */
public class ProgrammeUI {

    public int getProgrammeMenuSelection() {
        String[] menuOptions = {
            "Add new programme",
            "Remove programme",
            "Edit programme details",
            "Find programme",
            "List all programme",
            "Add tutorial group to a programme",
            "Remove tutorial group from a programme",
            "List all tutorial group for a programme",
            "Generate report"
        };
        Parts.header("Programme Management");
        return Parts.menu(menuOptions, "Close");
    }

    public String inputProgCode() {
        String code = "";

        do {
            code = Validate.stringNullCheckingInput("Enter programme code (eg. RSW) : ",
                    "  Programme code cannot be null.");

            if (code.length() != 3) {
                System.out.println("Invalid programme code! Please enter again.");
            }

        } while (code.length() != 3);

        return code.toUpperCase();
    }

    public String inputProgName() {
        String name = Validate.stringNullCheckingInput("Enter programme name (eg. Bachelor Degree of Software Engineering) : ",
                "  Programme name cannot be null.");

        return name;
    }
    
    public int inputProgDurationYear() {
        int year = 0;

        do {
            year = Validate.intInput("Enter programme duration year (1-3) : ",
                    "Programme duration year cannot be null and only accept number.");

            if (year < 1 || year > 3) {
                System.out.println("Invalid programme duration year! Please enter 1-3 only.");
            }

        } while (year < 1 || year > 3);

        return year;
    }

    public int inputProgYear() {
        int year = 0;

        do {
            year = Validate.intInput("Enter programme year (1-3) : ",
                    "Programme year cannot be null and only accept number.");

            if (year < 1 || year > 3) {
                System.out.println("Invalid programme year! Please enter 1-3 only.");
            }

        } while (year < 1 || year > 3);

        return year;
    }

    public int inputProgSem() {
        int sem = 0;

        do {
            sem = Validate.intInput("Enter programme sem (1-3) : ",
                    "Programme sem cannot be null and only accept number.");

            if (sem < 1 || sem > 3) {
                System.out.println("Invalid programme sem! Please enter 1-3 only.");
            }

        } while (sem < 1 || sem > 3);

        return sem;
    }

    public int inputTutorialGroup() {
        int group = 0;

        do {
            group = Validate.intInput("Enter tutorial group : Group ",
                    "Tutorial group cannot be null.");

            if (group < 1) {
                System.out.println("Invalid tutorial group! Please enter again.");
            }

        } while (group < 1);

        return group;
    }

    public int inputNumPaxInTutorialGroup() {
        int num = 0;

        do {
            num = Validate.intInput("Number of student in this tutorial group : ",
                    "Number of student cannot be null and only accept number.");

            if (num < 0) {
                System.out.println("Invalid number of student! Number of student cannot be negative. Please enter again.");
            }

        } while (num < 0);

        return num;
    }
}
