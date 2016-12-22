package boun.cmpe451.group9.Service.FollowTopic;


import boun.cmpe451.group9.DAO.FollowTopic.FollowTopicDAO;
import boun.cmpe451.group9.Models.DB.FollowTopic;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by seha on 10.12.2016.
 */
@SuppressWarnings("DefaultFileTemplate")
@Service
@Transactional(readOnly = true)
public class FollowTopicServiceImpl extends BaseServiceImpl<FollowTopic> implements FollowTopicService {

    private FollowTopicDAO followTopicDAO;

    @Autowired
    public void setFollowTopicDAO(FollowTopicDAO followTopicDAO) { this.followTopicDAO = followTopicDAO; }


    @Override
    public List<Topic> getFollowingTopicsById(long id) { return followTopicDAO.getFollowingTopicsById(id);}

    @Override
    public List<User> getFollowerUsersById(long id) { return  followTopicDAO.getFollowerUsersById(id);}

    @Override
    public boolean checkIfFollowTopicExistsByIds(long userID, long topicID) {
        return followTopicDAO.checkIfFollowTopicExistsByIds(userID,topicID);
    }
}
