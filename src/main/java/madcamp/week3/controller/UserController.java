package madcamp.week3.controller;

import lombok.extern.slf4j.Slf4j;
import madcamp.week3.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/user/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "getLogin";
    }

    @PostMapping("/user/login/details")
    public String postDetailsLogin(@ModelAttribute User user){
        //log.info("user: {}", user.toString());
        return "postLoginDetails";
    }
}