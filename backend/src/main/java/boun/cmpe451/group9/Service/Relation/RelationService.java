package boun.cmpe451.group9.Service.Relation;


import boun.cmpe451.group9.Models.DB.Relation;

import java.util.List;

/**
 * Service methods for the resource "Relation"
 */
public interface RelationService {

    /**
     * Adds a new resource "Relation"
     * @param relation the added resource
     */
    void addRelation(Relation relation);

    /**
     * Retrieves the resource "Relation" with the given id
     * @param id the id of the resource
     * @return the retrieved resource
     */
    Relation getRelationById(long id);

    List<Relation> getRelationFromTopicByTopicId(long id);

    /**
     * Updates the resource "Relation"
     * @param relation the updated relation
     */
    void updateRelation(Relation relation);

    /**
     * Removes the resource "Relation" with the given id
     * @param id the id of the deleted resource
     */
    void removeRelation(long id);



    boolean checkIfRelationExistsById(long id);

    boolean checkIfRelationExistsByTopicIds(long from, long to);
}
