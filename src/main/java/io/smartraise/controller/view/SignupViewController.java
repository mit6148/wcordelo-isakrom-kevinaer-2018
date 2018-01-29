package io.smartraise.controller.view;

import io.smartraise.model.Image;
import io.smartraise.model.login.SignUp;
import io.smartraise.security.CustomAuthProvider;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.security.Security;

@Controller
public class SignupViewController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CustomAuthProvider authProvider;

    @GetMapping(value = "/signup")
    public String getSignUp(Model model) {
        model.addAttribute("user",new SignUp());
        return "signup2";
    }

    @PostMapping("/signup")
    public void getEditMember(@ModelAttribute("user") SignUp signUp, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (credentialService.create(signUp) != null) {
                Resource resource = context.getResource("classpath:blank.png");
                imageService.create(resource.getFile(), signUp.getUsername(), Image.ImageType.PROFILE);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signUp.getUsername(), signUp.getPassword());
                request.getSession();

                Authentication authentication = authProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                response.sendRedirect("/member/"+signUp.getUsername());
            } else {
                response.sendRedirect("/home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
