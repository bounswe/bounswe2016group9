package boun.cmpe451.group9.DAO.SemanticTag;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SemanticTagDAOImpl extends BaseDAOImpl<SemanticTag> implements SemanticTagDAO {
    @Override
    public SemanticTag getSTagByName(String name) {
        return (SemanticTag) this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM semantic_tag t WHERE t.type = :name")
                .addEntity(SemanticTag.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public boolean checkExistenceByName(String name) {
        return getSTagByName(name) != null;
    }
}
