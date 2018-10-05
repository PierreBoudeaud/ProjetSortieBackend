package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.DAO;
import fr.eni.projetsortie.dao.ParticipantDAOImp;
import fr.eni.projetsortie.dao.SiteDAOImp;
import fr.eni.projetsortie.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class ParticipantServiceImp implements Service<Participant>{
    @Autowired
    private ParticipantDAOImp participantDAO;

    @Autowired
    private SiteDAOImp siteDAOImp;

    @Transactional
    @Override
    public int save(Participant entity) {
        return this.participantDAO.save(entity);
    }

    @Override
    public Participant get(Object id) {
        return this.participantDAO.get(id);
    }

    @Override
    public List<Participant> list() {
        return this.participantDAO.list();
    }

    @Transactional
    @Override
    public void update(Participant entity) {
        Participant lastParticipant = this.get(entity.getId());
        entity.setSite(this.siteDAOImp.get(entity.getSite().getId()));
        lastParticipant.setSite(entity.getSite());
        if(entity.getPassword().trim().length() > 1){
            lastParticipant.setPassword(entity.getPassword());
            this.cryptPassword(lastParticipant);
        }
        lastParticipant.setPseudo(entity.getPseudo());
        lastParticipant.setMail(entity.getMail());
        lastParticipant.setNom(entity.getNom());
        lastParticipant.setPrenom(entity.getPrenom());
        lastParticipant.setTelephone(entity.getTelephone());
        lastParticipant.setActif(entity.isActif());
        lastParticipant.setAdministrateur(entity.isAdministrateur());
        lastParticipant.setSite(entity.getSite());
        this.participantDAO.update(lastParticipant);
    }

    @Override
    public void delete(Object id) {
        this.participantDAO.delete(id);
    }

    public void cryptPassword(Participant participant) {
        participant.setPassword(participant.getPassword().toUpperCase());
        participant.setSalt("sj<koskjsgigknkgi");
    }
}
