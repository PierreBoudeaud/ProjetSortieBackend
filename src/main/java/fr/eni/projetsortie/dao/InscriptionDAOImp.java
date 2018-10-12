package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.ProjetsortieApplication;
import fr.eni.projetsortie.model.Inscription;
import fr.eni.projetsortie.model.InscriptionId;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InscriptionDAOImp implements DAO<Inscription> {
    private static final Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Object save(Inscription entity){
        InscriptionId inscriptionId = new InscriptionId(entity.getSortie().getId(), entity.getParticipant().getId());
        entity.setId(inscriptionId);
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Inscription get(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Inscription inscription;
        InscriptionId inscriptionId = (InscriptionId) id;
        inscription = openSession.getSession().get(Inscription.class, inscriptionId);
        openSession.closeSession();
        return inscription;
    }

    @Override
    public List<Inscription> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Inscription> inscriptions;
        try{
            inscriptions = openSession.getSession().createQuery("select i from Inscription i INNER JOIN Sortie INNER JOIN Participant").list();
        } catch (Exception ex){
            this.logger.error(ex.toString());
            inscriptions = new ArrayList<>();
        }
        openSession.closeSession();
        return inscriptions;
    }

    @Transactional
    @Override
    public void update(Inscription entity){

    }

    @Override
    public void delete(Object id){
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Inscription inscription = this.get(id);
        openSession.getSession().delete(inscription);
        openSession.closeSession();
    }
}
