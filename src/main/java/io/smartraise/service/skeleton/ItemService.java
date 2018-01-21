package io.smartraise.service.skeleton;

import java.util.UUID;

public interface ItemService<T> {

    T get(UUID id) throws Exception;

    T create(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(UUID id) throws Exception;

    boolean isValid(T t);
}
