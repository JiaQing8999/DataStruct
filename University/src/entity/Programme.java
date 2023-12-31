package entity;

import adt.*;
import java.util.Objects;

/**
 *
 * @author Khor Zhi Ying
 */
public class Programme implements Comparable<Programme> {

    private String progCode;
    private String progName;
    private int progDurationYear;
    private ListInterface<String> tutorialGroup = new SortedArrayList<>();

    public Programme() {
    }

    public Programme(String progCode, String progName, int progDurationYear) {
        this.progCode = progCode;
        this.progName = progName;
        this.progDurationYear = progDurationYear;
    }

    public Programme(String progCode, ListInterface<String> tutorialGroup) {
        this.progCode = progCode;
        this.tutorialGroup = tutorialGroup;
    }

    public Programme(String progCode, String progName, int progDurationYear, ListInterface<String> tutorialGroup) {
        this.progCode = progCode;
        this.progName = progName;
        this.progDurationYear = progDurationYear;
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

    public int getProgDurationYear() {
        return progDurationYear;
    }

    public void setProgDurationYear(int progDurationYear) {
        this.progDurationYear = progDurationYear;
    }

    public ListInterface<String> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(ListInterface<String> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String writeFileFormatData() {
        String str = "";
        for (int i = 0; i < tutorialGroup.getNumberOfEntries(); i++) {
            str += tutorialGroup.getEntry(i);
            if (i < tutorialGroup.getNumberOfEntries() - 1) {
                str += "%";
            }
        }
        return progCode + "|" + progName + "|" + progDurationYear + "|" + str;
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
        String str = "  Programme Code          : " + progCode + "\n"
                + "  Programme Name          : " + progName + "\n"
                + "  Programme Duration Year : " + progDurationYear + "\n";

        return str;
    }

    public String toStringForTutorial() {
        String str = "  Programme Code          : " + progCode + "\n"
                + "  Group                   : ";

        for (int i = 0; i < tutorialGroup.getNumberOfEntries(); i++) {
            str += tutorialGroup.getEntry(i);
            if (i < tutorialGroup.getNumberOfEntries() - 1) {
                str += ", ";
            }
        }

        return "\b" + str.toString();
    }

    @Override
    public int compareTo(Programme o) {
        return this.progCode.compareTo(o.progCode);
    }

}
