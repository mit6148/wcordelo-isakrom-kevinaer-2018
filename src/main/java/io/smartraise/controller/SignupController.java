package io.smartraise.controller;

import io.smartraise.dao.CredentialDAO;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;
import io.smartraise.model.login.SignUp;
import io.smartraise.service.AdminService;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SignupController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupForm(Model model){
        return new ModelAndView("signup","login",new SignUp());
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createUser(
            @Valid @ModelAttribute("login") SignUp signUp,
            BindingResult result,
            ModelMap model) {
        try {
            Credential credential = credentialService.create(signUp);
            model.addAttribute("username", credential.getUsername());
            model.addAttribute("email", credential.getEmail());
            return "user";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView success(Principal principal, Model model){
        try {
            return new ModelAndView("user", "user", memberService.get(principal.getName()));
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/userAdmin", method = RequestMethod.GET)
    public ModelAndView successAdmin(Principal principal, Model model){
        try {
            if (principal != null && credentialService.isAdmin(principal.getName())){
                return new ModelAndView("adminView", "user", adminService.get(principal.getName()));
            } else {
                throw new Exception("Not allowed");
            }
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }
}
