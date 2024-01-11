package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stackId;

    private String stackName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Stack() {
    }

    public Stack(Long stackId, String stackName) {
        this.stackId = stackId;
        this.stackName = stackName;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stackId=" + stackId +
                ", stackName='" + stackName + '\'' +
                ", user=" + user +
                '}';
    }
}
