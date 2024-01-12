package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
//import madcamp.week3.service.UserService;
import madcamp.week3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/signup")
    public String getSignup(Model model){
        model.addAttribute("user", new User());
        return "getSignup";
    }

    @PostMapping("/user/signup/details")
    public String postDetailsSignup(@ModelAttribute User user, HttpSession session){
        log.info("user: {}", user.toString());
        session.setAttribute("user",user);
        return "postSignupDetails";
    }

    @PostMapping("/user/saveDetails")
    public String saveUserDetails(@ModelAttribute User userDetails, HttpSession session) {
        log.info("userDetails: {}", userDetails.toString());
        User user = (User) session.getAttribute("user");
        user.setEducation(userDetails.getEducation());
        user.setAward1(userDetails.getAward1());
        user.setAward2(userDetails.getAward2());
        user.setOneLineProfile(userDetails.getOneLineProfile());
        user.setGithubUrl(userDetails.getGithubUrl());
        log.info("user: {}", user.toString());
        userService.saveUserDetails(user);
        return "getLogin";
    }

    @GetMapping("/user/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "getLogin";
    }
    @PostMapping("/user/login")
    public String postLogin(@ModelAttribute User user){
        log.info("userLogin: {}", user.toString());
        // 여기까지는 출력
        if(userService.loginUser(user)){return "userProfile";}///////projecList로 테스트 redirect:/post
        else {return "getLogin";}
    }

    @GetMapping("/user/projects")
    public String getUserProjects(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user");
        List<Project> projects = userService.getProjectsByUserId(userId);
        model.addAttribute("projects", projects);
        return "projectList"; // Thymeleaf 템플릿 파일명
    }

    @GetMapping("/user/profile")
    public String getUserProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        log.info("userprofile1: {}", user.toString());
        User checkuser = userService.getUserByUSerId(user.getId());
        log.info("userprofile2: {}", user.toString());
        model.addAttribute("user", checkuser); // 모델에 사용자 정보 추가
        return "userProfile";
    } // userid, pwd받아오는데 나머지 정보 못받아옴

}