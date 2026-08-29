package com.auyurx.Controllers;

import com.auyurx.Entity.User;
import com.auyurx.Helpers.Message;
import com.auyurx.Helpers.MessageType;
import com.auyurx.Repository.UserRepository;
import com.auyurx.SecurityConfiguration.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/auth")
public class EmailVerificationController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserRepository userRepository;
    //verify the send mail
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token,
                              HttpSession session,
                              HttpServletRequest request) {

        User user = userRepository.findByEmailToken(token).orElse(null);

        if (user!=null){
            if (user.getEmailToken().equals(token)){
                user.setEnabled(true);
                user.setEmailVerified(true);
                userRepository.save(user);
                session.setAttribute("message", Message.builder()
                        .type(MessageType.red)
                        .content("Your Email is verified.You can Proceed!!!")
                        .build()
                );
                return "success_page";
            }
            session.setAttribute("message", Message.builder()
                    .type(MessageType.red)
                    .content("Your Email is not verified!!!")
                    .build()
            );

            return "error_page";

        }
        session.setAttribute("message", Message.builder()
                .type(MessageType.red)
                .content("Your Email is not verified!!!")
                .build());
        return "error_page";

    }
}