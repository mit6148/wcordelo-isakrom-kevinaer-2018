package io.smartraise.controller.view;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.accounts.SignUpRequest;
import io.smartraise.model.login.SignUp;
import io.smartraise.security.CustomAuthProvider;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SignupViewController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private CustomAuthProvider authProvider;

    @GetMapping(value = "/login")
    public String getSignUp(Model model) {
        model.addAttribute("user",new SignUp());
        return "login";
    }

    @PostMapping("/signup")
    public String getEditMember(@ModelAttribute("user") SignUp signUp, HttpServletRequest request) {
        try {
            if (credentialService.create(signUp) != null) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signUp.getUsername(), signUp.getPassword());
                request.getSession();

                Authentication authentication = authProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return "redirect:/signup/"+signUp.getUsername();
            } else {
                return "redirect:/signup";
            }
        } catch (Exception e) {
            return "redirect:/home";
        }
    }

    @GetMapping("/signup/{id}")
    public String getCreateMember(@PathVariable("id") String id,  Model model,  Principal principal) {
        if (principal != null && principal.getName().equalsIgnoreCase(id)) {
            if (memberService.exists(id)) {
                return "redirect:/home";
            }
            Member member = new Member();
            member.setUsername(id);
            String email = credentialService.get(id).getEmail();
            member.getContactInformation().setEmail(email);
            SignUpRequest request = new SignUpRequest();
            request.setMember(member);
            model.addAttribute("request", request);
            return "newMemberForm";
        } else {
            return "redirect:/signup";
        }
    }

    @RequestMapping(value = "/signup/{id}", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public String postCreateMember(@PathVariable("id") String id, @ModelAttribute SignUpRequest request, Principal principal) {
        if (principal != null && principal.getName().equalsIgnoreCase(id)) {
            request.getMember().getContactInformation().setUsername(request.getMember().getUsername());
            memberService.create(request.getMember());
            if (request.getPayment() != null && request.getPayment().getNumber() != null) {
                request.getPayment().setUsername(request.getMember().getUsername());
            }
            paymentService.create(request.getPayment());
            return "redirect:/";
        } else {
            return "redirect:/signup";
        }
    }
}
