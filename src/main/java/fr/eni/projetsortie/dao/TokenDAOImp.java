package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.model.Token;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenDAOImp implements DAO<Token> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int save(Token entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return 1;
    }

    @Override
    public Token get(Object id) {
        Token token;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        String tokenUUID = (String) id;
        token = openSession.getSession().get(Token.class, tokenUUID);

        return token;
    }

    @Override
    public List<Token> list() {
        return null;
    }

    @Override
    public void update(Token entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object token) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().delete(token);
        openSession.closeSession();
    }
}
