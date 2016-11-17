package boun.cmpe451.group9.Service.SemanticTag;

import boun.cmpe451.group9.DAO.SemanticTag.SemanticTagDAO;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SemanticTagServiceImpl extends BaseServiceImpl<SemanticTag> implements SemanticTagService {

    private SemanticTagDAO semanticTagDAO;

    @Autowired
    public void setSemanticTagDAO(SemanticTagDAO semanticTagDAO){
        this.semanticTagDAO = semanticTagDAO;
    }

    @Override
    public SemanticTag getSTagByName(String name) {
        return semanticTagDAO.getSTagByName(name);
    }

    @Override
    public boolean checkExistenceByName(String name) {
        return semanticTagDAO.checkExistenceByName(name);
    }
}
