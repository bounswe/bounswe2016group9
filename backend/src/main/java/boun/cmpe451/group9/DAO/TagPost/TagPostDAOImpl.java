package boun.cmpe451.group9.DAO.TagPost;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.TagPost;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class TagPostDAOImpl extends BaseDAOImpl<TagPost> implements TagPostDAO  {
    @Override
    public Collection<? extends Post> searchPostByTag(String[] keywords) {
        String sqlText = "'" + keywords[0];

        for(int i = 1; i < keywords.length; i++){
            sqlText += "|" + keywords[i];
        }
        sqlText += "'";

        return (List<Post>) this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM tag_post tp " +
                        "JOIN tag t ON tp.tag_id = t.id " +
                        "JOIN post p ON tp.post_id = p.id WHERE t.name REGEXP :text")
                .addEntity(Post.class)
                .setParameter("text", sqlText)
                .list();
    }
}
