package boun.cmpe451.group9.Service.Tag;

import boun.cmpe451.group9.DAO.Tag.TagDAO;
import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {

    private TagDAO tagDAO;

    @Autowired
    public void setTagDAO(TagDAO tagDAO){
        this.tagDAO = tagDAO;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }

    @Override
    public boolean checkIfTagExistsByName(String name) {
        return tagDAO.checkIfTagExistsByName(name);
    }

    @Override
    public List<Tag> getTagsByTopicId(long id) {
        return tagDAO.getTagsByTopicId(id);
    }

    @Override
    public List<Tag> getTagsByPostId(long id) { return tagDAO.getTagsByPostId(id);}
}
