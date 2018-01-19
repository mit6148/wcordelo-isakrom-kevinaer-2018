package io.smartraise.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public ResponseEntity getCharities(@RequestParam(value = "term", required = false)List<String> searchTerms) {
        return null;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity getEvents(@RequestParam(value = "term", required = false)List<String> searchTerms) {
        return null;
    }
}
