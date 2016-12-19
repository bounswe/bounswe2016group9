package boun.cmpe451.group9.Service.UserRole;


import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Models.DB.UserRole;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {
    List<String> findRoleByUsername(User user);
}
