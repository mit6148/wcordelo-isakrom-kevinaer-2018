package io.smartraise.controller.member;

import io.smartraise.controller.CrudController;
import io.smartraise.model.Response;
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
        if (credentialService.exists(member.getUsername())
                && !credentialService.containsType(UserType.MEMBER, member.getUsername())
                && memberService.create(member)) {
            credentialService.addType(UserType.MEMBER, member.getUsername());
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal){
        return ResponseEntity.ok(memberService.get(id));
    }

    @Override
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Member member, Principal principal){
        if (memberService.update(member)) {
            return ResponseEntity.ok(memberService.get(member.getUsername()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal){
        if (memberService.delete(id)) {
            credentialService.removeType(UserType.MEMBER, id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}/organizations", method = RequestMethod.GET)
    public ResponseEntity getOrganizations(@PathVariable("id") String id, Principal principal){
        if (memberService.exists(id)) {
            return ResponseEntity.ok(
                    organizationService.getFromMember(memberService.get(id)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
