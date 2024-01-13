package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreForm {
    private Long userId;
    private int score;

    public ScoreForm() {
    }

    public ScoreForm(Long userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreForm{" +
                "userId=" + userId +
                ", score=" + score +
                '}';
    }
}
