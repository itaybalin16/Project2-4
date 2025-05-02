import java.util.Arrays;
import java.util.Objects;

public class Department {
    private String departmentName;
    private int numOfStudent;
    private Teacher[] techersArray;


    public Department(String departmentName, int numOfStudent, Teacher[] techersArray) {
        this.departmentName = departmentName;
        this.numOfStudent = numOfStudent;
        this.techersArray = techersArray;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public Teacher[] getTechersArray() {
        return techersArray;
    }

    public void setTechersArray(Teacher[] techersArray) {
        this.techersArray = techersArray;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(departmentName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", numOfStudent=" + numOfStudent +
                ", techersArray=" + Arrays.toString(techersArray) +
                '}';
    }

}