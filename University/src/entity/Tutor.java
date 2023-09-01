package entity;

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

    public String formatTutorData() {
        return tutorID + "|" + name + "|" + gender + "|" + ic + "|" + contactNum + "|" + faculty;
    }
}
