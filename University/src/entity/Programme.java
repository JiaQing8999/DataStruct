package entity;

import java.util.Objects;

/**
 *
 * @author Khor Zhi Ying
 */
public class Programme {

    private String progCode;
    private String progName;
    private int progYear;
    private int progSem;
    private int[] tutorialGroup;

    public Programme() {
    }

    // search and remove programme by id
    public Programme(String progCode) {
        this.progCode = progCode;
    }

    // add programme
    public Programme(String progCode, String progName, int progYear, int progSem) {
        this.progCode = progCode;
        this.progName = progName;
        this.progYear = progYear;
        this.progSem = progSem;
    }

    // list tutorial belong to this programme
    public Programme(String progCode, int progYear, int progSem) {
        this.progCode = progCode;
        this.progYear = progYear;
        this.progSem = progSem;
    }

    // list all the things
    public Programme(String progCode, String progName, int progYear, int progSem, int[] tutorialGroup) {
        this.progCode = progCode;
        this.progName = progName;
        this.progYear = progYear;
        this.progSem = progSem;
        this.tutorialGroup = tutorialGroup;
    }

    public String getProgCode() {
        return progCode;
    }

    public void setProgCode(String progCode) {
        this.progCode = progCode;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public int getProgYear() {
        return progYear;
    }

    public void setProgYear(int progYear) {
        this.progYear = progYear;
    }

    public int getProgSem() {
        return progSem;
    }

    public void setProgSem(int progSem) {
        this.progSem = progSem;
    }

    public int[] getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(int[] tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 71 * hash + Objects.hashCode(this.progCode);
//        return hash;
//    }
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
        final Programme other = (Programme) obj;
        return Objects.equals(this.progCode, other.progCode);
    }

    @Override
    public String toString() {
        String str = "Programme Name: " + progName + "\n"
                + "Programme Code: " + progCode + "\n"
                + "Programme Year: " + progYear + "\n"
                + "Programme Sem: " + progSem + "\n\n" ;
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                str += tutorialGroup[i][j] + " ";
//            }
//            str += "\n";
//        }
        return str;
    }
    
    public static void main(String[] args) {
        Programme p = new Programme();
        
        p.setProgCode("RSW");
        p.setProgName("Bachelor Degree of Software Enginnering");
        p.setProgYear(2);
        p.setProgSem(1);
        
        System.out.println(p.toString());
    }

}
