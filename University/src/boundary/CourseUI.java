/*
 * @author sowyichin
 */
package boundary;

import entity.Course;
import java.util.Calendar;
import java.util.Scanner;
import utility.Validate;

public class CourseUI {

    Scanner scanner = new Scanner(System.in);

    //Main menu of course module
    public int getCourseMenuSelection() {
        String[] menuOptions = {
            "Add course", "Remove course", "Find course", "Amend course details",
            "Add programme to course", "Remove programme from course", "Generate reports"};
        Parts.header("Course");
        return Parts.menu(menuOptions, "Close");
    }

    public String inputCourseID() {
        String courseID = Validate.stringNullCheckingInput("Course ID : ", "  This field cannot be empty.");
        return courseID;
    }

    public String inputCourseName() {
        String courseName = Validate.stringNullCheckingInput("Course name : ", "  This field cannot be empty.");
        return courseName;
    }

    public String inputCourseDescription() {
        String courseDescription = Validate.stringNullCheckingInput("Course description : ", "  This field cannot be empty.");
        return courseDescription;
    }

    public int inputCourseCreditHours(String courseID) {
        char last = courseID.charAt(courseID.length() - 1);
        int courseCreditHours = Character.getNumericValue(last);
        return courseCreditHours;
    }

    public Course inputProductDetails() {
        String courseID = inputCourseID();
        String courseName = inputCourseName();
        String courseDescription = inputCourseDescription();
        int courseCreditHours = inputCourseCreditHours(courseID);
        Calendar calendar = Calendar.getInstance();
        int courseYearCommenced = calendar.get(Calendar.YEAR);
        return new Course(courseID, courseName, courseDescription, courseCreditHours, courseYearCommenced, null);
    }
}
