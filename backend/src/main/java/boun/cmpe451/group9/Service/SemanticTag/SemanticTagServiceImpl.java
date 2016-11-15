package boun.cmpe451.group9.Service.SemanticTag;

import boun.cmpe451.group9.DAO.SemanticTag.SemanticTagDAO;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SemanticTagServiceImpl implements SemanticTagService {

    private SemanticTagDAO semanticTagDAO;

    @Autowired
    public void setSemanticTagDAO(SemanticTagDAO semanticTagDAO){
        this.semanticTagDAO = semanticTagDAO;
    }

    @Override
    public void addSTag(SemanticTag semanticTag) {
        semanticTagDAO.addSTag(semanticTag);
    }

    @Override
    public void addSTagWithSave(SemanticTag semanticTag) {
        semanticTagDAO.addSTagWithSave(semanticTag);
    }

    @Override
    public SemanticTag getSTagById(long id) {
        return semanticTagDAO.getSTagById(id);
    }

    @Override
    public SemanticTag getSTagByName(String name) {
        return semanticTagDAO.getSTagByName(name);
    }

    @Override
    public void updateSTag(SemanticTag semanticTag) {
        semanticTagDAO.updateSTag(semanticTag);
    }

    @Override
    public void removeSTagById(long id) {
        semanticTagDAO.removeSTagById(id);
    }

    @Override
    public boolean checkIfSTagExistsByName(String name) {
        return semanticTagDAO.checkIfSTagExistsByName(name);
    }
}
