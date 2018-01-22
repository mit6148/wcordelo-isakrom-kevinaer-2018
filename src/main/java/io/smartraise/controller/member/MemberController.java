package io.smartraise.controller.member;

import io.smartraise.controller.CrudController;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential.UserType;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/member")
public class MemberController implements CrudController<Member> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Member member) {
        try {
            if (!credentialService.exists(member.getUsername())) {
                throw new Exception("No user found matching that username");
            }
            if (credentialService.containsType(UserType.MEMBER, member.getUsername())) {
                throw new Exception("User account wiht this user name already exists");
            }
            memberService.create(member);
            credentialService.addType(UserType.MEMBER, member.getUsername());
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal){
//        Administrator administrator = new Administrator();
        try {
            Member member = memberService.get(id);
//            System.out.println(member);
//            if (principal != null && credentialService.verify(principal.getName(), id)) {
            return ResponseEntity.ok(member);
//            } else {
//                return ResponseEntity.ok(member);
//            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Member member, Principal principal){
        try {
            if (!member.getUsername().equals(id)) {
                throw new Exception("Forbidden!");
            }
            memberService.update(member);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal){
        try {
            memberService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/organizations", method = RequestMethod.GET)
    public ResponseEntity getOrganizations(@PathVariable("id") String id, Principal principal){
        try {
            Member member = memberService.get(id);
            if (member == null){
                throw new Exception("User doesn't exist");
            }
            return ResponseEntity.ok(organizationService.getFromMember(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
