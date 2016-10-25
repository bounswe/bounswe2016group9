package boun.cmpe451.group9.Controllers.User;

import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User u){
        userService.updateUser(id, u);
        return u;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User u){
        userService.addUser(u);
        return u;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.removeUser(id);
        return "User " + id + " deleted successfully.";
    }
}
