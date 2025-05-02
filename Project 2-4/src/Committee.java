import java.util.Arrays;
import java.util.Objects;

public class Committee {
    private String committeeName;
    private Teacher[] teacherCommitteeArr;
    private Teacher chairman;


    public Committee(Teacher chairman, String committeeName, Teacher[] teacherCommitteeArr) {
        this.committeeName = committeeName;
        this.teacherCommitteeArr = teacherCommitteeArr;
    }


    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public Teacher[] getTeacherCommitteeArr() {
        return teacherCommitteeArr;
    }

    public void setTeacherCommitteeArr(Teacher[] teacherCommitteeArr) {
        this.teacherCommitteeArr = teacherCommitteeArr;
    }

    public Teacher getChairman() {
        return chairman;
    }

    public void setChairman(Teacher chairman) {
        this.chairman = chairman;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return Objects.equals(committeeName, committee.committeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(committeeName);
    }

    @Override
    public String toString() {
        return "Committee{" +
                "chairmanName='" + chairman + '\'' +
                ", committeeName='" + committeeName + '\'' +
                ", teacherCommitteeArr=" + Arrays.toString(teacherCommitteeArr) +
                '}';
    }

}