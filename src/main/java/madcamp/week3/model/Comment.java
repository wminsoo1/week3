package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentId;

    private String CommentDescription;

    @ManyToOne
    @JoinColumn(name = "user_id") // 외래 키 컬럼명
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id") // 외래 키 컬럼명
    private Post post;

    public Comment() {
    }

    public Comment(Long commentId, String commentDescription, User user, Post post) {
        CommentId = commentId;
        CommentDescription = commentDescription;
        this.user = user;
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "CommentId=" + CommentId +
                ", CommentDescription='" + CommentDescription + '\'' +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
