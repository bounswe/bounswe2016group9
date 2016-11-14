package boun.cmpe451.group9.Services.User;

import boun.cmpe451.group9.BackendApplication;
import boun.cmpe451.group9.Service.User.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BackendApplication.class})
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /*
    @Test
    public void testGetUserById(){
        Assert.
    }*/

}
