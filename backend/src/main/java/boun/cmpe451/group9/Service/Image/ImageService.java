package boun.cmpe451.group9.Service.Image;


import boun.cmpe451.group9.Models.DB.Image;

/**
 * Service methods for the resource "Image"
 */
public interface ImageService {

    /**
     * Adds a new resource "Image"
     * @param image the added resource
     */
    void addImage(Image image);

    /**
     * Retrieves the resource "Image" with the given id
     * @param id the id of the resource
     * @return the retrieved resource
     */
    Image getImageById(long id);

    /**
     * Updates the resource "Image"
     * @param image the updated resource
     */
    void updateImage(Image image);

    /**
     * Removes the resource "Image" with the given id
     * @param id the id of the resource
     */
    void removeImageById(long id);
}
