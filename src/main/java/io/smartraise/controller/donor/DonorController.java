package io.smartraise.controller.donor;

import io.smartraise.controller.CrudController;
import io.smartraise.model.accounts.Donor;
import io.smartraise.model.donations.Donation;
import io.smartraise.service.DonationService;
import io.smartraise.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/donor")
public class DonorController implements CrudController<Donor> {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonationService donationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Donor donor) {
        try {
            donorService.create(donor);
            return ResponseEntity.ok(donor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(donorService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Donor donor, Principal principal) {
        try {
            donorService.update(donor);
            return ResponseEntity.ok(donor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        try {
            donorService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/donation", method = RequestMethod.POST)
    public ResponseEntity createDonation(
            @PathVariable("id") String id, @RequestBody Donation donation, Principal principal) {
        try {
            if (donorService.get(id).getUsername().equals(donation.getDonor().getUsername())){
                donationService.create(donation);
                return ResponseEntity.ok(donation);
            } else {
                return ResponseEntity.badRequest().body("Donor and donation donor don't match");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/donations", method = RequestMethod.GET)
    public ResponseEntity getDonations(@PathVariable("id") String id, Principal principal) {
        try {
            return ResponseEntity.ok(donationService.getDonationsByDonor(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
