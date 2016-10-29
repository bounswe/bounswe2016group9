package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

public interface UserDAO {

    void addUser(User u);

    User getUserById(long id);

    User getUserByUsername(String username);

    void updateUser(long id, User u);

    void removeUser(long id);

    List<User> getAllUsers();

    boolean checkUserExists(long id);

    boolean checkUserByUsername(String username);
}
