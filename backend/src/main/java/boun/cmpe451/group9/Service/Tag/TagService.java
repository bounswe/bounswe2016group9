package boun.cmpe451.group9.Service.Tag;


import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

public interface TagService extends BaseService<Tag> {

    Tag getTagByName(String name);

    boolean checkIfTagExistsByName(String name);

    List<Tag> getTagsByTopicId(long id);
}
