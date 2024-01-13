package madcamp.week3.service;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
import madcamp.week3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

}
