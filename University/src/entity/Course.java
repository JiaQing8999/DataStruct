/*
 * @author sowyichin
 */

package entity;

import java.util.Objects;

public class Course {
    
    private String courseID;
    private String courseName;
    private String courseDescription;
    private int courseCreditHours;
    private int courseYearCommenced;
    //private Programme[] programmeID;

    public Course() {
    }

    public Course(String courseID) {
        this.courseID = courseID;
    }

    //Full constructor here 
    
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
    
    //get and set for programme data field

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
     
    //format need to write
    @Override
    public String toString() {
        return String.format("%-8s",courseID);
    }
}
