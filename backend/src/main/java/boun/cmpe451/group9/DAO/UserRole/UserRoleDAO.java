package boun.cmpe451.group9.DAO.UserRole;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.UserRole;

import java.util.List;


/**
 * DAO methods for UserRole entity
 */
public interface UserRoleDAO extends BaseDAO<UserRole> {

    /**
     * Finds all roles of a user
     * @param username username of the user
     * @return all roles of the user "username"
     */
    List<String> findRoleByUsername(String username);
}
