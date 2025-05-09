import java.util.Scanner;
//Itay Balin
//Yiska Malka
public class Main {
    public static void main(String[] args) {
        boolean keepRunning = true;
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the name of the college: ");
        String collegeName = scn.nextLine();
        CollegeManager manager = new CollegeManager(collegeName);
        System.out.println("Welcome to " + manager.getCollegeName());

        while (keepRunning) {
            menuPrint();
            int num = scn.nextInt();
            scn.nextLine();
            switch (num) {
                case 0:
                    System.out.println("You choose 0 - the program is dead. bye!");
                    keepRunning = false;
                    break;
                case 1:
                    System.out.println("You choose 1 - adding a teacher");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter ID:");
                    String id = scn.nextLine();

                    if (manager.checkIfTeacherExists(id)) {
                        System.out.println("teacher already exists, try again!");
                        break;
                    }

                    System.out.println("Enter teacher's full name:");
                    String teacherName = scn.nextLine();

                    System.out.println("Enter degree (NONE / BSC / MSC / DR / PROF:");
                    String degree = scn.nextLine().toUpperCase();
                    AcademicRank rank = AcademicRank.valueOf(degree);

                    String degreeName = null;
                    if (rank != AcademicRank.NONE) {
                        System.out.println("Enter degree name:");
                        degreeName = scn.nextLine();

                    }

                    System.out.println("Enter salary:");
                    int salary = scn.nextInt();
                    scn.nextLine();

                    System.out.println("Enter department name (if doesn't have one - enter 'none'):");
                    String department = scn.nextLine();

                    if (!department.equals("none")) {
                        if (!manager.checkIfDepartmentExist(department)) {
                            System.out.println("department doesn't exist, try again!");
                            break;
                        }
                        Department department2 = manager.getDepartment(department);
                        Teacher teacher2 = manager.getTeacher(id);

                        manager.addTeacher2Department(teacher2, department2);
                    }

                    Committee[] committeesOfTeacher = new Committee[10];
                    manager.addTeacher(teacherName, id, rank, degreeName, salary, department, committeesOfTeacher);

                    System.out.println("Teacher added successfully.");
                    System.out.println("-------------------------------------------------");

                    break;
                case 2:
                    System.out.println("You choose 2 - adding a committee");
                    System.out.println("-------------------------------------------------");

                    System.out.println("Enter chairman id: ");
                    String chairmanID = scn.nextLine();
                    chairmanStatus status = manager.checkChairman(chairmanID);
                    if (!checkValid(status)) {
                        break;
                    }

                    Teacher chairman =  manager.getTeacher(chairmanID);

                    System.out.println("Enter committee name: ");
                    String committeeName = scn.nextLine(); //בדיקה אם הועדה קיימת

                    if (manager.checkIfCommitteeExist(committeeName)) {
                        System.out.println("Committee already exist, try again!");
                        break;
                    }

                    System.out.println("Enter number of teachers in committee: ");
                    int numOfTeachersC = scn.nextInt();
                    System.out.println();

                    Teacher[] teachers = new Teacher[numOfTeachersC];

                    manager.addCommittee(chairman, committeeName, teachers);

                    System.out.println("Committee added successfully.");
                    System.out.println("-------------------------------------------------");


                    break;
                case 3:
                    System.out.println("You choose 3 - adding a member to a committee");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter ID of teacher to add to committee: ");
                    String teacherID2Add = scn.nextLine();
                    if (!manager.checkIfTeacherExists(teacherID2Add)) {
                        System.out.println("teacher doesn't exist! try again :)");
                        break;
                    }
                    Teacher t = manager.getTeacher(teacherID2Add);

                    System.out.println("Enter the committee name: ");
                    String committee2Add2 = scn.nextLine();
                    if (!manager.checkIfCommitteeExist(committee2Add2)) {
                        System.out.println("committee doesn't exist! try again :)");
                        break;
                    }
                    Committee c = manager.getCommittee(committee2Add2);

                    Teacher chairmanCheck = c.getChairman();
                    if (chairmanCheck.equals(t)) {
                        System.out.println(t.getName() + " is the chairman, can't be added to " + committee2Add2 + ". try again!");
                        break;
                    }

                    if (!manager.addTeacher2Committee(t, c)){
                        System.out.println(t.getName() + " already in " + c + ". is not added!");
                    } else{
                        manager.addComm2TeacherComms(t, c);
                        System.out.println(t.getName() + " successfully added to " + committee2Add2);
                    }
                    System.out.println("-------------------------------------------------");
                    break;

                case 4:
                    System.out.println("You choose 4 - updating the chairman");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter the new chairman ID: ");
                    String newChairmanID = scn.nextLine();
                    chairmanStatus status2 = manager.checkChairman(newChairmanID);
                    if (!checkValid(status2)) {
                        break;
                    }

                    Teacher newChairman = manager.getTeacher(newChairmanID);

                    System.out.println("Enter committee that's needs a new chairman: ");
                    String committee2AddNewChairman = scn.nextLine();

                    if (!manager.checkIfCommitteeExist(committee2AddNewChairman)) {
                        System.out.println("no such committee! try again :)");
                        break;
                    }

                    Committee c2 = manager.getCommittee(committee2AddNewChairman);
                    Teacher[] teachersInCommittee = c2.getTeacherCommitteeArr();

                    if (manager.checkTeacherExistsInArr(teachersInCommittee, newChairman)){
                        manager.removeMemberFromArr(newChairman, teachersInCommittee);
                    }

                    c2.setChairman(newChairman);

                    System.out.println("Chairman updated!");
                    System.out.println("-------------------------------------------------");
                    break;

                case 5:
                    System.out.println("You choose 5 - removing a member from a committee");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter the name of committee to remove a member from");
                    String c_Name = scn.nextLine();
                    if (!manager.checkIfCommitteeExist(c_Name)) {
                        System.out.println("No such committee! try again :)");
                        break;
                    }

                    Committee comm = manager.getCommittee(c_Name);

                    Teacher[] teachers_committee = comm.getTeacherCommitteeArr();

                    System.out.println("Enter ID of teacher to remove from committee: ");
                    String t_ID = scn.nextLine();

                    if (!manager.checkIfTeacherExists(t_ID)) {
                        System.out.println("teacher isn't in the committee, try again!");
                        break;
                    }
                    Teacher teach = manager.getTeacher(t_ID);

                    if (!manager.removeMemberFromArr(teach, teachers_committee)){
                        System.out.println("Teachers isn't in the committee! didn't remove :)");
                        break;
                    }

                    manager.removeCommFromTeacherComms(teach, comm);

                    System.out.println(teach.getName() + " successfully removed from " + t_ID);
                    System.out.println("-------------------------------------------------");

                    break;

                case 6:
                    System.out.println("You choose 6 - adding a department");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter department name:");
                    String departmentName = scn.nextLine();

                    if (manager.checkIfDepartmentExist(departmentName)) {
                        System.out.println("department already exists, try again!");
                        break;
                    }

                    System.out.println("Enter number of students:");
                    int numStudents = scn.nextInt();
                    scn.nextLine();

                    System.out.println("Enter num of teachers teaching:");
                    int numOfTeachersD = scn.nextInt();
                    scn.nextLine();
                    Teacher[] teachersInDep = new Teacher[numOfTeachersD];

                    manager.addDepartment(departmentName, numStudents, teachersInDep);

                    System.out.println("Department added successfully.");
                    System.out.println("-------------------------------------------------");

                    break;

                case 7:
                    System.out.println("You choose 7 - showing average salary of all teachers");
                    System.out.println("-------------------------------------------------");
                    System.out.println("The average salary is: " + manager.calAverageSalaryAllTeachers());
                    System.out.println("-------------------------------------------------");
                    break;
                case 8:
                    System.out.println("You choose 8 - showing average salary of a specific department");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter name of department: ");
                    String depName= scn.nextLine();

                    if (!manager.checkIfDepartmentExist(depName)) {
                        System.out.println("No such Department! try again :)");
                        break;
                    }

                    Department depart = manager.getDepartment(depName);
                    Teacher[] arr = depart.getTechersArray();

                    System.out.println("The average salary of teachers in " + depName + " is " + manager.calAverageSpecific(arr));
                    System.out.println("-------------------------------------------------");

                    break;
                case 9:
                    System.out.println("You choose 9 - showing details of all teachers");
                    System.out.println("-------------------------------------------------");
                    manager.getTeachersDetails();
                    System.out.println("-------------------------------------------------");
                    break;
                case 10:
                    System.out.println("You choose 10 - showing all committees");
                    System.out.println("-------------------------------------------------");
                    manager.showCommittee();
                    System.out.println("-------------------------------------------------");
                    break;
                case 11:
                    System.out.println("You choose 11 - adding a teacher to a department");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter ID of teacher to add to department: ");
                    String teacherID = scn.nextLine();

                    if (!manager.checkIfTeacherExists(teacherID)) {
                        System.out.println("teacher doesn't exist! try again :)");
                        break;
                    }

                    Teacher teacher = manager.getTeacher(teacherID);

                    System.out.println("Enter the department name: ");
                    String department2Add2 = scn.nextLine();
                    if(!manager.checkIfDepartmentExist(department2Add2)){
                        System.out.println("department doesn't exists! try again :)");
                    }

                    Department d = manager.getDepartment(department2Add2);

                    if (!manager.addTeacher2Department(teacher,d)){
                        System.out.println("Teacher already in department. teacher is not added!");
                    } else {System.out.println(teacher.getName() + " successfully added to " +  department2Add2);}

                    System.out.println("-------------------------------------------------");
                    break;

                default:
                    System.out.println("Wrong choice! try again :)");
                    System.out.println("-------------------------------------------------");
                    break;
            }
        }
    }

    private static boolean checkValid(chairmanStatus status) {
        if (status == chairmanStatus.NOT_FOUND) {
            System.out.println("Teacher not found. try again!");
            return false;
        }
        if (status == chairmanStatus.RANK_TOO_LOW){
            System.out.println("Teacher rank too low. should be DR / PROF");
            return false;
        }
        return true;
    }

    private static void menuPrint() {
        System.out.println("-------------------------------------------------");
        System.out.println("Please choose a number, choose 0 to exit: ");
        System.out.println("0 - exit");
        System.out.println("1 - add a teacher to the college");
        System.out.println("2 - add committee to the college");
        System.out.println("3 - add a member to a committee");
        System.out.println("4 - update the committee chairman ");
        System.out.println("5 - remove a member from the committee");
        System.out.println("6 - add department");
        System.out.println("7 - show salary average of all teachers");
        System.out.println("8 - show salary average of the teachers in specific department");
        System.out.println("9 - show all teachers details");
        System.out.println("10 - show all committees details");
        System.out.println("11 - add teacher to a department");
        System.out.println("-------------------------------------------------");
    }
}