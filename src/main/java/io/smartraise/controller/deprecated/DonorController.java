package io.smartraise.controller.deprecated;

import io.smartraise.controller.CrudController;
import io.smartraise.model.accounts.Donor;
import io.smartraise.model.fundraise.Donation;
import io.smartraise.model.login.Credential.UserType;
import io.smartraise.service.CredentialService;
import io.smartraise.service.DonationService;
import io.smartraise.service.deprecated.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/donor")
@Deprecated
public class DonorController implements CrudController<Donor> {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private CredentialService credentialService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Donor donor) {
        if (credentialService.exists(donor.getUsername())
                && credentialService.containsType(UserType.DONOR, donor.getUsername())
                && donorService.create(donor)) {
            credentialService.addType(UserType.DONOR, donor.getUsername());
            return ResponseEntity.ok(donor);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(donorService.get(id));
    }

    @Override
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Donor donor, Principal principal) {
        if (donorService.update(donor)) {
            return ResponseEntity.ok(donor);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        if (donorService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
//        try {
//            donorService.delete(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

    @RequestMapping(value = "/{id}/donation", method = RequestMethod.POST)
    public ResponseEntity createDonation(
            @PathVariable("id") String id, @RequestBody Donation donation, Principal principal) {
        if (donationService.create(donation)) {
            return ResponseEntity.ok(donationService.getDonationsByDonor(donation.getDonationId()));
        } else {
            return ResponseEntity.badRequest().build();
        }
//        try {
//            if (!id.equals(donation.getDonor())) {
//                throw new Exception("Donation donor does not match donor id");
//            }
//            donation = donationService.create(donation);
//            return ResponseEntity.ok(donation);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

    @RequestMapping(value = "/{id}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(donationService.getDonationsByDonor(id));
    }
}
