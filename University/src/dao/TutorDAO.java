package dao;

import adt.LinkedList;
import entity.Tutor;
import java.io.*;

/**
 *
 * @author Lim Jia Qing
 */
public class TutorDAO {

    public static LinkedList<Tutor> readTutorsFromFile(String fileName) {
        LinkedList<Tutor> tutorList = new LinkedList<>();

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
    
    
}
