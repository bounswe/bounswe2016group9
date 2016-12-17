package boun.cmpe451.group9.DAO.UserRole;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole> implements UserRoleDAO {
    @Override
    public List<String> findRoleByUsername(String username) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT ro.name FROM user_role r " +
                        "JOIN user u ON u.id = r.user_id " +
                        "JOIN role ro ON ro.id = r.role_id " +
                        "WHERE u.username = :username")
                .setParameter("username", username)
                .list();
    }
}
