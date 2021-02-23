package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping
public class UserController {


	@GetMapping("/user")
	public String show( Model model){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
		model.addAttribute("user", user);
		return "show";
	}









}