package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Sortie;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SortieDAOImp implements DAO<Sortie> {
    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Object save(Sortie entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Sortie get(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Sortie sortie;
        int idSortie = (int) id;
        sortie = openSession.getSession().get(Sortie.class, idSortie);
        openSession.closeSession();
        return sortie;
    }

    @Override
    public List<Sortie> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Sortie> sorties;
        try{
            sorties = openSession.getSession().createQuery("select s from Sortie s").list();
        } catch(Exception ex){
            this.logger.error(ex.toString());
            sorties = new ArrayList<>();
        }
        openSession.closeSession();
        return sorties;
    }

    @Transactional
    @Override
    public void update(Sortie entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Sortie sortie = this.get(id);
        openSession.getSession().delete(sortie);
        openSession.closeSession();
    }
    /*
    public Sortie searchByParticipant(String pseudo, String email) {
        Sortie sortie;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        CriteriaBuilder builder = openSession.getSession().getCriteriaBuilder();
        CriteriaQuery<Sortie> query = builder.createQuery(Sortie.class);
        Root<Sortie> root = query.from(Sortie.class);
        query.select(root).where(builder.equal(root.get("pseudo"), pseudo)).where(builder.or(builder.equal(root.get("email"), email)));
        Query<Sortie> q = openSession.getSession().createQuery(query);
        sortie = q.getSingleResult();
        return sortie;
    }*/

    public List<Integer> participantsOfSortie(int idSortie) {
        List<Integer> participants;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        try{
            participants = openSession.getSession().createQuery("SELECT p.id " +
                    "FROM Inscription i " +
                    "JOIN Participant p " +
                    "WHERE i.sortie = " + idSortie ).list();
        } catch(Exception ex) {
            System.out.println(ex.toString());
            this.logger.error(ex.toString());
            participants = new ArrayList<>();
        }
       return participants;
    }
}
