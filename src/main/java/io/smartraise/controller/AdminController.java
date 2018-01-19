package io.smartraise.controller;

import io.smartraise.model.accounts.Administrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createAdmin(@RequestBody Administrator administrator, Principal principal) {
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAdmin(@PathVariable("id") String id, Principal principal) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateAdmin(@PathVariable("id") String id, Principal principal) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(@PathVariable("id") String id, Principal principal) {
        return null;
    }

}
