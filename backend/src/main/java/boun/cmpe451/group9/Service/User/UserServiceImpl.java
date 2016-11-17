package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean checkUserExistsByUsername(String username) {
        return userDAO.checkUserByUsername(username);
    }
}
