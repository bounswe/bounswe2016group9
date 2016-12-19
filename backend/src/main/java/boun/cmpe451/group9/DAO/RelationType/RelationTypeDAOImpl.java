package boun.cmpe451.group9.DAO.RelationType;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.RelationType;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class RelationTypeDAOImpl extends BaseDAOImpl<RelationType> implements RelationTypeDAO{
    @Override
    public List<RelationType> autoComp(String keywords) {
        String regex = "^"+keywords;
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM relation_type t WHERE t.type REGEXP :regex")
                .addEntity(RelationType.class)
                .setParameter("regex", regex)
                .list();
    }
}
