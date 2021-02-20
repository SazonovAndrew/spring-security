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
public class RegController {
    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String show(){

        return "new";
    }









}