package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(HttpServletRequest httpServletRequest, ModelMap model) {
        model.addAttribute("enter", userServiceImpl.findUserByName((String)httpServletRequest.getSession().getAttribute("enterUser")));
        return "hello";
    }
}
