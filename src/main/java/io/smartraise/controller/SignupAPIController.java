package io.smartraise.controller;

import io.smartraise.model.login.Credential;
import io.smartraise.model.login.SignUp;
import io.smartraise.service.AdminService;
import io.smartraise.service.CredentialService;
import io.smartraise.service.DonorService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class SignupAPIController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DonorService donorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody SignUp signUp) {
        try {
            credentialService.create(signUp);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        try {
            memberService.delete(id);
            donorService.delete(id);
            credentialService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
