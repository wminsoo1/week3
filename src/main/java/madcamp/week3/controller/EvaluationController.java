package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.Evaluation;
import madcamp.week3.model.Project;
import madcamp.week3.model.ScoreForm;
import madcamp.week3.model.User;
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
    public String showEvaluatees(@RequestParam Long projectId, HttpSession session, RedirectAttributes redirectAttributes) {
        Map<String, Object> responseData = new HashMap<>();

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        log.info("loggedInUser: {}", loggedInUser.getId());
        log.info("projectId: {}", loggedInUser.getId());
        Project project = projectRepository.findById(projectId).orElse(null);

        List<User> evaluatees = evaluationService.getEvaluatees(projectId, loggedInUser.getId());
        for (User evaluatee : evaluatees) {
            log.info("evaluatee:{}", evaluatee.toString());
            Evaluation evaluation = new Evaluation(loggedInUser, evaluatee, 0);
            log.info("evaluation:{}", evaluation.toString());
            evaluationRepository.save(evaluation);
        }
        List<Evaluation> evaluationList = evaluationRepository.findByEvaluator_Id(loggedInUser.getId());

        log.info("Project: {}", project);
        log.info("LoggedInUser: {}", loggedInUser);
        log.info("EvaluationList: {}", evaluationList);

        redirectAttributes.addFlashAttribute("project", project);
        redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
        redirectAttributes.addFlashAttribute("evaluationList", evaluationList);

        return "redirect:/evaluatees";
    }

    @PostMapping("/evaluatees")
    public String postEvaluatees(RedirectAttributes redirectAttributes, HttpSession session) {
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        for (Map.Entry<String, ?> entry : flashAttributes.entrySet()) {
            log.info("Flash Attribute - Key: {}, Value: {}", entry.getKey(), entry.getValue());
        }

        List<Evaluation> evaluations = (List<Evaluation>) flashAttributes.get("evaluations");
        for (Evaluation evaluation : evaluations) {
            log.info("Received Evaluation - Evaluator ID: {}", evaluation.getEvaluator().getId());
            log.info("Received Evaluation - Evaluatee ID: {}", evaluation.getEvaluatee().getId());
            log.info("Received Evaluation - Score: {}", evaluation.getScore());

            // 여기에 추가로 필요한 로직을 작성하세요.
            // 예: 데이터베이스에 평가 결과 저장, 통계 처리 등
        }
        // 평가 처리 로직을 추가하고 평가 결과를 어딘가에 저장하거나 처리합니다.
        return "evaluate_users";

    }
}
