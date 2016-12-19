package boun.cmpe451.group9.Service.UserRole;

import boun.cmpe451.group9.DAO.UserRole.UserRoleDAO;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Models.DB.UserRole;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService{
    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO){
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public List<String> findRoleByUsername(User user) {
        return userRoleDAO.findRoleByUsername(user.getUsername());
    }
}
