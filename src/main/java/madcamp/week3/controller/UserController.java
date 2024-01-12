package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Post;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
//import madcamp.week3.service.UserService;
import madcamp.week3.repository.UserRepository;
import madcamp.week3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/signup")
    public String getSignup(Model model) {
        model.addAttribute("user", new User());
        return "getSignup";
    }

    @GetMapping("/user/signup/details")
    public String getDetailsSignup(@ModelAttribute User user, HttpSession session) {
        log.info("getDetailsSignup: {}", user.toString());
        session.setAttribute("user", user);
        return "postSignupDetails";
    }

    @PostMapping("/user/saveDetails")
    public String saveUserDetails(@ModelAttribute User userDetails, HttpSession session) {
        log.info("saveUserDetails: {}", userDetails.toString());
        User user = (User) session.getAttribute("user");
        log.info("saveUserDetails:{}", user);
        user.setEducation(userDetails.getEducation());
        user.setAward1(userDetails.getAward1());
        user.setAward2(userDetails.getAward2());
        user.setOneLineProfile(userDetails.getOneLineProfile());
        user.setGithubUrl(userDetails.getGithubUrl());
        log.info("saveUserDetails: {}", user.toString());
        userService.saveUserDetails(user);
        log.info("saveUserDetails user: {}", user.toString());
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String getLogin(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        log.info("getLogin:{}", user);
        model.addAttribute("user", user);
        return "getLogin";
    }

    @PostMapping("/user/login")
    public String postLogin(@ModelAttribute User user, HttpSession session) {
        // 여기까지는 출력
        User loggedInUser = userService.loginUser(user);
//        User user1 = (User) session.getAttribute("user");
//        log.info("user1:{}", user1);
        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            log.info("userLogin: {}", loggedInUser.toString());
            return "redirect:/post";
        }///////projecList로 테스트 redirect:/post
        else {
            return "getLogin";
        }
    }
}

    /*
    @GetMapping("/user/projects")
    public String getUserProjects(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user");
        List<Project> projects = userService.getProjectsByUserId(userId);
        model.addAttribute("projects", projects);
        return "projectList"; // Thymeleaf 템플릿 파일명
    }

    @PostMapping("/user/projects")
    public String handleUserProjects(HttpServletRequest request, @ModelAttribute Post post) {
        // 선택된 사용자 ID 목록 받아오기
        String[] selectedUserIds = request.getParameterValues("selectedUserIds");

        // 확인을 위해 콘솔에 출력
        if (selectedUserIds != null) {
            for (String userId : selectedUserIds) {
                System.out.println("Selected User ID: " + userId);

                if (userId.equals("[]")) {
                    continue;
                }
                // 선택된 사용자 ID에 대한 추가 로직 수행
                Long id = Long.parseLong(userId);

                // 사용자가 속한 프로젝트에 현재 포스트의 프로젝트를 추가

                if (user != null) {
                    Project postProject = post.getProject();
                    user.getProjectList().add(postProject);
                    userRepository.save(user);
                }
            }
        } else {
            System.out.println("No selected users");
        }

        // 추가 로직 수행 후 리다이렉트 또는 다른 처리 수행
        return "redirect:/"; // 예시로 홈페이지로 리다이렉트
    }
    }*/