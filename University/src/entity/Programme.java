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
    private String[][] tutorialGroup;

    public Programme() {
    }

    public Programme(String progCode) {
        this.progCode = progCode;
    }

    public Programme(String progCode, String progName, int progYear, int progSem) {
        this.progCode = progCode;
        this.progName = progName;
        this.progYear = progYear;
        this.progSem = progSem;
    }

    public Programme(String progCode, int progYear, int progSem) {
        this.progCode = progCode;
        this.progYear = progYear;
        this.progSem = progSem;
    }

    public Programme(String progCode, String progName, int progYear, int progSem, String[][] tutorialGroup) {
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

    public String[][] getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String[][] tutorialGroup) {
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
        String str = progCode + progName + progYear + progSem;

        return str;
    }

}
