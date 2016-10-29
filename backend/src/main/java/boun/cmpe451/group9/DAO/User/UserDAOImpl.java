package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.Models.DB.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User u){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
    }

    @Override
    public User getUserById(long id){
        Session session = this.sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();

        return (User) session.createSQLQuery("SELECT * FROM user WHERE user.username = :username")
                .addEntity(User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public void updateUser(long id, User u){
        Session session = this.sessionFactory.getCurrentSession();

        session.merge(u);
    }

    @Override
    public void removeUser(long id){
        Session session = this.sessionFactory.getCurrentSession();

        session.delete(session.get(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();

        return (List<User>) session.createSQLQuery("SELECT * FROM user")
                .addEntity(User.class)
                .list();
    }

    @Override
    public boolean checkUserExists(long id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean checkUserByUsername(String username) {
        return getUserByUsername(username) != null;
    }
}
