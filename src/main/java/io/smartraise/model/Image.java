package io.smartraise.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

public class Image {

    public enum ImageType { PROFILE, COVER}

    private String bytes;
    private String mime;
    private String id;
    private ImageType type;

    public Image() {
    }

    public Image(MultipartFile multipartFile, String id, ImageType type) {
        this.id = id;
        try {
            this.bytes = encodeBase64String(multipartFile.getBytes());
        } catch (IOException e) {
            this.bytes = encodeBase64String(new byte[0]);
        }
        this.type = type;
    }

    public Image(File file, String id, ImageType type) {
        this.id = id;
        try {
            byte[] bytesArray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            this.bytes = encodeBase64String(bytesArray);
        } catch (IOException e) {
            this.bytes = encodeBase64String(new byte[0]);
        }
        this.type = type;
    }


    public String getBytes() {
        return bytes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(getId(), image.getId()) &&
                getType() == image.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }
}
