package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    @Column(unique = true)
    private String userName;

    private String education;

    @OneToMany(mappedBy = "user")
    private List<Stack> stackList;

    private String award1;
    private String award2;

    private String oneLineProfile;

    private String githubUrl;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;



    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projectList;


    public User() {
    }

    public User(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public User(Long id, String password, String userName, String education, List<Stack> stackList, String award1, String award2, String oneLineProfile, String githubUrl, List<Post> posts, List<Comment> comments, List<Project> projectList) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.education = education;
        this.stackList = stackList;
        this.award1 = award1;
        this.award2 = award2;
        this.oneLineProfile = oneLineProfile;
        this.githubUrl = githubUrl;
        this.posts = posts;
        this.comments = comments;
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", education='" + education + '\'' +
                ", stackList=" + stackList +
                ", award1='" + award1 + '\'' +
                ", award2='" + award2 + '\'' +
                ", oneLineProfile='" + oneLineProfile + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", posts=" + posts +
                ", comments=" + comments +
                ", projectList=" + projectList +
                '}';
    }
}
