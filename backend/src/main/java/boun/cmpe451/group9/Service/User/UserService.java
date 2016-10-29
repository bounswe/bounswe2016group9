package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

public interface UserService {

    void addUser(User u);

    User getUserById(long id);

    User getUserByUsername(String username);

    void updateUser(long id, User u);

    void removeUser(long id);

    List<User> getAllUsers();

    boolean checkUserExists(long id);

    boolean checkUserExistsByUsername(String username);
}
