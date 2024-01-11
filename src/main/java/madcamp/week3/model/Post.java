package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostId;

    private String postTitle;

    private String postDescription;

    @ManyToOne
    @JoinColumn(name = "user_id") // 외래 키 컬럼명
    private User user;

    public Post() {
    }

    public Post(Long postId, String postTitle, String postDescription, User user) {
        PostId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "PostId=" + PostId +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", user=" + user +
                '}';
    }
}
