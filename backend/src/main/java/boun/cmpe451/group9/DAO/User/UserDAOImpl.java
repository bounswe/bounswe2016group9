package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {
    @Override
    public User getUserByUsername(String username) {
        return (User) this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM user WHERE user.username = :username")
                .addEntity(User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public boolean checkUserByUsername(String username) {
        return getUserByUsername(username) != null;
    }
}
