package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/view")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "index";
    }
    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin")
    public  String create (@ModelAttribute("user") User user){
        userService.create(user);
        return "redirect:/admin/view";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.show(id));
        return "edit";
    }
    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        userService.update(id, user);
        return "redirect:/admin/view";
    }


    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin/view";
    }


    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}