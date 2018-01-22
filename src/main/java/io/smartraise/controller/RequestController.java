package io.smartraise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin/{id}")
public class RequestController {

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ResponseEntity getRequests(@PathVariable("id") String id, Principal principal) {
        return null;
    }

    @RequestMapping(value = "/request/{id2}", method = RequestMethod.GET)
    public ResponseEntity getRequest(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        return null;
    }

    @RequestMapping(value = "/request/{id2}/response", method = RequestMethod.POST)
    public ResponseEntity respond(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        return null;
    }
}
