package io.smartraise.util;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

public class ImageURI {

    public final static String EMPTY_PROFILE = "data:image/png;base64,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";

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
