/*
 * @author sowyichin
 */
package control;

import adt.DoublyLinkedList;
import adt.ListInterface;
import boundary.CourseUI;
import boundary.Parts;
import entity.Course;
import java.util.Iterator;
import java.util.Scanner;
import utility.Seperate;
import utility.Validate;

public class CourseTest {

    CourseUI cui = new CourseUI();
    ListInterface<Course> c = new DoublyLinkedList<>();
    Scanner s = new Scanner(System.in);

    public CourseTest() {
        c.add(new Course("AAMS3653", "Discrete Math", "Study mathematical structures", 3, 2023, null));
        c.add(new Course("AACS2383", "Data Mining", "Study data exploring", 3, 2023, null));
        c.add(new Course("BAIT2034", "Computer Network", "Study fundamental of network", 4, 2023, null));
        c.add(new Course("AACS1483", "Web Design", "Study web development", 3, 2023, null));
        c.add(new Course("BBMF3203", "Research Method", "Study research methodology", 3, 2023, null));
        c.add(new Course("BAIT2033", "Digital Multimedia", "Study creation of multimedia", 3, 2023, null));
    }

    public static void main(String[] args) {
        CourseTest ct = new CourseTest();
        ct.runCourseTest();
    }

    //menu
    public void runCourseTest() {
        int selection;
        do {
            selection = cui.getCourseMenuSelection();
            switch (selection) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    findCourse();
                    break;
                case 4:
                    amendCourse();
                    break;
                case 5:
                    listCourse();
                    break;
                case 6:
                    addProgramme();
                    break;
                case 7:
                    removeProgramme();
                    break;
                case 8:
                    generateReport();
                    break;
                case 0:
                    System.out.println("Saving Data...");
                    //Close
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);
    }

    //add course operation
    public void addCourse() {

        char next;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Add Course");

            //get input from user
            Course inputCourse = cui.inputProductDetails();
            System.out.println("");
            System.out.println("");

            //show the input and ask for confirmation
            System.out.println("Course ID   Course name                       Course description               Credit Hours   Year Commenced");
            System.out.println("************************************************************************************************************");
            System.out.printf("%9s   %-30s    %-40s %4d   %14d", inputCourse.getCourseID(), inputCourse.getCourseName(),
                    inputCourse.getCourseDescription(), inputCourse.getCourseCreditHours(), inputCourse.getCourseYearCommenced());
            System.out.println();
            System.out.println();

            char confirm = Validate.yesNoInput("Comfirm to add ? (Y)es/(N)o > ", "  Character input only.");
            if (confirm == 'Y' || confirm == 'y') {
                if (c.add(inputCourse)) {
                    System.out.println("Add successfully");
                }
            }

            //loop ? 
            next = Validate.yesNoInput("Add another course? (Y)es/(N)o > ", "  Character input only.");
        } while (next == 'Y' || next == 'y');
        //system pause
        Seperate.systemPause();
    }

    //remove course operation
    public void removeCourse() {

        int givenPosition;
        char next;

        do {
            int index = 1;
            //clear screen and header
            Parts.header("Remove Course");

            //iterator go through the list and print the list with no.
            System.out.println("No.  Course ID   Course name               ");
            System.out.println("*******************************************");

            //iterator go through the list
            Iterator<Course> it = c.getIterator();
            while (it.hasNext()) {
                Course cPrint = it.next();
                System.out.printf("%2d  %9s    %-30s    ", index, cPrint.getCourseID(), cPrint.getCourseName());
                index++;
                System.out.println();
            }
            System.out.println();

            //let user select no. of list to delete
            do {
                System.out.print("Enter the no.(1-" + c.getNumberOfEntries() + ") to delete : ");
                givenPosition = s.nextInt();
            } while (givenPosition < 1 || givenPosition > c.getNumberOfEntries());

            //ask user confirm to delete or not
            char confirm = Validate.yesNoInput("Comfirm to delete ? (Y)es/(N)o > ", "  Character input only.");
            if (confirm == 'Y' || confirm == 'y') {
                if (c.remove(givenPosition)) {
                    System.out.println("Delete successfully");
                }
            }

            //loop?
            next = Validate.yesNoInput("Delete another course? (Y)es/(N)o > ", "  Character input only.");
        } while (next == 'Y' || next == 'y');
        //system pause
        Seperate.systemPause();
    }

    //find course operation
    public void findCourse() {
        Parts.header("Find Course");

        Seperate.systemPause();
    }

    //amend course operation
    public void amendCourse() {

        int givenPosition, field;
        char next;

        do {
            int index = 1;
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Amend Course");
            System.out.println("No.  Course ID   Course name                       Course description");
            System.out.println("**********************************************************************");

            //iterator go through the list
            Iterator<Course> it = c.getIterator();
            while (it.hasNext()) {
                Course cPrint = it.next();
                System.out.printf("%2d  %9s    %-30s    %-40s ", index, cPrint.getCourseID(), cPrint.getCourseName(), cPrint.getCourseDescription());
                index++;
                System.out.println();
            }
            System.out.println();

            //let user select no. of list to delete
            do {
                System.out.print("Enter the no.(1-" + c.getNumberOfEntries() + ") to amend : ");
                givenPosition = s.nextInt();
            } while (givenPosition < 1 || givenPosition > c.getNumberOfEntries());

            Course courseAmend = c.getEntry(givenPosition);

            //display and ask which details need to amend
            System.out.println();
            System.out.println("1. Course name");
            System.out.println("2. Course description");
            do {
                System.out.print("Enter no. that want to amend : ");
                field = s.nextInt();
                if (field == 1) {
                    String newName = cui.inputCourseName();
                    courseAmend.setCourseName(newName);

                } else if (field == 2) {
                    String newDescription = cui.inputCourseDescription();
                    courseAmend.setCourseDescription(newDescription);
                }
            } while (field < 1 || field > 2);

            if (c.replace(givenPosition, courseAmend)) {
                System.out.println("Amend successfully");
            }

            //loop?
            next = Validate.yesNoInput("Amend another course? (Y)es/(N)o > ", "  Character input only.");
        } while (next == 'Y' || next == 'y');
        //system pause
        Seperate.systemPause();
    }

    //list course operation
    public void listCourse() {

        //clear screen and header
        Seperate.clearScreen();
        Parts.header("List all Course");
        System.out.println("Course ID   Course name                       Course description               Credit Hours   Year Commenced");
        System.out.println("************************************************************************************************************");

        //iterator go through the list
        Iterator<Course> it = c.getIterator();
        while (it.hasNext()) {
            Course cPrint = it.next();
            System.out.printf("%9s   %-30s    %-40s %4d   %14d", cPrint.getCourseID(), cPrint.getCourseName(),
                    cPrint.getCourseDescription(), cPrint.getCourseCreditHours(), cPrint.getCourseYearCommenced());
            System.out.println();
            System.out.println();
        }

        //footer and syatem pause
        System.out.println("************************************************************************************************************");
        System.out.println("Total of Course (s) : " + c.getNumberOfEntries());
        System.out.println();
        System.out.println();
        Seperate.systemPause();
    }

    //add programme operation
    public void addProgramme() {
        Parts.header("Add Programme to Course");

        Seperate.systemPause();
    }

    //remove course operation
    public void removeProgramme() {
        Parts.header("Remove Programme to Course");

        Seperate.systemPause();
    }

    //generate programme operation
    public void generateReport() {
        Parts.header("Generate Report");

        Seperate.systemPause();
    }

}
