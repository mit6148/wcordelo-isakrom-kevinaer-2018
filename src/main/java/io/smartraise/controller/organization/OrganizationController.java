package io.smartraise.controller.organization;

import io.smartraise.controller.CrudController;
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
@RequestMapping("/api/organization")
public class OrganizationController implements CrudController<Organization> {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Organization organization) {
        try {
            organizationService.create(organization);
            for (String member: organization.getMembers()) {
                memberService.addOrganization(member, organization.getOrganizationId());
            }
            return ResponseEntity.ok(organization);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(organizationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Organization organization, Principal principal) {
        try {
            if (!organization.getOrganizationId().equals(id)){
                throw new Exception("Organization id doesn't match organization");
            }
            organizationService.update(organization);
            return ResponseEntity.ok(organization);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        try {
            organizationService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
