import java.util.Arrays;
import java.util.Objects;

public class Teacher {

    private String name;
    private String id;
    private AcademicRank degree;
    private String degreeName;
    private int salary;
    private String department;
    private Committee[] committeesOfTeacher;

    public Teacher(String teacherFullName, String id, AcademicRank degree, String degreeName, int salary, Committee[] committeesOfTeacher) {
        this.name = teacherFullName;
        this.id = id;
        this.degree = degree;
        this.degreeName = degreeName;
        this.salary = salary;
        this.committeesOfTeacher = committeesOfTeacher;
    }

    public Teacher(String teacherFullName, String id, AcademicRank degree, String degreeName, int salary, String department, Committee[] committeesOfTeacher) {
        this(teacherFullName, id, degree, degreeName, salary, committeesOfTeacher);
        this.department = department;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teachers = (Teacher) o;
        return id.equals(teachers.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Teacher = " +
                "Teacher Full Name='" + name + '\'' +
                ", Degree='" + degree + '\'' +
                ", Degree Name='" + degreeName + '\'' +
                ", Department='" + department + '\''
                + ", Salary=" + salary
                + ", Committees=" + Arrays.toString(committeesOfTeacher);
    }

    public Committee[] getCommitteesOfTeacher() {
        return committeesOfTeacher;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AcademicRank getDegree() {
        return degree;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}