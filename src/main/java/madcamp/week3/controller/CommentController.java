package madcamp.week3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Comment;
import madcamp.week3.model.Post;
import madcamp.week3.model.User;
import madcamp.week3.repository.CommentRepository;
import madcamp.week3.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/post/comment")
    public String addComment(@ModelAttribute Comment comment, @RequestParam Long postId, HttpSession session) {
        // 여기에서 postId를 사용하여 해당 댓글이 어떤 게시물에 속하는지 설정
        // comment.setPost(...); // postId를 사용하여 Post를 설정해야 함
        User currentUser = (User) session.getAttribute("loggedInUser");
        log.info("addComment:{}", currentUser);
        Post postById = postRepository.findById(postId).get();
        comment.setUser(currentUser);
        comment.setPost(postById);
        commentRepository.save(comment);
        return "redirect:/post/detail/" + postId;
    }
}
