package io.smartraise.service.impl;

import io.smartraise.dao.ImageDAO;
import io.smartraise.model.Image;
import io.smartraise.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public boolean create(MultipartFile file, String id, Image.ImageType type) {
        if (imageDAO.existsByIdAndType(id, type)) {
            this.delete(id, type);
        }
        Image image = new Image(file, id, type);
        imageDAO.save(image);
        return true;
    }

    @Override
    public boolean create(File file, String id, Image.ImageType type) {
        if (imageDAO.existsByIdAndType(id, type)) {
            this.delete(id, type);
        }
        Image image = new Image(file, id, type);
        imageDAO.save(image);
        return true;
    }



    @Override
    public String get(String id, Image.ImageType imageType) throws IOException {
        return "data:image/png;base64, "+imageDAO.getDistinctByIdAndType(id, imageType).getBytes();
    }

    @Override
    public boolean delete(String id, Image.ImageType imageType) {
        imageDAO.deleteByIdAndType(id, imageType);
        return true;
    }
}
