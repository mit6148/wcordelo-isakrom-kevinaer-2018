package io.smartraise.controller.organization.event;

import io.smartraise.controller.CrudController;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.DonationService;
import io.smartraise.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/organization/{id}/events")
public class OrganizationEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private DonationService donationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity readAll(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(eventService.getEventsFromOrganization(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity readAllActive(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(eventService.getCurrentEventsFromOrganization(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(value = "/donations", method = RequestMethod.GET)
    public ResponseEntity getAllDonations(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(donationService.getDonationsByOrganization(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
