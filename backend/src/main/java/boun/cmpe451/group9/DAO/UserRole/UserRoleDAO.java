package boun.cmpe451.group9.DAO.UserRole;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.UserRole;

import java.util.List;


public interface UserRoleDAO extends BaseDAO<UserRole> {

    List<String> findRoleByUsername(String username);
}
