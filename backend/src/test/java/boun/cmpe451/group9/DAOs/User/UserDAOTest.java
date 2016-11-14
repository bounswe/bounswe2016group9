package boun.cmpe451.group9.DAOs.User;

import boun.cmpe451.group9.BackendApplication;
import boun.cmpe451.group9.DAO.User.UserDAO;
import boun.cmpe451.group9.Models.DB.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BackendApplication.class})
public class UserDAOTest {

    private User user;
    private List<User> userList;
    private int size;

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @BeforeTransaction
    void initUser(){
        userList = userDAO.getAllUsers();
        size = userList.size();

        user = new User();
        user.setAge(20);
        user.setEmail("qwe@qwe.com");
        user.setName("mert");
        user.setPassword("123456789");
        user.setSurname("even");
        user.setUsername("meven");
    }

    @Test
    @Transactional
    @Rollback
    public void testAddUser(){
        userDAO.addUser(user);

        user.setEntityId(userList.get(size).getEntityId());

        Assert.assertEquals(size+1, userList.size());
        Assert.assertSame(user, userList.get(0));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetUserById(){
        int id = 0;
        User retrieved = userDAO.getUserById(id);

        Assert.assertEquals(id, retrieved.getEntityId());
    }
}
