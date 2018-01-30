package io.smartraise.controller.view;

import io.smartraise.model.Image;
import io.smartraise.service.ImageService;
import io.smartraise.util.ImageURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageURI imageURI;

    @RequestMapping(value = "/profile/member/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void uploadProfile(@PathVariable("id") String id,
                                @RequestParam("file") MultipartFile file,
                                HttpServletResponse response) throws IOException{
        imageService.create(file, id, Image.ImageType.PROFILE);
        response.sendRedirect("/member/"+id);
    }

    @RequestMapping(value = "/profile/member/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity getProfile(@PathVariable("id") String id) {
        try {
            String string = imageService.get(id, Image.ImageType.PROFILE);
            return ResponseEntity.ok(string);
        } catch (Exception e) {
            return ResponseEntity.ok(imageURI.getEmptyProfile());
        }
    }
}
