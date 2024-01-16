package madcamp.week3.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Project;
import madcamp.week3.model.Scrum;
import madcamp.week3.service.ProjectSerivce;
import madcamp.week3.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class ScrumController {

    @Autowired
    private ScrumService scrumService;
    @Autowired
    private ProjectSerivce projectSerivce;

    @GetMapping("/scrum/details/{projectId}")
    public String getScrumList(@PathVariable Long projectId, Model model){
        // pathVariable로 받은 projectId로 scrumList 찾기
        List<Scrum> scrumList = scrumService.findScrumsByProjectId(projectId);
        // scrumList를 model에 저장
        model.addAttribute("scrumList", scrumList);
        // scrumDetail.html로
        return "scrumDetail";
    }

    @GetMapping("/scrum/add")
    public String getAddScrum(Model model, @RequestParam("projectId") Long projectId, HttpSession session){
        // scrumDetail에서 requestParam으로 넘긴 projectId를 model에 저장
        // scrum 새로 생성할것이므로 new Scrum()
        model.addAttribute("scrum", new Scrum());
        model.addAttribute("projectId", projectId);
        return "addScrum";
    }

    @PostMapping("/scrum/add")
    public String postAddScrum(Scrum scrum, @RequestParam("projectId") Long projectId){
        log.info("checkprojectId3" + projectId.toString());
        Project project = projectSerivce.findProjectById(projectId);
        scrum.setProject(project);
        scrumService.saveScrum(scrum);
        return "redirect:/scrum/details/" + projectId;
    }

}

