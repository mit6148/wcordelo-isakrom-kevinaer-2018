package io.smartraise.controller.member;

import io.smartraise.controller.CrudController;
import io.smartraise.model.Response;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.accounts.Payment;
import io.smartraise.model.fundraise.Donation;
import io.smartraise.model.login.Credential.UserType;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private DonationService donationService;

    @Autowired
    private PaymentService paymentService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Member member) {
        if (credentialService.exists(member.getUsername())
                && memberService.create(member)) {
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
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}/payment", method = RequestMethod.POST)
    public ResponseEntity updatePayment(@PathVariable("id") String id,
                                        @RequestBody Payment payment,
                                        Principal principal,
                                        HttpServletResponse response){
        if (paymentService.update(payment)) {
            try {
                response.sendRedirect("/member/"+id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}/payment", method = RequestMethod.GET)
    public ResponseEntity getPayment(@PathVariable("id") String id, Principal principal){
        return ResponseEntity.ok(paymentService.get(id));
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

    @RequestMapping(value = "/{id}/donation", method = RequestMethod.POST)
    public ResponseEntity createDonation(
            @PathVariable("id") String id, @RequestBody Donation donation, Principal principal) {
        if (donationService.create(donation)) {
            return ResponseEntity.ok(donationService.getDonationsByDonor(donation.getDonationId()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(donationService.getDonationsByDonor(id));
    }
}
