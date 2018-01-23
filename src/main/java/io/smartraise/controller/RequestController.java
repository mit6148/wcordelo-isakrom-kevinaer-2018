package io.smartraise.controller;

import io.smartraise.model.Response;
import io.smartraise.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/{id}")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ResponseEntity getRequests(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(requestService.getRequests(id));
    }

    @RequestMapping(value = "/request/{id2}", method = RequestMethod.GET)
    public ResponseEntity getRequest(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        return null;
    }

    @RequestMapping(value = "/request/{id2}/response", method = RequestMethod.POST)
    public ResponseEntity respond(
            @PathVariable("id") String id,
            @PathVariable("id2") String id2,
            @RequestParam("answer") boolean answer,
            Principal principal) {
        requestService.respond(id, id2, answer);
        return ResponseEntity.ok().build();
    }
}
