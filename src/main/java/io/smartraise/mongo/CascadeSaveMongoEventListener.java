package io.smartraise.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.util.ReflectionUtils;

/**
 *  http://www.baeldung.com
 */
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
    }

//    @Override
//    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
//        Object source = event.getSource();
//        ReflectionUtils.doWithFields(source.getClass(), new CascadeDeleteCallback(source, mongoOperations));
//    }
}
