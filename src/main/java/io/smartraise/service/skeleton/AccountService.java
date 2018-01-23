package io.smartraise.service.skeleton;

/**
 *  Service interface for a CRUD service involving accounts models.
 * @param <T>   the type of model for the CRUD service
 */
public interface AccountService<T> {

    /**
     *  Obtains a model
     * @param id    the id of the model being retrieved
     * @return the model
     * @throws Exception
     */
    T get(String id) throws Exception;

    /**
     *  Creates a new model
     * @param t     the  model being created
     * @throws Exception
     */
    void create(T t) throws Exception;

    /**
     *  Updates an existing model
     * @param t     the updated model
     * @throws Exception
     */
    void update(T t) throws Exception;

    /**
     *  Deletes a model
     * @param id    the id of the model being deleted
     * @throws Exception
     */
    void delete(String id) throws Exception;

    boolean isValid(T t);

    boolean exists(String id);
}
