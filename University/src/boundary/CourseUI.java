/*
 * @author sowyichin
 */
package boundary;

import entity.Course;
import java.util.Calendar;
import utility.Validate;

public class CourseUI {

    //Main menu of course module
    public int getCourseMenuSelection() {
        String[] menuOptions = {
            "Add course",
            "Remove course",
            "Find course",
            "Amend course details",
            "List all course",
            "Add programme to course",
            "Remove programme from course",
            "Generate reports"};
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

    public int inputCourseYearCommenced() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public Course inputProductDetails() {
        String courseID = inputCourseID();
        String courseName = inputCourseName();
        String courseDescription = inputCourseDescription();
        int courseCreditHours = inputCourseCreditHours(courseID);
        int courseYearCommenced = inputCourseYearCommenced();
        return new Course(courseID, courseName, courseDescription, courseCreditHours, courseYearCommenced, null);
    }

    public void printCourseDetails(Course course) {
        System.out.println("Course ID  Course name                       Course description               Credit Hours   Year Commenced");
        System.out.println("***********************************************************************************************************");
        System.out.printf("%9s  %-30s    %-40s %4d   %14d", course.getCourseID(), course.getCourseName(),
                course.getCourseDescription(), course.getCourseCreditHours(), course.getCourseYearCommenced());
        System.out.println("");
    }
}
