package control;

import boundary.*;
import entity.Tutor;

/**
 *
 * @author Lim Jia Qing
 */
public class ManageTutor {

    private TutorManagementUI tutorUI = new TutorManagementUI();

    public static void main(String[] args) {
        ManageTutor manageTutor = new ManageTutor();
        manageTutor.runManageTutor();
    }

    public void runManageTutor() {

        int selection;

        do {
            selection = tutorUI.getTutorMenuSelection();
            switch (selection) {
                case 1:
                    //Add tutor
                    break;
                case 2:
                    //Find tutor
                    break;
                case 3:
                    //Amend tutor details
                    break;
                case 4:
                    //Tutors list
                    break;
                case 5:
                    //Remove tutor
                    break;
                case 6:
                    //Generate reports
                    break;
                case 0:
                    System.out.println("Saving Data...");
                    //Close
                    System.out.println("Close.");
                    break;
            }
        } while (selection != 0);
    }

    public void addNewTutor() {
        Parts.header("Add a tutor");
        
        Tutor newTutor = new Tutor();
        newTutor.setName(tutorUI.inputName());
        newTutor.setGender(tutorUI.inputGender());
        newTutor.setIc(tutorUI.inputIC());
        newTutor.setContactNum(tutorUI.inputContactNum());
        newTutor.setFaculty(tutorUI.selectFaculty());
        
    }
}
