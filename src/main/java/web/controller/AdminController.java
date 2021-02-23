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
        Set<Role> roleSet = new HashSet<>();
        if(userRole.isEmpty()){
            user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
            userService.create(user);
            return "redirect:/admin/view";
        }
        if (userRole.contains("ROLE_ADMIN")){
            roleSet.add(new Role(1L, "ADMIN"));
            user.setRoles(roleSet);
        }
        if (userRole.contains("ROLE_USER")) {
            roleSet.add(new Role(2L, "USER"));
            user.setRoles(roleSet);
        }
        userService.create(user);

        return "redirect:/admin/view";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit( @PathVariable(value = "id")  int id, Model model){
        model.addAttribute("userEdit", userService.show(id));
        model.addAttribute("allRoles", userService.allRoles());
        return "edit";
    }
    @PatchMapping("/admin/edit")
    public String update(@ModelAttribute("userEdit") User userEdit,
                         @RequestParam(value = "addRole", required = false) ArrayList<String> userRole, Model model){
        if(!userEdit.getPassword().equals(userEdit.getPasswordConfirm())){
            model.addAttribute("errorpassword", "passwords don't equals");
            return "edit";
        }
        Set<Role> roleSet = new HashSet<>();
        if(userRole.isEmpty()){
            userEdit.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
            userService.create(userEdit);
            return "redirect:/admin/view";
        }
        if (userRole.contains("ROLE_ADMIN")){
            roleSet.add(new Role(1L, "ADMIN"));
            userEdit.setRoles(roleSet);
        }
        if (userRole.contains("ROLE_USER")) {
            roleSet.add(new Role(2L, "USER"));
            userEdit.setRoles(roleSet);
        }

        userService.update(userEdit);
        return "redirect:/admin/view";
    }


    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin/view";
    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        return "login";
//    }
}