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
@RequestMapping("/api/organization/{id}")
public class OrganizationMemberController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public ResponseEntity getMembers(@PathVariable("id") String id, Principal principal) {
        try {
            Organization organization = organizationService.get(id);
            if (organization == null) {
                throw new Exception("No organization found");
            }
            return ResponseEntity.ok(memberService.getMembersFromOrganization(organization));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public ResponseEntity getAdmins(@PathVariable("id") String id, Principal principal) {
        try {
            Organization organization = organizationService.get(id);
            if (organization == null) {
                throw new Exception("No organization found");
            }
            return ResponseEntity.ok(memberService.getAdminsFromOrganization(organization));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public ResponseEntity addMember(@PathVariable("id") String id, @RequestBody Member member, Principal principal) {
        try {
            organizationService.addMember(member, id);
            memberService.addOrganization(member.getUsername(), id);
            return ResponseEntity.ok(organizationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity addAdmin(@PathVariable("id") String id, @RequestBody Member member, Principal principal) {
        try {
            organizationService.addAdmin(member, id);
            memberService.addOrganization(member.getUsername(), id);
            return ResponseEntity.ok(organizationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/member/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMember(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        try {
            organizationService.deleteMember(memberService.get(id2), id);
            memberService.removeOrganization(id2, id);
            return ResponseEntity.ok(organizationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/admin/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(
            @PathVariable("id") String id, @PathVariable("id2") String id2, Principal principal) {
        try {
            organizationService.deleteAdmin(memberService.get(id2), id);
            return ResponseEntity.ok(organizationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
