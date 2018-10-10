package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.TokenDAOImp;
import fr.eni.projetsortie.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class TokenServiceImp implements Service<Token>{
    @Autowired
    private TokenDAOImp tokenDAO;

    @Override
    @Transactional
    public Object save(Token entity) {
        return this.tokenDAO.save(entity);
    }

    @Override
    public Token get(Object id) {
        return this.tokenDAO.get(id);
    }

    @Override
    public List<Token> list() {
        return null;
    }

    @Override
    @Transactional
    public void update(Token entity) {
        this.tokenDAO.update(entity);
    }

    @Override
    public void delete(Object id) {
        this.tokenDAO.delete(id);
    }

    public void deleteLastToken() { this.tokenDAO.deleteLastToken(); }
}
