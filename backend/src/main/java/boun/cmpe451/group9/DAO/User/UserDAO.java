package boun.cmpe451.group9.DAO.User;

import boun.cmpe451.group9.Models.DB.User;

public interface UserDAO {

    public void addUser(User u);

    public User getUserById(long id);

    public void updateUser(long id, User u);

    public void removeUser(long id);
}
