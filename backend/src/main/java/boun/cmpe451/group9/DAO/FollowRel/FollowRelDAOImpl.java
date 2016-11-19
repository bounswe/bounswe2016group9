package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"unused", "unchecked"})
@Repository
public class FollowRelDAOImpl extends BaseDAOImpl<FollowRel> implements FollowRelDAO {
    @Override
    public List<User> getFollowingById(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.following_id FROM relation r JOIN user u ON (u.id = r.follower_id) WHERE (u.id = :id)")
                .addEntity(User.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<User> getFollowerById(long id) {
        return  this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.follower_id FROM relation r JOIN user u ON (u.id = r.following_id) WHERE (u.id = :id)")
                .addEntity(User.class)
                .setParameter("id", id)
                .list();
    }
}
