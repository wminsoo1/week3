package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Post;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
//import madcamp.week3.service.UserService;
import madcamp.week3.repository.PostRepository;
import madcamp.week3.repository.UserRepository;
import madcamp.week3.service.ProjectSerivce;
import madcamp.week3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ProjectSerivce projectSerivce;

    @GetMapping("/user/signup")
    public String getSignup(Model model) {
        model.addAttribute("user", new User());
        return "getSignup";
    }

    @GetMapping("/user/signup/details")
    public String getDetailsSignup(@ModelAttribute User user, HttpSession session) {
        if (userService.loginUser(user) != null) {
            return "redirect:/user/login";
        }
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
        user.setStackList(userDetails.getStackList());
        log.info("saveUserDetails: {}", user.toString());
        userService.saveUserDetails(user);
        log.info("saveUserDetails user: {}", user.toString());
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String getLogin(Model model, HttpSession session) {
//        User user = (User) session.getAttribute("user");
        model.addAttribute("user", new User());
        return "getLogin";
    }

    @PostMapping("/user/login")
    public String postLogin(@ModelAttribute User user, HttpSession session) {
        if (userService.loginUser(user) == null) {
            return "redirect:/user/signup";
        }
        // 여기까지는 출력
        log.info("postLogin");
        User loggedInUser = userService.loginUser(user);
        log.info("postLogin:{}", loggedInUser.toString());
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

    @GetMapping("/user/profile/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "userProfile";
    }


    @GetMapping("/user/projects")
    public String getUserProjects(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        List<Project> projects = userService.getProjectsByUserId(user.getId());
        model.addAttribute("projects", projects);
        return "projectList"; // Thymeleaf 템플릿 파일명
    }

    @PostMapping("/user/projects")
    public String handleUserProjects(Model model, @RequestParam Long postId, @RequestParam(name = "selectedUserIds", defaultValue = "") String selectedUserIdsString
            ,HttpSession session) {
        // 선택된 사용자 ID 목록 받아오기
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        Long projectId = (Long) session.getAttribute("currentProjectId");
        if (user.getId() == postRepository.findById(postId).get().getUser().getId()){
            List<String> selectedUserIds = new ArrayList<>();
            Long userId = postRepository.findById(postId).map(post -> post.getUser().getId()).orElse(null);
            selectedUserIds.add(String.valueOf(userId));
            if (!selectedUserIdsString.isEmpty()) {
                // selectedUserIdsString이 비어있지 않은 경우 for문을 돌며 값 추가
                for (String id : selectedUserIdsString.split(",")) {
                    selectedUserIds.add(id);
                    log.info("handleUserProjects: id {}", id);
                }
            }
            log.info("handleUserProjects postid: {}", postId);
            // 현재 포스트의 유저 ID 가져오기

            if (userId != null) {
                log.info("handleUserProjects:{}", userId);
                // 확인을 위해 콘솔에 출력
                if (!selectedUserIds.isEmpty()) {
                    // 현재 프로젝트의 사용자 리스트 초기화
                    List<User> userList = new ArrayList<>();

                    // 선택된 사용자 ID에 대한 추가 로직 수행
                    for (String arrayId : selectedUserIds) {
                        log.info("Selected User ID: {}", arrayId);
                        Long id = Long.parseLong(arrayId);

                        // 선택된 사용자 ID에 대한 추가 로직 수행
                        User selectedUser = userRepository.findById(id).orElse(null);

                        if (selectedUser != null) {
                            // 사용자가 속한 프로젝트에 현재 포스트의 프로젝트를 추가
                            Project postProject = postRepository.findById(postId).map(Post::getProject).orElse(null);

                            if (postProject != null) {
                                selectedUser.getProjectList().add(postProject);

                                // 선택된 사용자를 현재 프로젝트의 사용자 리스트에 추가
                                userList.add(selectedUser);

                                userRepository.save(selectedUser);
                            }
                        }
                    }

                    // 현재 프로젝트에 사용자 리스트 설정
                    postRepository.findById(postId).ifPresent(post -> post.getProject().setUserList(userList));
                } else {
                    log.info("No selected users");
                }
            }

            boolean hasMade = userId != null && projectSerivce.hasUserJoinedProject(user.getId(), projectId);
            model.addAttribute("hasMade", hasMade);
//            boolean hasVoted = user != null && user.getVotedIdea() != null;
//            model.addAttribute("hasVoted", hasVoted);
            return "redirect:/user/projects"; // 예시로 홈페이지로 리다이렉트
        }
        return "redirect:/post/detail/" + postId;
    }
}