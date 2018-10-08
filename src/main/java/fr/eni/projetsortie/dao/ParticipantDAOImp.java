package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Participant;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipantDAOImp implements DAO<Participant> {
    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public int save(Participant entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Participant get(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Participant participant;
        int idParticipant = (int) id;
        participant = openSession.getSession().get(Participant.class, idParticipant);
        openSession.closeSession();
        return participant;
    }

    @Override
    public List<Participant> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Participant> participants;
        try{
            participants = openSession.getSession().createQuery("select p from Participant p").list();
        } catch(Exception ex){
            this.logger.error(ex.toString());
            participants = new ArrayList<>();
        }
        openSession.closeSession();
        return participants;
    }

    @Transactional
    @Override
    public void update(Participant entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Participant participant = this.get(id);
        openSession.getSession().delete(participant);
        openSession.closeSession();
    }

    public Participant searchByPseudoOrEmail(String pseudo, String email) {
        Participant participant = null;
        System.out.println(pseudo);
        System.out.println(email);
        try {
            OpenSession openSession = new OpenSession(this.sessionFactory);
            List<Participant> participants = openSession.getSession().createCriteria(Participant.class)
                    .add(Restrictions.or(
                            Restrictions.eq("pseudo", pseudo),
                            Restrictions.eq("mail", email)
                    )).list();
            participant = participants.get(0);
        } catch(Exception ex) {
            this.logger.error(ex.getMessage());
        }
        return participant;
    }
}
