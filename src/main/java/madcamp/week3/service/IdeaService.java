package madcamp.week3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import madcamp.week3.model.Idea;
import madcamp.week3.model.User;
import madcamp.week3.repository.IdeaRepository;
import madcamp.week3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    @Transactional
    public void voteForIdea(Long ideaId, User user) {
        Optional<Idea> idea = ideaRepository.findById(ideaId);
        if (idea.isPresent() && user != null) {
            // 사용자의 투표한 아이디어를 설정하고 저장
            user.setVotedIdea(idea.get());
            userRepository.save(user);
        }
    }
    public void saveIdea(Idea idea) {
        ideaRepository.save(idea);
    }

    public List<Idea> getAllIdeasWithVotes() {
        // 모든 아이디어를 조회하고 반환
        return ideaRepository.findAll();
    }
}