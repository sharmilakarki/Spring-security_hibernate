/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.controller;

import com.sharmila.hibernatespringsecurity.service.UserService;
import com.sharmila.hibernatespringsecurity.entity.User;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sharmila
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private UserService userService;

    private Session session;

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public ModelAndView defaultPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid user credentials");
        }
        if (logout != null) {
            model.addObject("message", "Logged out successfully");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/user/SignupPage")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("Signup");
        return mv;
    }

    @RequestMapping(value = "/admin/**")
    public String adminProfile() {

        return "adminDashboard";
    }

    @RequestMapping(value = {"/userprofile"})
    public ModelAndView userProfile() {

        return new ModelAndView("profile");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView errorPage() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("error page");
        return model;
    }

    @RequestMapping(value = "/user/AllUsers")
    public ModelAndView getUsers() {

        return new ModelAndView("AllUsers", "user", userService.getAll());
    }

    @RequestMapping("/user/add")
    public ModelAndView addUser(@ModelAttribute("userAdd") User user) {

        if (user.getId() == 0) {
            userService.insert(user);

        } else {
            System.out.println(user.toString());
            userService.update(user);
        }

        return new ModelAndView("redirect:/user/AllUsers");
    }

    @RequestMapping(value = "delete")
    public ModelAndView deleteUser(@RequestParam int id) {
        userService.delete(id);
        return new ModelAndView("redirect:/user/AllUsers");
    }

    @RequestMapping(value = "edit")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute("userEdit") User user) {
        user = userService.getById(id);
        return new ModelAndView("editUser", "user", user);
    }

    @RequestMapping("/info")
    public String info() {
        return "InformationPage";
    }
}
