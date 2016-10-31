package boun.cmpe451.group9.Service.Image;

import boun.cmpe451.group9.DAO.Image.ImageDAO;
import boun.cmpe451.group9.Models.DB.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private ImageDAO imageDAO;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO){
        this.imageDAO = imageDAO;
    }

    @Override
    public void addImage(Image image) {
        imageDAO.addImage(image);
    }

    @Override
    public Image getImageById(long id) {
        return imageDAO.getImageById(id);
    }

    @Override
    public void updateImage(Image image) {
        imageDAO.updateImage(image);
    }

    @Override
    public void removeImageById(long id) {
        imageDAO.removeImageById(id);
    }
}
