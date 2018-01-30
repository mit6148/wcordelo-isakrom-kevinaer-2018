package io.smartraise.controller.event;

import io.smartraise.controller.CrudController;
import io.smartraise.model.Privilege;
import io.smartraise.model.fundraise.DonationRequest;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private MemberService memberService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Event event) {
        if (charityService.exists(event.getCharity().getCharityId())
                && organizationService.exists(event.getOrganization().getOrganizationId())
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
//            event = eventService.createProfile(event);
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

    @RequestMapping(value = "/{id}/donation", method = RequestMethod.POST)
    public ResponseEntity createDonation(@PathVariable("id") String id,
                                         @RequestBody DonationRequest donationRequest,
                                         Principal principal,
                                         HttpServletResponse response) throws IOException {
        if (principal != null) {
            if (donationRequest.getDonorId().isEmpty()) {
                donationRequest.setDonorId(principal.getName());
            }
            if (donationService.makeDonation(
                    eventService.get(donationRequest.getEventId()),
                    memberService.get(donationRequest.getDonorId()),
                    donationRequest.getAmount())) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } else {
            response.sendRedirect("/login");
            return ResponseEntity.badRequest().build();
        }
    }
}
