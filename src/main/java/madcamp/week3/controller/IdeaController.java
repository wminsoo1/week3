package madcamp.week3.controller;

import madcamp.week3.model.Idea;
import madcamp.week3.model.User;
import madcamp.week3.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IdeaController {

    @Autowired
    private IdeaService ideaService; // IdeaService는 Idea 목록을 가져오는 로직을 구현

    @GetMapping("/idea/add")
    public String showAddIdeaForm(Model model) {
        return "ideaAdd";
    }

    @PostMapping("/idea/add")
    public String addIdea(@RequestParam String ideaTitle, @RequestParam String ideaDescription, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Idea newIdea = new Idea(ideaTitle, ideaDescription, user);
        ideaService.saveIdea(newIdea);
        return "redirect:/vote"; // Redirect to the voting page
    }

    @GetMapping("/vote")
    public String showVotingForm(Model model, HttpSession session) {
        List<Idea> ideas = ideaService.getAllIdeas();
        model.addAttribute("ideas", ideas);

        User user = (User) session.getAttribute("loggedInUser");
        boolean hasVoted = user != null && user.getVotedIdea() != null;
        model.addAttribute("hasVoted", hasVoted);

        return "vote"; // Display the vote.html template with the list of ideas
    }

    @PostMapping("/vote")
    public String processVote(@RequestParam Long ideaId, HttpSession session) {
        // 투표 로직 구현, 예를 들어, 사용자 세션에서 User 정보를 가져와서 투표 처리
        User user = (User) session.getAttribute("loggedInUser");
        ideaService.voteForIdea(ideaId, user);

        return "redirect:/vote/result"; // 투표 후 다시 투표 페이지로 리다이렉트
    }

    @GetMapping("/vote/result")
    public String resultVote(Model model){
        List<Idea> ideas = ideaService.getAllIdeasWithVotes();
        model.addAttribute("ideas", ideas);
        return "voteResult"; // voteResults.html 템플릿 사용
    }
}