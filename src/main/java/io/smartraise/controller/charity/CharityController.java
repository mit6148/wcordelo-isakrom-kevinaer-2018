package io.smartraise.controller.charity;

import io.smartraise.controller.CrudController;
import io.smartraise.model.Request;
import io.smartraise.model.fundraise.Charity;
import io.smartraise.service.CharityService;
import io.smartraise.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/charity")
public class CharityController implements CrudController<Charity> {

    @Autowired
    private CharityService charityService;

    @Autowired
    private RequestService requestService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Charity charity) {
        if (charityService.create(charity)){
            charity = charityService.get(charity.getCharityId());
            Request request = requestService.makeRequest(Request.RequestType.VERIFY_CHARITY, charity);
            return ResponseEntity.ok(charity);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable("id") String id, Principal principal) {
        return ResponseEntity.ok(charityService.get(id));
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
            @PathVariable("id") String id, @RequestBody Charity charity, Principal principal) {
        if (charityService.update(charity)) {
            return ResponseEntity.ok(charity);
        } else  {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id, Principal principal) {
        if (charityService.delete(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
