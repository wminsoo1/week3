package madcamp.week3.repository;

import madcamp.week3.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Project p JOIN p.userList u WHERE u.id = :userId AND p.projectId = :projectId")
    boolean existsByIdAndProjectId(@Param("userId") Long userId, @Param("projectId") Long projectId);
}
