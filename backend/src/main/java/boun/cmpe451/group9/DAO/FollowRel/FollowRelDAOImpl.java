package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class FollowRelDAOImpl extends BaseDAOImpl<FollowRel> implements FollowRelDAO {
    @Override
    public List<User> getFollowingById(long userId) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT u.* FROM follow_rel r JOIN user u ON u.id = r.following_id WHERE r.follower_id = :id")
                .addEntity(User.class)
                .setParameter("id", userId)
                .list();
    }
    @Override
    public List<User> getFollowerById(long userId) {
        return  this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT u.* FROM follow_rel r JOIN user u ON u.id = r.follower_id WHERE r.following_id = :id")
                .addEntity(User.class)
                .setParameter("id", userId)
                .list();
    }
}
