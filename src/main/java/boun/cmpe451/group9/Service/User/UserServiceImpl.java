package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User u) {
        this.userDAO.addUser(u);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id, User u) {
        this.userDAO.updateUser(id, u);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        this.userDAO.removeUser(id);
    }
}
