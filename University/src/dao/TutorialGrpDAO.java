package dao;
import adt.BinarySearchTree;
import adt.BinarySearchTreeInterface;
import entity.TutorialGroup;
import java.io.*;
import java.util.Iterator;

/**
 *
 * @author Phua Mei Kee
 */

public class TutorialGrpDAO {

    
    public static BinarySearchTreeInterface<TutorialGroup> readTutorialGrpFromFile(String fileName) {
        BinarySearchTreeInterface<TutorialGroup> group = new BinarySearchTree<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the pipe "|" as a delimiter
                String[] parts = line.split("\\|");

                // Check if there are enough parts to create a Tutor object
                if (parts.length >= 3) {
                    int studentId = Integer.parseInt(parts[0]);
                    String studentName = parts[1];
                    int tutorialGrpNum = Integer.parseInt(parts[2]);

                    // Create a TutorialGroup object and add it to the list
                    TutorialGroup tutorialGrp = new TutorialGroup(studentId, studentName, tutorialGrpNum);
                    group.add(tutorialGrp);
                } else {
                    // Handle invalid data or log an error
                    System.err.println("Invalid data: " + line);
                }
            }
        } catch (IOException e) {
            // Handle any IO exceptions (e.g., file not found)
            e.printStackTrace();
        }

        return group;
    }
    
    public static void writeTutorialGrpToFile(String fileName, BinarySearchTreeInterface<TutorialGroup> tutorialGrpList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Get an iterator for the tutorialGrpList
            Iterator<TutorialGroup> iterator = tutorialGrpList.getInorderIterator();

            while (iterator.hasNext()) {
                TutorialGroup tutorialGrp = iterator.next();
                // Format the tutorialGrp data and write it to the file
                String tutorData = tutorialGrp.formatTutorialGroupData();
                writer.write(tutorData);
                writer.newLine(); // Add a newline after each tutor entry
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file \"" + fileName + "\" : " + e.getMessage());
        }
    }
}
