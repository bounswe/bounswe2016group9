package boun.cmpe451.group9.Service;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class BaseServiceImpl<T extends Base> implements BaseService<T> {

    private BaseDAO<T> baseDAO;

    @Autowired
    public void setBaseDAO(BaseDAO<T> baseDAO){
        this.baseDAO = baseDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.baseDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(long id) {
        return this.baseDAO.getById(id);
    }

    @Override
    @Transactional
    public void save(T entity) {
        this.baseDAO.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        this.baseDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkIfEntityExistsById(long id) {
        return this.baseDAO.checkIfEntityExistsById(id);
    }
}
