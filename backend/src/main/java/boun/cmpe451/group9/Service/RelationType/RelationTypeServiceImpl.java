package boun.cmpe451.group9.Service.RelationType;

import boun.cmpe451.group9.DAO.RelationType.RelationTypeDAO;
import boun.cmpe451.group9.Models.DB.RelationType;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RelationTypeServiceImpl extends BaseServiceImpl<RelationType> implements RelationTypeService {
    private RelationTypeDAO relationTypeDAO;

    @Autowired
    public void setRelationTypeDAO(RelationTypeDAO relationTypeDAO){this.relationTypeDAO=relationTypeDAO;}

    @Override
    public List<RelationType> autoComp(String keywords) {
        return relationTypeDAO.autoComp(keywords);
    }
}
