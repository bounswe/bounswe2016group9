package boun.cmpe451.group9.Service.Relation;

import boun.cmpe451.group9.DAO.Relation.RelationDAO;
import boun.cmpe451.group9.Models.DB.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelationServiceImpl implements RelationService {

    private RelationDAO relationDAO;

    @Autowired
    public void setRelationDAO(RelationDAO relationDAO){
        this.relationDAO = relationDAO;
    }

    @Override
    public void addRelation(Relation relation) {
        relationDAO.addRelation(relation);
    }

    @Override
    public Relation getRelationById(long id) {
        return relationDAO.getRelationById(id);
    }

    @Override
    public List<Relation> getRelationFromTopicByTopicId(long id) {
        return relationDAO.getallRelationFromTopicByTopicId(id);
    }

    @Override
    public void updateRelation(Relation relation) {
        relationDAO.updateRelation(relation);
    }

    @Override
    public void removeRelation(long id) {
        relationDAO.removeRelationById(id);
    }

    @Override
    public boolean checkIfRelationExistsById(long id) {
        return relationDAO.checkIfRelationExistsById(id);
    }

    @Override
    public boolean checkIfRelationExistsByTopicIds(long from, long to) {
        return relationDAO.checkIfRelationExistsByTopicIds(from, to);
    }
}
