package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    private String userName;

    @NotBlank(message = "학력을 입력하세요")
    private String education;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Stack.class)
    @CollectionTable(name = "user_stack", joinColumns = @JoinColumn(name = "user_id"))
    private List<Stack> stackList;


    private String award1;
    private String award2;

    @NotBlank(message = "한줄소개를 입력하세요")
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

    @OneToMany(mappedBy = "creator")
    private List<Idea> createdIdeas;

    @ManyToOne
    @JoinColumn(name = "voted_idea_id")
    private Idea votedIdea;

    private Integer allScore = 0;

    public User() {
    }

    public User(Long id) {
        this.id = id;
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
                ", allscore= " + allScore + '\'' +
                '}';
    }
}
