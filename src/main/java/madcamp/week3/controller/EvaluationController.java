package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.*;
import madcamp.week3.repository.EvaluationRepository;
import madcamp.week3.repository.ProjectRepository;
import madcamp.week3.service.EvaluationService;
import madcamp.week3.service.ProjectSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class EvaluationController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectSerivce projectSerivce;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/evaluatees")
    public String showEvaluatees(@RequestParam Long projectId, HttpSession session, Model model) {
        ArrayList<Evaluation> evaluations = new ArrayList<>();

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        log.info("loggedInUser: {}", loggedInUser.getId());
        log.info("projectId: {}", loggedInUser.getId());
        Project project = projectRepository.findById(projectId).orElse(null);

        List<User> evaluatees = evaluationService.getEvaluatees(projectId, loggedInUser.getId());
        for (User evaluatee : evaluatees) {
            log.info("evaluatee:{}", evaluatee.toString());
            Evaluation evaluation = new Evaluation(loggedInUser, evaluatee, 0);
            log.info("evaluation:{}", evaluation.toString());
            evaluations.add(evaluation);
        }
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setEvaluations(evaluations);

        log.info("Project: {}", project);
        log.info("LoggedInUser: {}", loggedInUser);
        List<Evaluation> evaluations1 = evaluationForm.getEvaluations();
        for (Evaluation evaluation: evaluations1) {
            log.info("showEvaluatees evaluation: {}",evaluation.toString());
        }
        model.addAttribute("project", project);
        model.addAttribute("evaluationForm", evaluationForm);
        return "evaluate_users";
    }

    @PostMapping("/evaluatees")
    public String submitEvaluation(@ModelAttribute("EvaluationForm") EvaluationForm  evaluationForm, HttpSession session) {
        // 평가 정보를 사용하여 필요한 로직 수행
        List<Evaluation> evaluations = evaluationForm.getEvaluations();
        User user = (User) session.getAttribute("loggedInUser");
        for (Evaluation evaluation : evaluations) {
            log.info("Received evaluation: {}", evaluation);
            evaluation.setEvaluator(user);
            evaluationRepository.save(evaluation);
            // 여기서 평가 정보를 저장 또는 처리하는 로직을 추가할 수 있습니다.
        }
        // 다른 로직 수행 후 결과 페이지로 이동
        return "redirect:/post";
    }
}
