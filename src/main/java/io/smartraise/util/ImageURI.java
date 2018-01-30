package io.smartraise.util;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

public class ImageURI {

    @Autowired
    private ApplicationContext context;

    private static final String PROFILE_PROFILE = "/static/img/empty-profile-grey.jpg";
    private static final String PROFILE_ORG = "/static/img/unique_cool_gifts_to_customize_create_fun_classic_round_sticker-r2d7327b97c2a4c15aa3463b8597e14a3_v9wth_8byvr_324.jpg";

    private String emptyOrg;
    private String emptyProfile;
    public ImageURI() {
        emptyOrg = makeURL(PROFILE_ORG);
        emptyProfile = makeURL(PROFILE_PROFILE);
    }

    private String makeURL(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            byte[] imag = IOUtils.toByteArray(resource.getInputStream());
            return "data:image/png;base64,"+encodeBase64String(imag);
        } catch (Exception e){
            return "";
        }
    }

    public String getEmptyOrg() {
        return emptyOrg;
    }

    public String getEmptyProfile() {
        return emptyProfile;
    }
}
