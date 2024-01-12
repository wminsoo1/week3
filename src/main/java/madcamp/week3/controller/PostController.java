package madcamp.week3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Post;
import madcamp.week3.model.Project;
import madcamp.week3.repository.PostRepository;
import madcamp.week3.repository.ProjectRepository;
import madcamp.week3.service.ProjectSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepository;
    private final ProjectSerivce projectSerivce;

    @GetMapping("/post")
    public String viewPost(Model model){
        List<Post> posts = postRepository.findAll();
        log.info("posts: {}", posts);
        model.addAttribute("posts", posts);
        return "post";
    }

    @GetMapping("/post/add")
    public String getPostAdd(Model model){
        model.addAttribute("post", new Post());
        return "postAdd";
    }

    @PostMapping("/post/add")
    public String postPostAdd(@ModelAttribute Post post){
        log.info("post:{}", post);
        Project project = projectSerivce.saveProjectFromPost(post);
        post.setProject(project);
        postRepository.save(post);
        return "redirect:/post";
    }
}
