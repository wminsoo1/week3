package madcamp.week3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvaluationController {

    @GetMapping("/evaluatees")
    public String getEvaluatees(){
        return "sad";
    }
}
