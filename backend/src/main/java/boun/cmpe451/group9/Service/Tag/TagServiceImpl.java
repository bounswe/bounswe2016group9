package boun.cmpe451.group9.Service.Tag;

import boun.cmpe451.group9.DAO.Tag.TagDAO;
import boun.cmpe451.group9.Models.DB.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TagServiceImpl implements TagService {

    private TagDAO tagDAO;

    @Autowired
    public void setTagDAO(TagDAO tagDAO){
        this.tagDAO = tagDAO;
    }

    @Override
    public void addTag(Tag tag) {
        tagDAO.addTag(tag);
    }

    @Override
    public Tag getTagById(long id) {
        return tagDAO.getTagById(id);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDAO.updateTag(tag);
    }

    @Override
    public void removeTag(long id) {
        tagDAO.removeTagById(id);
    }
}
