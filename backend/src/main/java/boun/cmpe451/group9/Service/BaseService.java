package boun.cmpe451.group9.Service;

import boun.cmpe451.group9.Models.Base;

import java.util.List;

public interface BaseService<T extends Base> {

    List<T> findAll();

    T getById(long id);

    void save(T entity);

    void deleteById(long id);

    boolean checkIfEntityExistsById(long id);
}
