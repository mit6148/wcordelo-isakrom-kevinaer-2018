package io.smartraise.controller.view;

import io.smartraise.model.Image;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.accounts.Payment;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class MemberViewController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/member/{id}")
    public String getMember(@PathVariable("id") String id, Model model, Principal principal){
        if (principal != null && principal.getName().equalsIgnoreCase(id)) {
            Member member = memberService.get(id);
            try {
                model.addAttribute("profile_image", imageService.get(id, Image.ImageType.PROFILE));
            } catch (Exception e) {

            }

            Map<String, String> orgImages = new HashMap<>();
            Set<Organization> organizationList =  organizationService.getFromMember(member);
            for (Organization organization: organizationList) {
                try {
                    orgImages.put(organization.getOrganizationId(), imageService.get(organization.getOrganizationId(), Image.ImageType.ORG));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("payment", paymentService.get(id));
            model.addAttribute("profile", member);
            model.addAttribute("orgs", organizationList);
            model.addAttribute("orgsImages", orgImages);
            model.addAttribute("donations", donationService.getDonationsByDonor(id));
            return "member";
        } else {
            return "home";
        }
    }

    @GetMapping("/member/{id}/edit")
    public String getEditMember(@PathVariable("id") String id, Model model, Principal principal, HttpServletResponse response){
        if (principal != null && principal.getName().equalsIgnoreCase(id)) {
            try {
                model.addAttribute("profile_image", imageService.get(id, Image.ImageType.PROFILE));
            } catch (Exception e) {

            }
            Payment payment = paymentService.get(id);
            model.addAttribute("profile", memberService.get(id));
            model.addAttribute("payment", paymentService.get(id));
            model.addAttribute("user", principal.getName());
            return "memberEdit";
        } else {
//                response.sendRedirect("/home");
            return "redirect:/login";
        }
    }

    @PostMapping("/member/{id}/edit")
    public void putEditMember(@PathVariable("id") String id,
                              @ModelAttribute("profile") Member member,
                              Principal principal,
                              HttpServletResponse response){
        try {
            if (principal != null && principal.getName().equalsIgnoreCase(id)) {
                memberService.update(member);
                response.sendRedirect("/member/" + id);
            } else {
                response.sendRedirect("/home");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/member/{id}/payment")
    public void postEditPayment(@PathVariable("id") String id,
                                @ModelAttribute("payment") Payment payment,
                                Principal principal,
                                HttpServletResponse response){
        try {
            if (principal != null && principal.getName().equalsIgnoreCase(id)) {
                paymentService.update(payment);
                response.sendRedirect("/member/" + id);
            } else {
                response.sendRedirect("/home");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
