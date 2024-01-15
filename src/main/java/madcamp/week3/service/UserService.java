package madcamp.week3.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Idea;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
import madcamp.week3.repository.IdeaRepository;
import madcamp.week3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final IdeaRepository ideaRepository;


    public void saveUserDetails(User user) {
        // 사용자 정보를 데이터베이스에 저장
        userRepository.save(user);
    }

    public User loginUser(User user) {
        User checkUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (checkUser != null) {return checkUser;}
        else{return null;}
    }

    public List<Project> getProjectsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getProjectList();
    }

    public User getUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return user;
    }



    public User getUserByUSerId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return user;
    }

    public void updateScore(Long userId, Integer newScore) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAllScore(newScore);
            userRepository.save(user);
        }
    }

    public List<Idea> getTop3IdeasByVotes() {
        List<Idea> allIdeas = ideaRepository.findAll();

        List<Idea> top3Ideas = allIdeas.stream()
                .sorted(Comparator.<Idea, Integer>comparing(idea -> idea.getNumberOfVotedUsers()).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return top3Ideas;
    }
}
