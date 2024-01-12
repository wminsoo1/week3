package madcamp.week3.controller;

import madcamp.week3.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "getLogin";
    }

    @GetMapping("/user/login/details")
    public String getLoginDetails(Model model) {
        model.addAttribute("user", new User());
        return "postLoginDetails";
    }

    @PostMapping("/user/login")
    public String postLogin(@ModelAttribute User user){
        return "postLoginDetails";
    }
}
