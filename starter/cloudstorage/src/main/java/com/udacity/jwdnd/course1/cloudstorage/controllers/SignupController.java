package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("newUser") User newUser, Model model){
        return "signup";
    }

    @PostMapping("/signup/save")
        public String saveUser(@ModelAttribute("newUser") User newUser){
       if (userService.getUserByUserName(newUser.getUsername()) != null){
           return "redirect:/signup?userexists";
       }


        try {
            userService.createUser(newUser);

            return "redirect:/login?signupSuccess";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/signup?error";
    }





}
