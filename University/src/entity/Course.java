/*
 * @author sowyichin
 */
package entity;

import adt.DoublyLinkedList;
import adt.ListInterface;
import java.util.Objects;

public class Course {

    private String courseID;
    private String courseName;
    private String courseDescription;
    private int courseCreditHours;
    private int courseYearCommenced;
    private ListInterface<String> programmeID = new DoublyLinkedList<>();

    public Course() {
    }

    public Course(String courseID) {
        this.courseID = courseID;
    }

    public Course(String courseID, String courseName, String courseDescription, int courseCreditHours, int courseYearCommenced, ListInterface<String> programmeID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseCreditHours = courseCreditHours;
        this.courseYearCommenced = courseYearCommenced;
        this.programmeID = programmeID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public void setCourseCreditHours(int courseCreditHours) {
        this.courseCreditHours = courseCreditHours;
    }

    public int getCourseYearCommenced() {
        return courseYearCommenced;
    }

    public void setCourseYearCommenced(int courseYearCommenced) {
        this.courseYearCommenced = courseYearCommenced;
    }

    public ListInterface<String> getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(ListInterface<String> programmeID) {
        this.programmeID = programmeID;
    }

    //method to add a program ID to the course
    public void addProgrammeID(String programmeID) {
        if (this.programmeID == null) {
            this.programmeID = new DoublyLinkedList<>(); // Initialize with an empty list
        }
        this.programmeID.add(programmeID);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        return Objects.equals(this.courseID, other.courseID);
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", courseName=" + courseName + ", courseDescription=" + courseDescription + ", courseCreditHours=" + courseCreditHours + ", courseYearCommenced=" + courseYearCommenced + ", programmeID=" + programmeID + '}';
    }
}
