package boun.cmpe451.group9.Service;

import boun.cmpe451.group9.Models.Base;

import java.util.List;

public interface BaseService<T extends Base> {

    /**
     * Finds all objects
     * @return a list of object T
     */
    List<T> findAll();

    /**
     * Retrieves object T with id "id"
     * @param id
     * @return object T with id "id"
     */
    T getById(long id);

    /**
     * Saves T "entity" to database
     * @param entity object that is wanted to be saved
     */
    void save(T entity);

    /**
     * Deletes an object with id "id"
     * @param id id of object that is wanted to be deleted
     */
    void deleteById(long id);

    /**
     * Checks if the object with the given "id" exists
     * @param id id of the object
     * @return TRUE if the object exits, FALSE if not
     */
    boolean checkIfEntityExistsById(long id);
}
