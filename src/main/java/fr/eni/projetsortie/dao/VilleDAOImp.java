package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.model.Site;
import fr.eni.projetsortie.model.Ville;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VilleDAOImp implements DAO<Ville> {
    @Autowired
    private SessionFactory sessionFactory;



    @Transactional
    @Override
    public int save(Ville entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Ville get(Object id) {
        Ville ville;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        int idVille = (int) id;
        ville = openSession.getSession().get(Ville.class, idVille);

        return ville;
    }

    @Override
    public List<Ville> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Ville> villes;
        try{
            villes = openSession.getSession().createQuery("select v from Ville v").list();
        } catch(Exception ex){
            villes = new ArrayList<>();
        }
        openSession.closeSession();
        return villes;
    }

    @Transactional
    @Override
    public void update(Ville entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Ville ville = this.get(id);
        openSession.getSession().delete(ville);
        openSession.closeSession();
    }
}
