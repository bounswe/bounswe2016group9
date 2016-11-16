package boun.cmpe451.group9.Service.Tag;

import boun.cmpe451.group9.DAO.Tag.TagDAO;
import boun.cmpe451.group9.Models.DB.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public Tag getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDAO.updateTag(tag);
    }

    @Override
    public void removeTag(long id) {
        tagDAO.removeTagById(id);
    }

    @Override
    public boolean checkIfTagExistsByName(String name) {
        return tagDAO.checkIfTagExistsByName(name);
    }

    @Override
    public boolean checkIfTagExistsById(long id) {
        return tagDAO.checkIfTagExistsById(id);
    }

    @Override
    public List<Tag> getTagsByTopicId(long id) {
        return tagDAO.getTagsByTopicId(id);
    }
}
