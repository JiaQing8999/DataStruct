package entity;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Phua Mei Kee
 */
public class TutorialGroup implements Comparable<TutorialGroup> {

    public static Iterator<TutorialGroup> getIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int studentId;
    private String studentName;
    private int group;


    public TutorialGroup() {
    }

    public TutorialGroup(int id) {
        this.studentId = id;
    }

    public TutorialGroup(int id, String studentName, int group) {
        this.studentId = id;
        this.studentName = studentName;
        this.group = group;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
        final TutorialGroup other = (TutorialGroup) obj;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (this.group != other.group) {
            return false;
        }
        return Objects.equals(this.studentName, other.studentName);
    }

    @Override
    public String toString() {
        return String.format("%6d %7s %18d", studentId, studentName, group);
    }

    @Override
    public int compareTo(TutorialGroup o) {
        if (this.studentId > o.studentId) {
            return 1;
        } else if (this.studentId == o.studentId) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public String formatTutorialGroupData() {
        return studentId + "|" + studentName + "|" + group;
    }

}
