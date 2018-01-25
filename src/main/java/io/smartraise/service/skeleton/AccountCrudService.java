package io.smartraise.service.skeleton;

/**
 *  Service interface for a CRUD service involving accounts models.
 * @param <T>   the type of model for the CRUD service
 */
public interface AccountCrudService<T> {

    /**
     *  Obtains a model
     * @param id    the id of the model being retrieved
     * @return the model
     * @throws Exception
     */
    T get(String id);

    /**
     *  Creates a new model
     * @param t     the  model being created
     * @return true if successfully created model, false otherwise
     */
    boolean create(T t);

    /**
     *  Updates an existing model
     * @param t     the updated model
     * @return true if successfully updated model, false otherwise
     */
    boolean update(T t);

    /**
     *  Deletes a model
     * @param id    the id of the model being deleted
     * @throws Exception
     */
    boolean delete(String id);
}
