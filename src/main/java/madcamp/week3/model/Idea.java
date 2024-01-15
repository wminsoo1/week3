package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ideaId;
    private String ideaTitle;
    private String ideaDescription;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @OneToMany(mappedBy = "votedIdea")
    private List<User> votedUsers;

    public int getNumberOfVotedUsers() {
        return votedUsers.size();
    }

    public Idea() {
    }

    public Idea(String ideaTitle, String ideaDescription, User creator) {
        this.ideaTitle = ideaTitle;
        this.ideaDescription = ideaDescription;
        this.creator = creator;
    }

    public Idea(String ideaTitle, String ideaDescription, User creator, List<User> votedUsers) {
        this.ideaTitle = ideaTitle;
        this.ideaDescription = ideaDescription;
        this.creator = creator;
        this.votedUsers = votedUsers;
    }

    public int numberOfVotedUsers() {
        return this.votedUsers.size();
    }

}
