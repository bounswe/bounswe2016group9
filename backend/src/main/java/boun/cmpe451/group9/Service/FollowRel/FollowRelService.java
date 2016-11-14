package boun.cmpe451.group9.Service.FollowRel;


import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;

import java.util.List;

/**
 * Service methods for the resource "FollowRel"
 */
public interface FollowRelService {

    /**
     * Adds a new resource "FollowRel"
     * @param followRel the added resource
     */
    void addFollowRel(FollowRel followRel);


    /**
     * Retrieves the resource "FollowRel" with the given id
     * @param id the id of the resource
     * @return the retrieved resource
     */
    FollowRel getFollowRelById(long id);


    /**
     * Updates the resource "FollowRel"
     * @param followRel the updated resource
     */
    void updateFollowRel(FollowRel followRel);


    /**
     * Removes the resource "FollowRel" with the given id
     * @param id the id of the removed resource
     */
    void removeFollowRelById(long id);

    List<User> getFollowingByUserId(long id);

    List<User> getFollowerByUserId(long id);
}
