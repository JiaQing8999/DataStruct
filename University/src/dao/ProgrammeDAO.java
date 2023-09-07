package dao;

import adt.SortedArrayList;
import adt.SortedLinkedList;
import adt.SortedListInterface;
import entity.Programme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author Khor Zhi Ying
 */
public class ProgrammeDAO {

//    public static SortedListInterface<Programme> readProgFromFile(String fileName) {
//        SortedListInterface<Programme> progList = new SortedLinkedList<>();
//        SortedListInterface<String> tutorialGrpList = readProgTutorialGroupFromFile("programmeTutorialGroup.txt");
//
//        try (
//                BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                // Split the line into parts using the pipe "|" as a delimiter
//                String[] parts = line.split("\\|");
//
//                // Check if there are enough parts to create object
//                if (parts.length >= 3) {
//                    String progCode = parts[0];
//                    String progName = parts[1];
//                    int progDurationYear = Integer.parseInt(parts[2]);
//
//                    // Create a new list for each Programme object
//                    SortedListInterface<String> tempTutorialGrpList = new SortedArrayList<>();
//
//                    for (int i = 0; i < tutorialGrpList.getNumberOfEntries(); i++) {
//                        if (tutorialGrpList.getEntry(i).substring(0, 3).equalsIgnoreCase(progCode)) {
//                            tempTutorialGrpList.add(tutorialGrpList.getEntry(i).substring(3));
//                        }
//                    }
//
//                    // Create a object and add it to the list
//                    Programme prog = new Programme(progCode, progName, progDurationYear, tempTutorialGrpList);
//                    progList.add(prog);
//                } else {
//                    // Handle invalid data or log an error
//                    System.err.println("Invalid data: " + line);
//                }
//            }
//        } catch (IOException e) {
//            // Handle any IO exceptions (e.g., file not found)
//            e.printStackTrace();
//        }
//
//        return progList;
//    }
    public static SortedListInterface<Programme> readProgFromFile(String fileName) {
        SortedListInterface<Programme> progList = new SortedLinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the pipe "|" as a delimiter
                String[] parts = line.split("\\|");

                // Check if there are enough parts to create an object
                if (parts.length >= 4) {
                    String progCode = parts[0];
                    String progName = parts[1];
                    int progDurationYear = Integer.parseInt(parts[2]);
                    String[] tutorialGroupsArray = parts[3].split("%");

                    // Create a SortedArrayList to store the tutorial groups
                    SortedListInterface<String> tutorialGroups = new SortedArrayList<>();
                    for (String group : tutorialGroupsArray) {
                        tutorialGroups.add(group);
                    }

                    // Create a Programme object and add it to the list
                    Programme prog = new Programme(progCode, progName, progDurationYear, tutorialGroups);
                    progList.add(prog);
                } else {
                    // Handle invalid data or log an error
                    System.err.println("Invalid data: " + line);
                }
            }
        } catch (IOException e) {
            // Handle any IO exceptions (e.g., file not found)
            e.printStackTrace();
        }

        return progList;
    }

//    public static void writeProgToFile(String fileName, SortedListInterface<Programme> progList) {
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//
//            // Get an iterator for the list
//            Iterator<Programme> iterator = progList.getIterator();
//
//            while (iterator.hasNext()) {
//                Programme prog = iterator.next();
//                // Write data into file
//                String progData = prog.writeFileFormatData();
//                writer.write(progData);
//                writer.newLine(); // Add a newline after each entry
//            }
//
//            writer.close(); // Close the BufferedWriter
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Error writing to file \"" + fileName + "\" : " + e.getMessage());
//        }
//    }
    public static void writeProgToFile(String fileName, SortedListInterface<Programme> progList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Get an iterator for the list
            Iterator<Programme> iterator = progList.getIterator();

            while (iterator.hasNext()) {
                Programme prog = iterator.next();
                // Write data into file
                String progData = prog.writeFileFormatData();
                writer.write(progData);
                writer.newLine(); // Add a newline after each entry
            }

            writer.close(); // Close the BufferedWriter
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file \"" + fileName + "\" : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(readProgFromFile("programme.txt"));
        //System.out.println(readProgTutorialGroupFromFile("programmeTutorialGroup.txt"));
    }

}
