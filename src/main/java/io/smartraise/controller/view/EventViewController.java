package io.smartraise.controller.view;

import io.smartraise.model.fundraise.DonationRequest;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.DonationService;
import io.smartraise.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class EventViewController {

    @Autowired
    private EventService eventService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/event/{id}")
    public String getMember(@PathVariable("id") String id, Model model, Principal principal){
        model.addAttribute("event", eventService.get(id));
        model.addAttribute("donations", donationService.getDonationsByEvent(id));
        return "event";
    }

//    @PostMapping("/event/{id}/donations")
//    public String getMember(@PathVariable("id") String id, @RequestBody DonationRequest donationRequest, Principal principal){
//        model.addAttribute("event", eventService.get(id));
//        model.addAttribute("donations", donationService.getDonationsByEvent(id));
//        return "event";
//    }
}
