package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostId;

    private String postTitle;

    private String postDescription;

    @OneToOne
    @JoinColumn(name = "project_id") // 외래 키 컬럼명
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id") // 외래 키 컬럼명
    private User user;

    @OneToMany(mappedBy = "post") // 양방향 관계 설정
    private List<Comment> comment;

    public Post() {
    }

    public Post(Long postId, String postTitle, String postDescription, Project project, User user) {
        PostId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.project = project;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "PostId=" + PostId +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", project=" + project +
                ", user=" + user +
                '}';
    }
}
