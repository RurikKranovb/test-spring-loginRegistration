package com.finnplay.test.controller;

import com.finnplay.test.model.User;
import com.finnplay.test.service.DetailsService;
import com.finnplay.test.service.SecurityService;
import com.finnplay.test.service.UserService;
import com.finnplay.test.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
//@AllArgsConstructor
public class UserController
{

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private DetailsService detailsService;

    @GetMapping({"","/"})
    public String home()
    {
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("birthDate", new Date());

        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") User user, BindingResult result, Model model)
    {
        userValidation.validate(user,result);

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(user);

//        securityService.autoLogin(user.getEmail(), user.getPassword());

        return "/index";
    }

//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
////        if (securityService.isAuthenticated()) {
////            return "redirect:/";
////        }
//
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }

    @GetMapping("/user")
    public String users(User user, Model model)
    {
        model.addAttribute("user", userService.findByEmail(securityService.findLoggedInUsername()));

        return "user";
    }

    @PostMapping("/user")
    public String client(User user, BindingResult result, Model model)
    {

//        userValidation.validate(user,result);
//
//        if (result.hasErrors()) {
//            return "registration";
//        }
//        userService.update(user);

        userService.update(user);

        return "user";
    }

}
