package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EvaluationForm {
    private List<Evaluation> evaluations;

    public EvaluationForm() {
    }
}
