package madcamp.week3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Post;
import madcamp.week3.model.Project;
import madcamp.week3.model.User;
import madcamp.week3.repository.PostRepository;
import madcamp.week3.repository.ProjectRepository;
import madcamp.week3.repository.UserRepository;
import madcamp.week3.service.ProjectSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepository;
    private final ProjectSerivce projectSerivce;
    private final UserRepository userRepository;

    @GetMapping("/post")
    public String viewPost(Model model){
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        log.info("posts: {}", posts);
        model.addAttribute("posts", posts);
        return "post";
    }

    @GetMapping("/post/add")
    public String getPostAdd(Model model, HttpSession session){
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/user/signup";
        }
        model.addAttribute("post", new Post());
        return "postAdd";
    }

    @PostMapping("/post/add")
    public String postPostAdd(@ModelAttribute Post post, HttpSession session){
        log.info("postPostAdd:{}", post);

        User currentUser = (User) session.getAttribute("loggedInUser");
        Long userId = currentUser.getId();
        log.info("postPostAdd currentUser:{}", currentUser);
        log.info("postPostAdd userId:{}", userId);
        User userById = userRepository.findById(userId).orElse(new User());
        log.info("postPostAdd:{}", userById);
        post.setUser(userById);
        Project project = projectSerivce.saveProjectFromPost(post);
        post.setProject(project);
        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping("/post/detail/{postId}")
    public String viewPostDetail(@PathVariable Long postId, Model model, HttpSession session){
        Post postById = postRepository.findById(postId).get();
        Long id = postById.getUser().getId();
        model.addAttribute("post", postById);
        model.addAttribute("selectedUserIds", new ArrayList<>());
        model.addAttribute("loggedInUserId", id);
        session.setAttribute("currentProjectId", postRepository.findById(postId).get().getProject().getProjectId());
        if (postRepository.findById(postId).get().getProject().getUserList() != null){
            model.addAttribute("hasMade", false);
        }
        return "postDetail";
    }
}
