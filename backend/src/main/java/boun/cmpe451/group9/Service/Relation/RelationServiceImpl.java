package boun.cmpe451.group9.Service.Relation;

import boun.cmpe451.group9.DAO.Relation.RelationDAO;
import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RelationServiceImpl extends BaseServiceImpl<Relation> implements RelationService {

    private RelationDAO relationDAO;

    @Autowired
    public void setRelationDAO(RelationDAO relationDAO){
        this.relationDAO = relationDAO;
    }

    @Override
    public List<Relation> getRelationFromTopicByTopicId(long id) {
        return relationDAO.getAllRelationFromTopicByTopicId(id);
    }

    @Override
    public List<Relation> getAllRelationsToTopicByTopicId(long id) {
        return relationDAO.getAllRelationsToTopicByTopicId(id);
    }

    @Override
    public boolean checkIfRelationExistsByTopicIds(long from, long to) {
        return relationDAO.checkIfRelationExistsByTopicIds(from, to);
    }
}
