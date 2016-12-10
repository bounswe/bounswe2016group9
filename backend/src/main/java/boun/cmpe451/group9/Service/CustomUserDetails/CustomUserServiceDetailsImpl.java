package boun.cmpe451.group9.Service.CustomUserDetails;

import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.DAO.UserRole.UserRoleDAO;
import boun.cmpe451.group9.Models.DB.CustomUserDetails;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomUserServiceDetailsImpl implements CustomUserDetailsService {

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;

    @Autowired
    public CustomUserServiceDetailsImpl(UserDAO userDAO, UserRoleDAO userRoleDAO){
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        List<String> userRoles = userRoleDAO.findRoleByUsername(username);
        return new CustomUserDetails(user, userRoles);
    }
}
