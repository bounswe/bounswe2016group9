package boun.cmpe451.group9.Service.FollowRel;


import boun.cmpe451.group9.DAO.FollowRel.FollowRelDAO;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowRelServiceImpl implements FollowRelService {

    private FollowRelDAO followRelDAO;

    @Autowired
    public void setFollowRelDAO(FollowRelDAO followRelDAO){
        this.followRelDAO = followRelDAO;
    }

    @Override
    public void addFollowRel(FollowRel followRel) {
        followRelDAO.addFollowRel(followRel);
    }

    @Override
    public FollowRel getFollowRelById(long id) {
        return followRelDAO.getFollowRelById(id);
    }

    @Override
    public void updateFollowRel(FollowRel followRel) {
        followRelDAO.updateFollowRel(followRel);
    }

    @Override
    public void removeFollowRelById(long id) {
        followRelDAO.removeFollowRelById(id);
    }

    @Override
    public List<User> getFollowingByUserId(long id) {
        return followRelDAO.getfollowingById(id);
    }

    @Override
    public List<User> getFollowerByUserId(long id) {
        return followRelDAO.getfollowerById(id);
    }
}
