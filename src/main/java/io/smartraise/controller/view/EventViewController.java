package io.smartraise.controller.view;

import io.smartraise.model.fundraise.Event;
import io.smartraise.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class EventViewController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event/{id}")
    public String getMember(@PathVariable("id") String id, Model model, Principal principal){
        model.addAttribute("event", eventService.get(id));
        return "event";
    }
}
