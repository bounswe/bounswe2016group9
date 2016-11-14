package boun.cmpe451.group9.DAO.Image;

import boun.cmpe451.group9.Models.DB.Image;

/**
 * Database access methods for the table "IMAGE"
 */
public interface ImageDAO {

    /**
     * Adds a new row into "IMAGE" table
     * @param image the obj representation of the added row
     */
    void addImage(Image image);

    /**
     * Updates the row in the table "IMAGE" with the given one
     * @param image the obj representation of the updated row
     */
    void updateImage(Image image);

    /**
     * Retrieves the row with the given id from the table "IMAGE"
     * @param id the id of the row
     * @return the obj representation of the retrieved row
     */
    Image getImageById(long id);

    /**
     * Deletes the row from the table "IMAGE" with the given id
     * @param id the id of the removed row
     */
    void removeImageById(long id);
}
