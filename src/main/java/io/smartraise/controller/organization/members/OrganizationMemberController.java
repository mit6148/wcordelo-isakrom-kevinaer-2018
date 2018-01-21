package io.smartraise.controller.organization.members;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.MemberService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/organization/{id}")
public class OrganizationMemberController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public ResponseEntity getMembers(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(organizationService.getMembers(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public ResponseEntity getAdmins(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(organizationService.getAdmins(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/member/{id2}", method = RequestMethod.POST)
    public ResponseEntity addMember(@PathVariable("id") String id, @RequestBody Member member, Principal principal) {
        try {
            organizationService.addMember(member, UUID.fromString(id));
            return ResponseEntity.ok(organizationService.get(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admin/{id2}", method = RequestMethod.POST)
    public ResponseEntity addAdmin(@PathVariable("id") String id, @RequestBody Member member, Principal principal) {
        try {
            organizationService.addAdmin(member, UUID.fromString(id));
            return ResponseEntity.ok(organizationService.get(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/member/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMember(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        try {
            organizationService.deleteMember(memberService.get(id2), UUID.fromString(id));
            return ResponseEntity.ok(organizationService.get(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admin/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        try {
            organizationService.deleteAdmin(memberService.get(id2), UUID.fromString(id));
            return ResponseEntity.ok(organizationService.get(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
