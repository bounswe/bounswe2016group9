package boun.cmpe451.group9.Service.FollowRel;


import boun.cmpe451.group9.DAO.FollowRel.FollowRelDAO;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FollowRelServiceImpl extends BaseServiceImpl<FollowRel> implements FollowRelService {

    private FollowRelDAO followRelDAO;

    @Autowired
    public void setFollowRelDAO(FollowRelDAO followRelDAO){
        this.followRelDAO = followRelDAO;
    }

    @Override
    public List<User> getFollowingByUserId(long id) {
        return followRelDAO.getFollowingById(id);
    }

    @Override
    public List<User> getFollowerByUserId(long id) {
        return followRelDAO.getFollowerById(id);
    }

    @Override
    public boolean checkIfFollowRelExistsByIds(long followerID, long followingID) {
        return followRelDAO.checkIfFollowRelExistsByIds(followerID,followingID);
    }
}
