package com.auyurx.Controllers;

import com.auyurx.Entity.User;
import com.auyurx.Forms.UserForm;
import com.auyurx.Helpers.Message;
import com.auyurx.Helpers.MessageType;
import com.auyurx.Repository.UserRepository;
import com.auyurx.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Controller
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
public String HomePage(){
//        User user  = (User) httpSession.getAttribute("loggedInUser");
//        if (user == null) return "redirect:/login";
    return "home";
}
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }
    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession httpSession) {

        if (bindingResult.hasErrors()){
            return "register";
        }
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setEmailVerified(false);
        User savedUser = userService.saveUser(user);
        System.out.println("User Saved Successfully------------------");

         return "redirect:/login";

    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password){
        System.out.println("LOGIN METHOD HIT");
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
            return "login";
        }
         if(!user.isEmailVerified()|| !user.isEnabled()){
            System.out.println("User not verified");
            return "login";
        }
        if(passwordEncoder.matches(password,user.getPassword())){
            return "dashboard";
        }
        return "login";
    }
}
