package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evalId;

    @ManyToOne
    @JoinColumn(name = "evaluator_id", referencedColumnName = "id")
    private User evaluator;

    @ManyToOne
    @JoinColumn(name = "evaluatee_id", referencedColumnName = "id")
    private User evaluatee;

    private Integer score;

    public Evaluation() {
    }

    public Evaluation(User evaluator, User evaluatee, Integer score) {
        this.evaluator = evaluator;
        this.evaluatee = evaluatee;
        this.score = score;
    }

    // 추가적인 생성자와 메소드가 필요하면 여기에 추가
}