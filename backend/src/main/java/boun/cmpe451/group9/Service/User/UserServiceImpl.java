package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.DAO.Role.RoleDAO;
import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.DAO.UserRole.UserRoleDAO;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Models.DB.UserRole;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private RoleDAO roleDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) { this.userRoleDAO = userRoleDAO; }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) { this.roleDAO = roleDAO; }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public void save(User user){
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleDAO.getById(2)); //ROLE_USER
        userRoleDAO.save(userRole);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public boolean checkUserExistsByUsername(String username) {
        return userDAO.checkUserByUsername(username);
    }
}
