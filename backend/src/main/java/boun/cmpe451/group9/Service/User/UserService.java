package boun.cmpe451.group9.Service.User;

import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/**
 * Service methods for the resource "User"
 */
public interface UserService {

    /**
     * Adds a new resource "User"
     * @param user the added resource
     */
    void addUser(User user);

    /**
     * Retrieves the resource "User" with the given id
     * @param id the id of the user
     * @return the retrieved resource
     */
    User getUserById(long id);

    /**
     * Retrieves the resource "User" with the given username
     * @param username the username of the user
     * @return the retrieved resource
     */
    User getUserByUsername(String username);

    /**
     * Updates the resource "User"
     * @param user the updated resource
     */
    void updateUser(User user);

    /**
     * Removes the resource "User" with the given id
     * @param id the id of the user
     */
    void removeUser(long id);

    /**
     * Retrieves the list of all the users in the table "USER"
     * @return the list of all users in the table "USER"
     */
    List<User> getAllUsers();

    /**
     * Checks if the user with the given id exists
     * @param id the id of the user
     * @return TRUE if the user exists, FALSE if not
     */
    boolean checkUserExists(long id);

    /**
     * Checks if the user with the given "username" exists
     * @param username the username of the user
     * @return TRUE if the user exits, FALSE if not
     */
    boolean checkUserExistsByUsername(String username);
}
