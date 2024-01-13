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

    public boolean hasUserJoinedProject(Long userId, Long projectId) {
        // 이 메서드는 사용자가 특정 프로젝트에 이미 참여했는지 여부를 반환합니다.
        // 실제 구현은 프로젝트와 사용자 관계를 관리하는 repository의 쿼리 메서드를 사용하여 수행합니다.
        return projectRepository.existsByIdAndProjectId(userId, projectId);
    }
}

