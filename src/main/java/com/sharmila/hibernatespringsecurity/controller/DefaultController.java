/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.controller;

import com.sharmila.hibernatespringsecurity.dao.RoleDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.service.UserService;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import com.sharmila.hibernatespringsecurity.service.UserRolesService;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/")
public class DefaultController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    private RoleDao roleDao;
    private Session session;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView defaultPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid user credentials");
        }
        if (logout != null) {
            model.addObject("message", "Logged out successfully");
            request.getSession().invalidate();
        }

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/user/SignupPage")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("Signup");
        return mv;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminProfile(Principal principal, ModelMap map) {
        String name = principal.getName();

        map.addAttribute("username", name);
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

    @RequestMapping(value = "/admin/AllUsers")
    public ModelAndView getUsers() {

        return new ModelAndView("AllUsers", "user", userService.getAll());
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult result) {
        System.out.println("inside insert");

        Role role = roleDao.getById(2);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRole(roles);
        userService.insert(user);
//            System.out.println("Users "+user.toString());

        return new ModelAndView("redirect:/admin/AllUsers");
    }

    @RequestMapping(value = "admin/editUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userAdd") User user, BindingResult result, @RequestParam("id") int id) {
        String view = "";
       User u = userService.getById(id);
        if (u.getId() != 0) {
            System.out.println("ok");
            Date date = new Date();
            Timestamp t = new Timestamp(date.getTime());
            user.setModifiedDate(t);

            userService.update(user);

        }
        System.out.println(u.getId()+"hhh");
        return "redirect:/admin/AllUsers";
    }

    @RequestMapping(value = "delete")
    public ModelAndView deleteUser(@RequestParam int id) {
        userService.delete(id);
        return new ModelAndView("redirect:/admin/AllUsers");
    }

    @RequestMapping(value = "edit")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute("userEdit") User user, @ModelAttribute UserRoles userRoles) {
        user = userService.getById(id);

        return new ModelAndView("editUser", "user", user);
    }

    @RequestMapping("/info")
    public String info() {
        return "InformationPage";
    }
}
