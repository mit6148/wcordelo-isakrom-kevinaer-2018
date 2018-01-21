package io.smartraise.controller;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface CrudController<T> {

    ResponseEntity create(T t);

    ResponseEntity read(String id, Principal principal);

    ResponseEntity update(String id, T t, Principal principal);

    ResponseEntity delete(String id, Principal principal);
}
