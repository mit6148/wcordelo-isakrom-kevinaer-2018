package io.smartraise.controller;

import io.smartraise.model.accounts.Member;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CredentialService credentialService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("id") String id, Principal principal){
        try {
            Member member = memberService.get(id);
            if (principal != null && credentialService.verify(principal.getName(), id)) {

                return ResponseEntity.ok(member);
            } else {
                return ResponseEntity.ok(memberService.getPublic(member));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody Member member) {
        try {
            memberService.create(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
