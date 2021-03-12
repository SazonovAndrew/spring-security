package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.*;

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
        List<User> list = userService.index();
        model.addAttribute("users", userService.index());
        return "index";
    }
    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.allRoles());
        return "new";
    }
    @PostMapping("/admin/new")
    public  String create (@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                           @RequestParam(value = "addRole", required = false) ArrayList<String> userRole, Model model){
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("errorpassword", "passwords don't equals");
            model.addAttribute("allRoles", userService.allRoles());
            return "new";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("allRoles", userService.allRoles());
            return "new";
        }
        userService.addRolesToUser(user, userRole);
        userService.create(user);

        return "redirect:/admin/view";
    }
    @GetMapping("/admin/edit/{id}")
    public String edit( @PathVariable(value = "id")  int id, Model model){
        model.addAttribute("userEdit", userService.getUserById(id));
        model.addAttribute("allRoles", userService.allRoles());
        return "edit";
    }
    @PostMapping("/admin/edit")
    public String update(@ModelAttribute(value = "userEdit") User userEdit,
                         @RequestParam(value = "addRole", required = false) ArrayList<String> userRole, Model model){
        if( userService.userExist(userEdit.getUsername())  &&
                userEdit.getId() != userService.findByUserForUsername(userEdit.getUsername()).getId()) {
            model.addAttribute("nameExists", "such username already exists");
            return "edit";
        }

        if(!userEdit.getPassword().equals(userEdit.getPasswordConfirm())){
            model.addAttribute("errorpassword", "passwords don't equals");
            return "edit";
        }
        userService.addRolesToUser(userEdit, userRole);
        userService.update(userEdit);
        return "redirect:/admin/view";
    }
    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin/view";
    }

}