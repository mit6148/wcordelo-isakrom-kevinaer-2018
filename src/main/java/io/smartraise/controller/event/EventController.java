package io.smartraise.controller.event;

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
@RequestMapping("/api/event")
public class EventController implements CrudController<Event> {

    @Autowired
    private EventService eventService;

    @Autowired
    private DonationService donationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Event event) {
        try {
            eventService.create(event);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(eventService.get(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Event event, Principal principal) {
        try {
            eventService.update(event);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        try {
            eventService.delete(UUID.fromString(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @RequestMapping(value = "/{id}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(donationService.getDonationsByEvent(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
