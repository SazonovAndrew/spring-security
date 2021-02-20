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
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
//	public String show(@ModelAttribute("user") User user, Model model) {
//		model.addAttribute("user", userDao.show(user.getId()));
//		return "jm-spring/show";
//	}
	public String show(){
		return "welcome";
	}









}