package madcamp.week3.repository;

import madcamp.week3.model.Project;
import madcamp.week3.model.Scrum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScrumRepository extends JpaRepository<Scrum, Long> {
    List<Scrum> findByProjectProjectId(Long projectId);
}
