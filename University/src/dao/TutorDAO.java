package dao;

import adt.*;
import entity.Tutor;
import java.io.*;
import java.util.Iterator;

/**
 *
 * @author Lim Jia Qing
 */
public class TutorDAO {

    public static SortedListInterface<Tutor> readTutorsFromFile(String fileName) {
        SortedListInterface<Tutor> tutorList = new SortedLinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the pipe "|" as a delimiter
                String[] parts = line.split("\\|");

                // Check if there are enough parts to create a Tutor object
                if (parts.length >= 6) {
                    String tutorID = parts[0];
                    String name = parts[1];
                    char gender = parts[2].charAt(0);
                    String ic = parts[3];
                    String contactNum = parts[4];
                    String faculty = parts[5];

                    // Create a Tutor object and add it to the list
                    Tutor tutor = new Tutor(tutorID, name, gender, ic, contactNum, faculty);
                    tutorList.add(tutor);
                } else {
                    // Handle invalid data or log an error
                    System.err.println("Invalid data: " + line);
                }
            }
        } catch (IOException e) {
            // Handle any IO exceptions (e.g., file not found)
            e.printStackTrace();
        }

        return tutorList;
    }

    public static void writeTutorsToFile(String fileName, SortedListInterface<Tutor> tutorList) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // Get an iterator for the tutorList
            Iterator<Tutor> iterator = tutorList.getIterator();

            while (iterator.hasNext()) {
                Tutor tutor = iterator.next();
                // Format the tutor data as needed and write it to the file
                String tutorData = tutor.formatTutorData();
                writer.write(tutorData);
                writer.newLine(); // Add a newline after each tutor entry
            }

            writer.close(); // Close the BufferedWriter
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file \"" + fileName + "\" : " + e.getMessage());
        }
    }

}
