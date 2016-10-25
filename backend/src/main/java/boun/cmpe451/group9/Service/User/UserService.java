package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.Models.DB.User;

public interface UserService {

    public void addUser(User u);

    public User getUserById(long id);

    public void updateUser(long id, User u);

    public void removeUser(long id);
}
