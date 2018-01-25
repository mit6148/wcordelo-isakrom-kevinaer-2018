package io.smartraise.service.skeleton;

import java.util.UUID;

public interface CrudService<T> {

    T get(String id);

    boolean create(T t);

    boolean update(T t);

    boolean delete(String id);

//    boolean isValid(T t);
//
//    boolean exists(String id);
}
