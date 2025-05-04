public class CollegeManager {
    private Teacher[] teachers = new Teacher[1];
    private int numOfT = 0;
    private Committee[] committees = new Committee[1];
    private int numOfC = 0;
    private Department[] departments = new Department[1];
    private int numOfD = 0;
    private String collegeName;

    public CollegeManager(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void addTeacher(String name, String id, AcademicRank degree, String degreeName, int salary, String department, Committee[] committeesOfTeacher) {
        Teacher t = new Teacher(name, id, degree, degreeName, salary, department, committeesOfTeacher);

        if (numOfT == teachers.length) {
            teachers = extendArrTeachers(teachers);
        }

        teachers[numOfT++] = t;

    }

    public void addDepartment(String depName, int numStudents, Teacher[] teachersInDep) {
        Department d = new Department(depName, numStudents, teachersInDep);

        if (numOfD == departments.length) {
            departments = extendArrDepartment(departments);
        }

        departments[numOfD++] = d;

    }

    public void addCommittee(Teacher chairman, String committeeName, Teacher[] teachers) {
        Committee c = new Committee(chairman, committeeName, teachers);

        if (numOfC == committees.length) {
            committees = extendArrCommittee(committees);
        }

        committees[numOfC++] = c;
    }

    private void printArr(String[] arr, int numOf) {
        if (numOf == 0) {
            System.out.println("No names to print");
            return;
        }
        for (int i = 0; i < numOf; i++) {
            System.out.println(arr[i]);
        }
    }

    public Teacher getTeacher(String id) {
        for (int i = 0; i < numOfT; i++) {
            if (id.equals(teachers[i].getId())) {
                return teachers[i];
            }
        }
        return null;
    }

    public Department getDepartment(String name) {
        for (int i = 0; i < numOfD; i++) {
            if (name.equals(departments[i].getDepartmentName())) {
                return departments[i];
            }
        }
        return null;
    }

    public Committee getCommittee(String name) {
        for (int i = 0; i < numOfC; i++) {
            if (name.equals(committees[i].getCommitteeName())) {
                return committees[i];
            }
        }
        return null;
    }

    public boolean checkIfTeacherExists(String id) {
        for (int i = 0; i < numOfT; i++) {
            if (id.equals(teachers[i].getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfDepartmentExist(String depName) {
        for (int i = 0; i < numOfD; i++) {
            if (depName.equals(departments[i].getDepartmentName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfCommitteeExist(String committeeName) {
        for (int i = 0; i < numOfC; i++) {
            if (committeeName.equals(committees[i].getCommitteeName())) {
                return true;
            }
        }
        return false;
    }

    public chairmanStatus checkChairman(String id) {
        boolean ifTeacher = checkIfTeacherExists(id);

        if (!ifTeacher) {
            return chairmanStatus.NOT_FOUND;
        }

        Teacher t = getTeacher(id); //לוקחים מרצה כדי לבדוק את התואר שלו

        if (t.getDegree().ordinal() < AcademicRank.DR.ordinal()) {
            return chairmanStatus.RANK_TOO_LOW;
        }

        return chairmanStatus.VALID;
    }

    private Teacher[] extendArrTeachers(Teacher[] oldArr) {
        Teacher[] newArr = new Teacher[oldArr.length * 2];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
//        System.out.println("Teachers size was too small. now is 2x bigger! new size is: " + newArr.length);
        return newArr;
    }

    private Department[] extendArrDepartment(Department[] oldArr) {
        Department[] newArr = new Department[oldArr.length * 2];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
//        System.out.println("Department size was too small. now is 2x bigger! new size is: " + newArr.length);
        return newArr;
    }

    private Committee[] extendArrCommittee(Committee[] oldArr) {
        Committee[] newArr = new Committee[oldArr.length * 2];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
//        System.out.println("Committee size was too small. now is 2x bigger! new size is: " + newArr.length);
        return newArr;
    }

    public boolean addTeacher2Committee(Teacher t, Committee c) {
        Teacher[] members = c.getTeacherCommitteeArr();

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                members[i] = t;
                break;
            }

            if (members[i].equals(t)) {
                return false;
            }
        }
        return true;
    }

    //yiska parts
    public boolean removeMemberFromArr(Teacher teacher, Teacher[] arr) {
        boolean exists = checkTeacherExistsInArr(arr, teacher);
        if (!exists) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(teacher)) {
                arr[i] = null;
                break;
            }
        }
        return true;
    }

    public int calAverageSalaryAllTeachers() {
        if (teachers == null || numOfT == 0) {
            return 0;
        }

        double sum = 0;
        int count = 0;

        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null) {
                sum += teachers[i].getSalary();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return (int) (sum / count);
    }

    public int calAverageSpecific(Teacher[] arr) {
        double sum = 0;
        int count = 0;
        if (arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                sum += arr[i].getSalary();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return (int) (sum / count);
    }

    public void getTeachersDetails() {
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null) {
                System.out.println(teachers[i].toString());
            }
        }
        System.out.println();
    }

    public void showCommittee() {
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null) {
                System.out.println(committees[i].toString());
            }
        }
        System.out.println();
    }

    public boolean checkTeacherExistsInArr(Teacher[] teacher_comm, Teacher teacher) {
        for (int i = 0; i < teacher_comm.length; i++) {
            if (teacher_comm[i] != null && teacher_comm[i].equals(teacher)) { //GPT said to replace teacher with teacher_comm - if i have a bug need to revert
                return true;
            }
        }
        return false;
    }

    public boolean addTeacher2Department(Teacher teacher, Department d) {
        Teacher[] T_arr = d.getTechersArray();
        for (int i = 0; i < T_arr.length; i++) {
            if (T_arr[i] != null && T_arr[i].equals(teacher)) {
                return false;
            }
        }
        for (int i = 0; i < T_arr.length; i++) {
            if (T_arr[i] == null) {
                T_arr[i] = teacher;
                break;
            }
        }
        return true;
    }

    public void addComm2TeacherComms(Teacher t, Committee c) {
        Committee[] teacherComms = t.getCommitteesOfTeacher();
        for (int i = 0; i < teacherComms.length; i++) {
            if (teacherComms[i] == null) {
                teacherComms[i] = c;
                break;
            }
        }
    }

    public void removeCommFromTeacherComms(Teacher t, Committee c) {
        Committee[] teacherComms = t.getCommitteesOfTeacher();
        for (int i = 0; i < teacherComms.length; i++) {
            if (teacherComms[i] != null && teacherComms[i].equals(c)) {
                teacherComms[i] = null;
            }
        }
    }
}