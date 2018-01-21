package io.smartraise.service.skeleton;

/**
 *
 * @param <T>
 */
public interface AccountService<T> {

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    T get(String id) throws Exception;

    /**
     *
     * @param t
     * @throws Exception
     */
    void create(T t) throws Exception;

    /**
     *
     * @param t
     * @throws Exception
     */
    void update(T t) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    boolean isValid(T t);
}
