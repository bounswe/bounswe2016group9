package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(User u) {
        userDAO.addUser(u);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public void updateUser(long id, User u) {
        userDAO.updateUser(id, u);
    }

    @Override
    public void removeUser(long id) {
        userDAO.removeUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean checkUserExists(long id) {
        return userDAO.checkUserExists(id);
    }

    @Override
    public boolean checkUserExistsByUsername(String username) {
        return userDAO.checkUserByUsername(username);
    }
}
