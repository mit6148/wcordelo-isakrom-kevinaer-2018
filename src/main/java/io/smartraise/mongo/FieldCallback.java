package io.smartraise.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

/**
 * http://www.baeldung.com
 */
public class FieldCallback implements ReflectionUtils.FieldCallback {
    private boolean idFound;

    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }
}
