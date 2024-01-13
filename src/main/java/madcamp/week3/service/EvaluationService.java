package madcamp.week3.service;

import madcamp.week3.model.Project;
import madcamp.week3.model.User;
import madcamp.week3.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private ProjectRepository projectRepository;

    // 특정 프로젝트에 대한 평가 대상자 목록을 가져오는 메소드
    public List<User> getEvaluatees(Long projectId, Long evaluatorId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        if (project == null) {
            return null; // 프로젝트가 존재하지 않을 경우
        }

        // 프로젝트에 속한 모든 사용자를 가져오고, 평가자를 제외한 목록 생성
        return project.getUserList().stream()
                .filter(user -> !user.getId().equals(evaluatorId))
                .collect(Collectors.toList());
    }

    // 추가적인 메소드 구현
}

