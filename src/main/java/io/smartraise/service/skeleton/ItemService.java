package io.smartraise.service.skeleton;

import java.util.UUID;

public interface ItemService<T> {

    T get(String id) throws Exception;

    T create(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(String id) throws Exception;

    boolean isValid(T t);

    boolean exists(String id);
}
