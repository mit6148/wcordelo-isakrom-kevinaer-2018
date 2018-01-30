package io.smartraise.controller.view;

import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.MemberService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class OrganizationViewController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/organization")
    public String getOrganizationForm(Model model, Principal principal, HttpServletResponse response){
        if (principal != null && !principal.getName().isEmpty()) {
            model.addAttribute("organization", new Organization());
            return "signUpOrg";
        } else {
            return "login";
        }
    }

    @PostMapping("/organization")
    public void createOrganization(@ModelAttribute("organization") Organization organization,
                                   Principal principal,
                                   HttpServletResponse response) throws IOException {
        if (principal != null && !principal.getName().isEmpty()) {
            organization.addAdmin(principal.getName());
            if (organizationService.create(organization)) {
                memberService.addOrganization(principal.getName(), organization.getOrganizationId());
            }
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/login?error");
        }
    }

    @GetMapping("/organization/{id}")
    public String getOrganization(@PathVariable("id") String id,
                                      Model model, Principal principal, HttpServletResponse response){
        if (principal != null && !principal.getName().isEmpty()) {
            Organization organization = organizationService.get(id);
            model.addAttribute("organization",organization);
            return "organization";
        } else {
            return "login";
        }
    }
}
