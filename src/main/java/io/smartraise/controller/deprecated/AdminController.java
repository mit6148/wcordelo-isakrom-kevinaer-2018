package io.smartraise.controller.deprecated;

import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.login.Credential;
import io.smartraise.service.deprecated.AdminService;
import io.smartraise.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
@Deprecated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CredentialService credentialService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String adminForm(Model model){
//        model.addAttribute("admin", new Administrator());
//        return "admin";
//    }

    //TODO Remove exception handler

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAdmin(@RequestBody Administrator administrator, Principal principal) {
        try {
            if (credentialService.isAdmin(principal.getName())){
                adminService.create(administrator);
                credentialService.addType(Credential.UserType.ADMINISTRATOR, administrator.getUsername());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Permission Denied");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAdmin(@PathVariable("id") String id, Principal principal) {
        try {
            if (principal != null && credentialService.verify(principal.getName(), id)){
                return ResponseEntity.ok(adminService.get(id));
            } else if (principal != null && credentialService.isAdmin(principal.getName())){
                return ResponseEntity.ok(adminService.get(id));
            } else {
                throw new Exception("Not Allowed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateAdmin(
            @PathVariable("id") String id,@RequestBody Administrator administrator, Principal principal) {
        try {
            if (principal != null && credentialService.verify(principal.getName(), id)){
                adminService.update(administrator);
                return ResponseEntity.ok().build();
            } else {
                throw new Exception("Not Allowed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(
            @PathVariable("id") String id,@RequestBody Administrator administrator, Principal principal) {
        try {
            if (principal != null && credentialService.verify(principal.getName(), id)){
                adminService.delete(administrator.getUsername());
                return ResponseEntity.ok().build();
            } else {
                throw new Exception("Not Allowed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
