package madcamp.week3.service;

import madcamp.week3.model.Post;
import madcamp.week3.model.Project;
import madcamp.week3.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectSerivce {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProjectFromPost(Post post) {
        Project project = new Project(
                post.getProject().getProjectTitle(),
                post.getProject().getDate(),
                post.getProject().getProjectDescription(),
                post.getProject().getMemberCount()
        );
        return projectRepository.save(project);
    }
}

