package madcamp.week3.repository;

import madcamp.week3.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 해당 searchString가 들어간 postTitle, postDescription, projectTitle, projectDescription, userName 찾는 sql query
    @Query("SELECT p FROM Post p WHERE " +
            "p.postTitle LIKE %?1% OR " +
            "p.postDescription LIKE %?1% OR " +
            "p.project.projectTitle LIKE %?1% OR " +
            "p.project.projectDescription LIKE %?1% OR " +
            "p.user.userName LIKE %?1%")
    List<Post> searchByKeyword(String searchString);

}
