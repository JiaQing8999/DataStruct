package entity;

import adt.ListInterface;
import adt.SortedLinkedList;
import adt.SortedListInterface;
import java.util.Objects;

/**
 *
 * @author Khor Zhi Ying
 */
public class Programme implements Comparable<Programme>{

    private String progCode;
    private String progName;
    private int progDurationYear;
    private SortedListInterface<String> tutorialGroup = new SortedLinkedList<>();

    public Programme() {
    }

    public Programme(String progCode) {
        this.progCode = progCode;
    }

    public Programme(String progCode, String progName, int progDurationYear) {
        this.progCode = progCode;
        this.progName = progName;
        this.progDurationYear = progDurationYear;
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

    public int getProgDurationYear() {
        return progDurationYear;
    }

    public void setProgDurationYear(int progDurationYear) {
        this.progDurationYear = progDurationYear;
    }

    public SortedListInterface<String> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(SortedListInterface<String> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }
    
    public String writeFileFormatData(){
        return progCode + "|" + progName + "|" + progDurationYear;
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
        final Programme other = (Programme) obj;
        return Objects.equals(this.progCode, other.progCode);
    }

    @Override
    public String toString() {
        String str = "Programme Code: " + progCode + "\n"
                + "Programme Name: " + progName + "\n"
                + "Programme Duration Year: " + progDurationYear;
        
        return "\b" + str;
    }

    @Override
    public int compareTo(Programme o) {
        return this.progCode.compareTo(o.progCode);
    }

}
