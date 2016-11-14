package boun.cmpe451.group9.DAO.Image;

import boun.cmpe451.group9.Models.DB.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAOImpl implements ImageDAO {

    private SessionFactory sessionFactory;

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(image);
    }

    @Override
    public void updateImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(image);
    }

    @Override
    public Image getImageById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Image.class, id);
    }

    @Override
    public void removeImageById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getImageById(id));
    }
}
