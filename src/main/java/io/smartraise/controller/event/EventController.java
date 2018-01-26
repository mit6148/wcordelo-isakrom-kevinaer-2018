package io.smartraise.controller.event;

import io.smartraise.controller.CrudController;
import io.smartraise.model.Privilege;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.CharityService;
import io.smartraise.service.DonationService;
import io.smartraise.service.EventService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/event")
public class EventController implements CrudController<Event> {

    @Autowired
    private EventService eventService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CharityService charityService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Event event) {
        if (charityService.exists(event.getCharity())
                && organizationService.exists(event.getOrganization())
                && (charityService.get(event.getCharity()).getPrivilege() == Privilege.CHARITY_VERIFIED)
                && eventService.create(event)) {
            return ResponseEntity.ok(eventService.get(event.getEventId()));
        } else {
            return ResponseEntity.badRequest().build();
        }
//        try {
//            if (!(charityService.exists(event.getCharity())
//                    && organizationService.exists(event.getOrganization()))
//                && (charityService.get(event.getCharity()).getPrivilege() == Privilege.CHARITY_VERIFIED)) {
//                throw new Exception("Not valid groups");
//            }
//            event = eventService.create(event);
//            return ResponseEntity.ok(event);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e);
//        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(eventService.get(id));
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Event event, Principal principal) {
        if (eventService.update(event)) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        if (eventService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(donationService.getDonationsByEvent(id));
    }
}
