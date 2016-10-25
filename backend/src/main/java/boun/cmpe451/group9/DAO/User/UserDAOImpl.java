package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.Models.DB.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User u){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
        logger.info("User added successfully. Username: " + u.getUsername());
    }

    public User getUserById(long id){
        Session session = this.sessionFactory.getCurrentSession();
        User u = session.get(User.class, id);
        if(u != null){
            logger.info("User retrieved successfully. Username: " + u.getUsername());
            return u;
        }else{
            logger.error("User id " + id + " NOT FOUND");
            return new User();
        }
    }

    public void updateUser(long id, User u){
        Session session = this.sessionFactory.getCurrentSession();

        if(getUserById(id) != null){
            session.merge(u);
            logger.info("User updated successfully. Username: " + u.getUsername());
        }else{
            logger.error("User id " + id + " NOT FOUND");
        }
    }

    public void removeUser(long id){
        Session session = this.sessionFactory.getCurrentSession();
        User u = session.get(User.class, id);

        if(u != null){
            session.delete(u);
            logger.info("User deleted successfully. Username: " + u.getUsername());
        }else{
            logger.error("User id " + id + " NOT FOUND");
        }
    }
}
