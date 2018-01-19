package io.smartraise.service;

public interface Service<T> {

    T get(String id) throws Exception;

    void create(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(T t) throws Exception;
}
