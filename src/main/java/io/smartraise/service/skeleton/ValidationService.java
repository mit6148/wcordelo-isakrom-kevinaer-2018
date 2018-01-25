package io.smartraise.service.skeleton;

public interface ValidationService<T> {
    boolean isValid(T t);

    boolean exists(String id);
}
