package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;

    @GetMapping(value = "/")
    public String printUsers(HttpServletRequest httpServletRequest, ModelMap model) {
        User users = new User();
        model.addAttribute("userk", users);
        model.addAttribute("enter", userServiceImpl.findUserByName((String)httpServletRequest.getSession().getAttribute("enterUser")));
        model.addAttribute("listRoles", roleServiceImpl.listRoles());
        model.addAttribute("listPersons", userServiceImpl.listUsers());
        return "users";
    }

//    @PostMapping(value = "/edit")
//    public String editUser(@ModelAttribute("listPersons") User user, @RequestParam("role") String roles, @RequestParam("id") Long id, ModelMap model) {
////        roleServiceImpl.updateRole(new Role(id, roles));
////        userServiceImpl.updateUser(user);
////        model.addAttribute("listPersons", userServiceImpl.listUsers());
//        return "redirect:/admin/";
//    }
//
//    @PostMapping(value = "/newuser")
//    public String createUser(@ModelAttribute("listPersons") User user, @RequestParam("prava") Role prava, ModelMap model) {
////        if(userServiceImpl.checkUser(user)) {
////            userServiceImpl.add(user);
////            roleServiceImpl.add(prava);
////            model.addAttribute("listPersons", userServiceImpl.listUsers());
//            return "redirect:/admin/";
////        }
////        return "redirect:error";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam Long id, ModelMap model) {
////        userServiceImpl.deleteUser(id);
//        model.addAttribute("listPersons", userServiceImpl.listUsers());
//        return "redirect:/admin/";
//    }
//
//    @GetMapping("/error")
//    public String errorUser(ModelMap model) {
//        return "error";
//    }
}
