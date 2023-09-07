package entity;

import adt.*;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Lim Jia Qing
 */
public class Tutor implements Comparable<Tutor> {

    private String tutorID;
    private String name;
    private char gender;
    private String ic;
    private String contactNum;
    private String faculty;

    // Constructors
    public Tutor() {
        // Default constructor
    }

    public Tutor(String tutorID, String name, char gender, String ic, String contactNum, String faculty) {
        this.tutorID = tutorID;
        this.name = name;
        this.gender = gender;
        this.ic = ic;
        this.contactNum = contactNum;
        this.faculty = faculty;
    }

    // Getter and Setter methods
    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        String genderFull = null;
        switch (gender) {
            case 'M':
                genderFull = "Male";
                break;
            case 'F':
                genderFull = "Female";
                break;
        }
        return "\nTutor ID : " + tutorID
                + "\nName : " + name
                + "\nGender : " + genderFull
                + "\nIC Number : " + ic
                + "\nContact Number : " + contactNum
                + "\nFaculty : " + faculty;
    }

    @Override
    public int compareTo(Tutor o) {
        // Compare the tutorIDs of the current Tutor and the other Tutor
        return this.tutorID.compareTo(o.tutorID);
    }

    // Comparison method for tutor ID
    public int compareTutorID(Tutor otherTutor) {
        return this.tutorID.compareTo(otherTutor.getTutorID());
    }

    // Comparison method for name
    public int compareName(Tutor otherTutor) {
        return this.name.compareTo(otherTutor.getName());
    }

    // Comparison method for gender
    public int compareGender(Tutor otherTutor) {
        return Character.compare(this.gender, otherTutor.getGender());
    }

    // Comparison method for faculty
    public int compareFaculty(Tutor otherTutor) {
        return this.faculty.compareTo(otherTutor.getFaculty());
    }

    public String generateNewTutorID(SortedListInterface<Tutor> tutorSortedList) {
        // Initialize the new ID
        String newID = null;

        // Define the format of the tutor ID
        String idPrefix = "TU";
        int idLength = 6; // Length of the numeric part of the ID
        int startingID = 1; // The starting numeric part of the ID

        // Create an iterator for the sorted list
        Iterator<Tutor> iterator = tutorSortedList.getIterator();

        while (iterator.hasNext()) {
            Tutor currentTutor = iterator.next();

            // Construct the expected ID (e.g., "TU000001")
            String expectedID = idPrefix + String.format("%0" + idLength + "d", startingID);

            // Compare the current tutor's ID with the expected ID
            if (!currentTutor.getTutorID().equals(expectedID)) {
                // Found a missing ID, use it as the new ID
                newID = expectedID;
                break; // Exit the loop
            }

            // Increment the starting ID for the next iteration
            startingID++;
        }

        // If no missing ID was found, generate a new ID based on the last known ID
        if (newID == null) {
            newID = idPrefix + String.format("%0" + idLength + "d", startingID);
        }

        return newID;
    }

    public String formatTutorData() {
        return tutorID + "|" + name + "|" + gender + "|" + ic + "|" + contactNum + "|" + faculty;
    }

    public static String tutorIdInput(String promptMsg, String errorMsg) {
        String id;
        String formatRegex = "TU\\d{6}"; // Define the required format (e.g., TU followed by 6 digits)

        do {
            id = Validate.stringNullCheckingInput(promptMsg, errorMsg); // Get non-empty input using stringNullCheckingInput

            // Check if the input matches the required format
            if (!id.matches(formatRegex)) {
                System.out.println(errorMsg); // Display the error message if the format is invalid
            }
        } while (!id.matches(formatRegex)); // Continue until a valid tutor ID is provided

        return id;
    }
}
