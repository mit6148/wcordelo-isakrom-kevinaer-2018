package io.smartraise.service;

import io.smartraise.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    boolean create (MultipartFile image, String id, Image.ImageType imageType);

    boolean create (File image, String id, Image.ImageType imageType);

    String get(String id, Image.ImageType imageType) throws IOException;

    boolean delete(String id, Image.ImageType imageType);

}
