package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.validation.*;
import java.util.Collections;


@Controller
@RequestMapping
public class RegController {
    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String showRegPage(Model model){
        model.addAttribute("newUser", new User());
        return "reg";
    }
    @PostMapping("/reg")
    public String regUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult,  Model model){
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("errorpassword", "passwords don't equals");
           return "reg";
        }
        if(bindingResult.hasErrors()){
            return  "reg";
        }
        user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
        userService.create(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}