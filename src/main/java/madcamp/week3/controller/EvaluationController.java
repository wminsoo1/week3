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

    @ResponseBody
    @PostMapping("/evaluatees")
    public ResponseEntity<Map<String, Object>> showEvaluatees(@RequestParam Long projectId, HttpSession session) {
        Map<String, Object> responseData = new HashMap<>();

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Project project = projectRepository.findById(projectId).orElse(null);

        List<User> evaluatees = evaluationService.getEvaluatees(projectId, loggedInUser.getId());
        for (User evaluatee : evaluatees) {
            Evaluation evaluation = new Evaluation(loggedInUser, evaluatee, 0);
            evaluationRepository.save(evaluation);
        }

        List<Evaluation> evaluationList = evaluationRepository.findByEvaluator_Id(loggedInUser.getId());

        responseData.put("project", project);
        responseData.put("loggedInUser", loggedInUser);
        responseData.put("evaluation", evaluationList);

        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/asd")
    public String postEvaluatees(@RequestBody List<Evaluation> evaluations, HttpSession session) {
        for (Evaluation evaluation : evaluations) {
            log.info("Evaluator ID: {}", evaluation.getEvaluator().getId());
            log.info("Evaluatee ID: {}", evaluation.getEvaluatee().getId());
            log.info("Score: {}", evaluation.getScore());
            // 추가로 필요한 로직을 여기에 작성
        }

        // 평가 처리 로직을 추가하고 평가 결과를 어딘가에 저장하거나 처리합니다.

        return "evaluate_users";
    }

}
