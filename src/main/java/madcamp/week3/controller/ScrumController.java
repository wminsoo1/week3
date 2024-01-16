package madcamp.week3.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Scrum;
import madcamp.week3.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class ScrumController {

    @Autowired
    private ScrumService scrumService;
    @GetMapping("/scrum/details/{projectId}")
    public String getScrumList(@PathVariable Long projectId, Model model, HttpSession session){
        List<Scrum> scrumList = scrumService.findScrumsByProjectId(projectId);
        model.addAttribute("scrumList", scrumList);
        return "scrumDetail";
    }

}
