package control;

/**
 *
 * @author Phua Mei Kee
 */

import adt.ArrayQueue;
import adt.BinarySearchTree;
import adt.BinarySearchTreeInterface;
import adt.QueueInterface;
import boundary.Parts;
import boundary.TutorialGroupUI;
import dao.TutorialGrpDAO;
import entity.TutorialGroup;
import java.util.Iterator;
import java.util.Scanner;
import utility.Seperate;
import utility.Validate;

public class TestTutorialGroup {

    Scanner scanner = new Scanner(System.in);
    TutorialGroup group = new TutorialGroup();
    BinarySearchTreeInterface<TutorialGroup> bTree = new BinarySearchTree<>();
    QueueInterface<TutorialGroup> addQueue = new ArrayQueue<>();
    QueueInterface<TutorialGroup> changeQueue = new ArrayQueue<>();
    TutorialGroupUI tGrpUI = new TutorialGroupUI();
    private final String fileName = "tutorialGrp.txt";
    int numDel, numAdd = 0;

    public TestTutorialGroup() {

        bTree = TutorialGrpDAO.readTutorialGrpFromFile(fileName);

    }

    public static void main(String[] args) {

        TestTutorialGroup tg = new TestTutorialGroup();
        tg.runTutorialGroupTest();
    }

    //menu
    public void runTutorialGroupTest() {
        int selection;

        do {
            selection = tGrpUI.getTutorialGrpMenuSelection();
            switch (selection) {
                case 1 :
                    addStudent();
                    break;
                case 2 :
                    removeStudent();
                    break;
                case 3 :
                    changeTGroup();
                    break;
                case 4 :
                    findStudent();
                    break;
                case 5 :
                    listATGroup();
                    break;
                case 6 :
                    filterTGroup();
                    break;
                case 7 :
                    report();
                    break;
                case 0 :
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);

    }

    public void addStudent() {
        char cont;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Add Student");

            String newName = tGrpUI.inputStudentName();
            int newId = tGrpUI.inputStudentId();
            int newTG = tGrpUI.inputNewTutorialGroup();

            //show the student need to add                
            TutorialGroup addStudent = new TutorialGroup(newId, newName, newTG);

            System.out.println("\nID         Student Name    Tutorial Group\n");
            System.out.println("------------------------------------------------\n");
            System.out.println(newId + "    " + newName + "            " + newTG);
            System.out.println("\n\n");

            char confirm = Validate.yesNoInput("Comfirm to add ? (Y)es/(N)o > ", "  Character input only.");
            if (confirm == 'Y' || confirm == 'y') {
                bTree.add(addStudent);
                addQueue.enqueue(addStudent);
                numAdd++;
            }

            //loop 
            cont = Validate.yesNoInput("Add another student? (Y)es/(N)o > ", "  Character input only.");
        } while (cont == 'Y' || cont == 'y');
        //system pause
        TutorialGrpDAO.writeTutorialGrpToFile(fileName, bTree);
        Seperate.systemPause();
    }

    public void removeStudent() {
        char cont;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Remove a student");
            String delName = tGrpUI.inputStudentName();
            int delId = tGrpUI.inputStudentId();
            int delTGroup = tGrpUI.inputCurrentTutorialGroup();

            TutorialGroup deleteStudent = new TutorialGroup(delId, delName, delTGroup);

            //show the student need to delete
            System.out.println("\nID        Student Name          Tutorial Group\n");
            System.out.println("------------------------------------------------\n");
            System.out.println(bTree.getEntry(deleteStudent));
            System.out.println("\n\n");

            char confirm = Validate.yesNoInput("Comfirm to delete this student ? (Y)es/(N)o > ", "  Character input only.");
            if (confirm == 'Y' || confirm == 'y') {
                if (bTree.contains(deleteStudent)) {
                    bTree.remove(deleteStudent);
                    numDel++;
                    System.out.println("The student is deleted.\n");
                } else {
                    System.out.println("This student not found.\n");
                }
            }

            //loop 
            cont = Validate.yesNoInput("Remove another student? (Y)es/(N)o > ", "  Character input only.");
        } while (cont == 'Y' || cont == 'y');
        //system pause
        TutorialGrpDAO.writeTutorialGrpToFile(fileName, bTree);
        Seperate.systemPause();

    }

    public void changeTGroup() {

        char cont;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Change a student's tutorial group");

            //change tutorial group
            String changeName = tGrpUI.inputStudentName();
            int changeId = tGrpUI.inputStudentId();
            int changeOldTG = tGrpUI.inputCurrentTutorialGroup();
            int changeNewTG = tGrpUI.inputNewTutorialGroup();
            TutorialGroup oldTutorialgGrp = new TutorialGroup(changeId, changeName, changeOldTG);
            TutorialGroup newTutorialgGrp = new TutorialGroup(changeId, changeName, changeNewTG);

            //show the student need to delete
            char confirm = Validate.yesNoInput("Comfirm to change this student' tutorial group ? (Y)es/(N)o > ", "  Character input only.");
            if (confirm == 'Y' || confirm == 'y') {
                if (bTree.contains(oldTutorialgGrp)) {
                    bTree.remove(oldTutorialgGrp); // Remove the student from the old group
                    bTree.add(newTutorialgGrp); // Add the student to the new group
                    System.out.println("Student's tutorial group changed.");
                } else {
                    System.out.println("Student not found.");
                }

            }

            //loop 
            cont = Validate.yesNoInput("Change another student's tutorial group? (Y)es/(N)o > ", "  Character input only.");
        } while (cont == 'Y' || cont == 'y');
        //system pause
        TutorialGrpDAO.writeTutorialGrpToFile(fileName, bTree);
        Seperate.systemPause();

    }

    public void findStudent() {
        char cont;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("Search a student");
            //search a student in a tutorial group

            String searchName = tGrpUI.inputStudentName();
            int searchId = tGrpUI.inputStudentId();
            int searchTGroup = tGrpUI.inputCurrentTutorialGroup();

            TutorialGroup searchStudent = new TutorialGroup(searchId, searchName, searchTGroup);
            System.out.println("\nID      Student Name          Tutorial Group\n");
            System.out.println("------------------------------------------------\n");
            System.out.println(bTree.getEntry(searchStudent));
            System.out.println("\n\n");
            boolean found = bTree.contains(searchStudent);

            System.out.printf("The student is %sin group %d%n", found ? "" : "not ", searchTGroup);
            cont = Validate.yesNoInput("Continue to search another student? (Y)es/(N)o > ", "Character input only.");
        } while (cont == 'Y' || cont == 'y');
        //system pause
        Seperate.systemPause();
    }

    public void listATGroup() {
        //list all students of a specific tutorial grp
        char cont;

        do {
            //clear screen and header
            Seperate.clearScreen();
            Parts.header("List A Tutorial Group's Student");
            int showTGroup = tGrpUI.inputCurrentTutorialGroup();

            System.out.println(
                    "Tutorial Group " + showTGroup + " Students:");
            Iterator<TutorialGroup> it = bTree.getInorderIterator();

            System.out.println("\nID         Student Name    \n");
            System.out.println("--------------------------\n");
            while (it.hasNext()) {
                TutorialGroup student = it.next();
                if (student.getGroup() == showTGroup) {
                    System.out.println(student.getStudentId() + "    " + student.getStudentName());
                }
            }

            cont = Validate.yesNoInput("Continue to show a tutorial group's student? (Y)es/(N)o > ", "Character input only.");
        } while (cont == 'Y' || cont == 'y');
        //system pause
        Seperate.systemPause();
    }

    public void filterTGroup() {

        int selection;

        do {
            selection = tGrpUI.getFilterGroupMenuSelection();
            switch (selection) {
                case 1 :
                    mostStudentsTG();
                    break;
                case 2 :
                    leastStudentsTG();
                    break;
                case 0 :
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);

    }

    public void mostStudentsTG() {
        Seperate.clearScreen();
        Parts.header("Tutorial Group have most students");
        int maxStudents = -1; // Initialize with a small value
        int mostStudentsTG = -1; // Initialize to an invalid tutorial group number

        // Find the maximum tutorial group number
        int maxGroupNum = findMaxGroupNum();

        // Iterate through all tutorial groups
        for (int groupNum = 1; groupNum <= maxGroupNum; groupNum++) {
            int studentsInGroup = countStudentsInGroup(groupNum);
            if (studentsInGroup > maxStudents) {
                maxStudents = studentsInGroup;
                mostStudentsTG = groupNum;
            }
        }

        if (mostStudentsTG != -1) {
            System.out.println("Tutorial Group with Most Students: Tutorial Group " + mostStudentsTG);
            System.out.println("Number of Students: " + maxStudents);
        } else {
            System.out.println("No tutorial group found.");
        }

        Seperate.systemPause();
    }

// Helper method to find the maximum tutorial group number
    private int findMaxGroupNum() {
        int maxGroupNum = 0;

        Iterator<TutorialGroup> it = bTree.getInorderIterator();

        while (it.hasNext()) {
            TutorialGroup student = it.next();
            if (student.getGroup() > maxGroupNum) {
                maxGroupNum = student.getGroup();
            }
        }

        return maxGroupNum;
    }

// Helper method to count students in a specific tutorial group
    private int countStudentsInGroup(int groupNum) {
        int count = 0;
        Iterator<TutorialGroup> it = bTree.getInorderIterator();
        while (it.hasNext()) {
            TutorialGroup student = it.next();
            if (student.getGroup() == groupNum) {
                count++;
            }
        }
        return count;
    }

    public void leastStudentsTG() {
        Seperate.clearScreen();
        Parts.header("Tutorial Group have least students");
        // Initialize with a large value to find the minimum
        int minStudents = Integer.MAX_VALUE;
        int leastStudentsTG = -1; // Initialize to an invalid tutorial group number

        // Find the maximum tutorial group number
        int maxGroupNum = findMaxGroupNum();

        // Iterate through all tutorial groups
        for (int groupNum = 1; groupNum <= maxGroupNum; groupNum++) {
            int studentsInGroup = countStudentsInGroup(groupNum);
            if (studentsInGroup < minStudents) {
                minStudents = studentsInGroup;
                leastStudentsTG = groupNum;
            }
        }

        if (leastStudentsTG != -1) {
            System.out.println("Tutorial Group with Least Students: Tutorial Group " + leastStudentsTG);
            System.out.println("Number of Students: " + minStudents);
        } else {
            System.out.println("No tutorial group found.");
        }

        Seperate.systemPause();
    }

    public int calcTotalStudents() {
        int totalStudent = 0;
        Iterator<TutorialGroup> it = bTree.getInorderIterator();

        while (it.hasNext()) {
            totalStudent++;
            //move to next student
            it.next();
        }

        return totalStudent;
    }

    public void report() {
        Seperate.clearScreen();
        Parts.header("Reports");
        // the first students
        System.out.println("The first student for this semester : \n" + bTree.getSmallestValue());
        // the last student
        System.out.println("\nThe last student for this semester : \n" + bTree.getLargestValue());
        // the total of students
        System.out.println("\nTotal students for this semester is " + calcTotalStudents() + "students");
        // add how many students(list), delete how many student(list),searched how many students
        System.out.println("\nStudents added this time: " + numAdd);
        System.out.println("\nID      Student Name          Tutorial Group\n");
        System.out.println("------------------------------------------------\n");
        for (int i = 0; i < numAdd; i++) {
            System.out.println(addQueue.dequeue());
        }

        System.out.println("\n\nStudents deleted this time: " + numDel);
        Seperate.systemPause();
    }
}
