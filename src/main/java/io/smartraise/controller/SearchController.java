package io.smartraise.controller;

import io.smartraise.service.CharityService;
import io.smartraise.service.EventService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private EventService eventService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public ResponseEntity getCharities(
            @RequestParam(value = "term", required = false) List<String> searchTerms) {
        if (searchTerms == null){
            return ResponseEntity.ok(charityService.getAll());
        }
        return ResponseEntity.ok(charityService.getCharities(searchTerms));
    }

    @RequestMapping(value = "/events/current", method = RequestMethod.GET)
    public ResponseEntity getCurrentEvents() {
        return ResponseEntity.ok(eventService.getCurrentEvents());
    }


    @RequestMapping(value = "/events/future", method = RequestMethod.GET)
    public ResponseEntity getFutureEvents() {
        return ResponseEntity.ok(eventService.getFutureEvents());
    }

    @RequestMapping(value = "/events/past", method = RequestMethod.GET)
    public ResponseEntity getPastEvents() {
        return ResponseEntity.ok(eventService.getExpiredEvents());
    }


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity getEvents(
            @RequestParam(value = "term", required = false) List<String> searchTerms) {
        if (searchTerms == null){
            return ResponseEntity.ok(eventService.getAll());
        }
        return ResponseEntity.ok(eventService.getEventsByQueries(searchTerms));
    }


    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public ResponseEntity getOrganizations(
            @RequestParam(value = "term", required = false) List<String> searchTerms) {
        if (searchTerms == null){
            return ResponseEntity.ok(organizationService.getAll());
        }
        return ResponseEntity.ok().build();
    }
}
