package io.smartraise.controller;

import io.smartraise.model.login.LogIn;
import io.smartraise.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody LogIn logIn) {
        try {
            return ResponseEntity.ok(credentialService.create(logIn));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity updateUser(@RequestBody Credential credential, Principal principal) {
//        return null;
//    }
}
