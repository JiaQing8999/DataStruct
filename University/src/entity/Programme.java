package entity;

import adt.ListInterface;
import adt.SortedLinkedList;
import adt.SortedListInterface;
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
    private SortedListInterface<Integer> tutorialGroup = new SortedLinkedList<>();

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
    public Programme(String progCode, String progName, int progYear, int progSem, SortedListInterface<Integer> tutorialGroup) {
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

    public SortedListInterface<Integer> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(SortedListInterface<Integer> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
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
        String str = "Programme Name: " + progName + "\n"
                + "Programme Code: " + progCode + "\n"
                + "Programme Year: " + progYear + "\n"
                + "Programme Sem: " + progSem + "\n\n";
        for (int i = 0; i < tutorialGroup.getNumberOfEntries(); i++) {
            str += "Group " + tutorialGroup.getEntry(i) + " ";
        }
        return "\b" + str;
    }

    public static void main(String[] args) {
        Programme p = new Programme();

        p.setProgCode("RSW");
        p.setProgName("Bachelor Degree of Software Enginnering");
        p.setProgYear(2);
        p.setProgSem(1);
        
        p.tutorialGroup.add(24);
        p.tutorialGroup.add(22);
        p.tutorialGroup.add(20);

        System.out.println(p.toString());
    }

}
