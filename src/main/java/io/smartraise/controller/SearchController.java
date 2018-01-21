package io.smartraise.controller;

import io.smartraise.service.CharityService;
import io.smartraise.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SearchController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public ResponseEntity getCharities(
            @RequestParam(value = "term", required = false) List<String> searchTerms) {
        try {
            return ResponseEntity.ok(charityService.getCharities(searchTerms));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity getEvents(
            @RequestParam(value = "term", required = false) List<String> searchTerms) {
        try {
            return ResponseEntity.ok(eventService.getEventsByQueries(searchTerms));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
