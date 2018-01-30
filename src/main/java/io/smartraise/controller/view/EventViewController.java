package io.smartraise.controller.view;

import io.smartraise.model.fundraise.Charity;
import io.smartraise.model.fundraise.DonationRequest;
import io.smartraise.model.fundraise.Event;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.CharityService;
import io.smartraise.service.DonationService;
import io.smartraise.service.EventService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class EventViewController {

    @Autowired
    private EventService eventService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private CharityService charityService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/event")
    public String createEventForm(@Param("org") String org, Model model, Principal principal) {
        if (principal != null) {
            Organization organization = organizationService.get(org);
            Event event = new Event();
            event.setOrganization(organization);
            model.addAttribute("organization", organization);
            model.addAttribute("event", event);
            System.out.println(event.getStartDate());
            model.addAttribute("charities", charityService.getAllSorted());
            model.addAttribute("selectedCharity", new Charity());
            return "createEvent";
        } else {
            return "login";
        }
    }

    @PostMapping("/event")
    public void createEvent(@ModelAttribute("event") Event event,
                            HttpServletResponse response, Principal principal) throws IOException {
        if (principal != null
                && event.getOrganization().getMembers().contains(principal.getName())
                && eventService.create(event)) {
            response.sendRedirect("/event/"+event.getEventId());
//            response.sendRedirect("/");
        } else {
            response.sendRedirect("/");
        }
    }

    @GetMapping("/event/{id}")
    public String getEvent(@PathVariable("id") String id, Model model, Principal principal){
        model.addAttribute("event", eventService.get(id));
        model.addAttribute("donations", donationService.getDonationsByEvent(id));
        return "event";
    }

    @GetMapping("/home")
    public String getMember(@PathVariable("id") String id, Model model, Principal principal){
        model.addAttribute("past", eventService.getExpiredEvents());
        model.addAttribute("current", eventService.getCurrentEvents());
        model.addAttribute("future", eventService.getExpiredEvents());
        return "home";
    }


//    @PostMapping("/event/{id}/donations")
//    public String getMember(@PathVariable("id") String id, @RequestBody DonationRequest donationRequest, Principal principal){
//        model.addAttribute("event", eventService.get(id));
//        model.addAttribute("donations", donationService.getDonationsByEvent(id));
//        return "event";
//    }
}
