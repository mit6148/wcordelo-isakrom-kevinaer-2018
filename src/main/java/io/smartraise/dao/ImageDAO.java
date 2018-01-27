package io.smartraise.dao;

import io.smartraise.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageDAO extends MongoRepository<Image, String> {

    Image getDistinctByIdAndType(String id, Image.ImageType type);

    boolean existsByIdAndType(String id, Image.ImageType type);

    void deleteByIdAndType(String id, Image.ImageType type);
}
