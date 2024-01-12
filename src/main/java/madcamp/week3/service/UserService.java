package madcamp.week3.service;

import madcamp.week3.model.User;
import madcamp.week3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean loginUser(User user) {
        User checkUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());

        if (checkUser != null) {return true;}
        else{return false;}
    }

}