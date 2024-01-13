package madcamp.week3.repository;

import madcamp.week3.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByEvaluator_Id(Long evaluatorId);
}
