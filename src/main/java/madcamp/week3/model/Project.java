package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectTitle;

    private String date;

    private String projectDescription;

    @ManyToMany(mappedBy = "projectList")
    private List<User> userList;

    private Integer memberCount;

    public Project() {
    }

    public Project(Long projectId, String projectTitle, String date, String projectDescription, List<User> userList, Integer memberCount) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.date = date;
        this.projectDescription = projectDescription;
        this.userList = userList;
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectTitle='" + projectTitle + '\'' +
                ", date='" + date + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", userList=" + userList +
                ", memberCount=" + memberCount +
                '}';
    }
}
