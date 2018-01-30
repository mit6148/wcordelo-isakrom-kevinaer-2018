package io.smartraise.controller.view;

import io.smartraise.model.Image;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Event;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrganizationViewController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private EventService eventService;

    @GetMapping("/organization")
    public String getOrganizationForm(Model model, Principal principal, HttpServletResponse response){
        if (principal != null && !principal.getName().isEmpty()) {
            model.addAttribute("organization", new Organization());
            return "signUpOrg";
        } else {
            return "redirect:/login";
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
//        if (principal != null && !principal.getName().isEmpty()) {
            Organization organization = organizationService.get(id);
            Map<String, String> member_images = new HashMap<>();
            List<List<Member>> lists = new ArrayList<>();
            List<Member> listOfThree = new ArrayList<>();
            for (Member member: memberService.getMembersFromOrganization(organization)) {
                listOfThree.add(member);
                if (listOfThree.size() == 3) {
                    lists.add(listOfThree);
                    listOfThree = new ArrayList<>();
                }
                try {
                    member_images.put(member.getUsername(), imageService.get(member.getUsername(), Image.ImageType.PROFILE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("organization",organization);
            model.addAttribute("members",lists);
            model.addAttribute("member_images",member_images);
            model.addAttribute("events",
                    eventService.getCurrentEventsFromOrganization(organization.getOrganizationId()));
            return "organization";
//        } else {
//            return "login";
//        }
    }
}
