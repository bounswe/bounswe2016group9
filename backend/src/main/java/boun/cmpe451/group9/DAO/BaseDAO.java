package boun.cmpe451.group9.DAO;

import boun.cmpe451.group9.Models.Base;

import java.util.List;


/**
 * Includes methods for base entity
 * @param <T> class of the entity
 */
public interface BaseDAO<T extends Base> {

    /**
     * Finds all T in DB
     * @return all T available
     */
    List<T> findAll();

    /**
     * Finds T with given id
     * @param id id of the T
     * @return T with the given id
     */
    T getById(long id);

    /**
     * Saves given T
     * @param entity T we want to
     */
    void save(T entity);

    /**
     * Deletes T with the given id
     * @param id id of T we want to delete
     */
    void deleteById(long id);

    /**
     * Checks if T with the given id exists
     * @param id id of T
     * @return TRUE if exists, FALSE otherwise
     */
    boolean checkIfEntityExistsById(long id);
}
