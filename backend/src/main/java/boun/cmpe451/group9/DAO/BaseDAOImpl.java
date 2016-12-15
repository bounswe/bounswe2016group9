package boun.cmpe451.group9.DAO;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
@Repository
public abstract class BaseDAOImpl<T extends Base> implements BaseDAO<T> {

    private Class<? extends T> genericType;

    private SessionFactory sessionFactory;

    public BaseDAOImpl(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        genericType = (Class) pt.getActualTypeArguments()[0];
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(genericType).list();
    }

    @Override
    public T getById(long id) {
        return sessionFactory.getCurrentSession().get(genericType, id);
    }

    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void deleteById(long id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public boolean checkIfEntityExistsById(long id) {
        return getById(id) != null;
    }
}
