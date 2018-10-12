package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.DAO;
import fr.eni.projetsortie.dao.ParticipantDAOImp;
import fr.eni.projetsortie.dao.SiteDAOImp;
import fr.eni.projetsortie.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class ParticipantServiceImp implements Service<Participant>{
    @Autowired
    private ParticipantDAOImp participantDAO;

    @Autowired
    private SiteDAOImp siteDAOImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Object save(Participant entity) {
        entity.setAdministrateur(false);
        entity.setActif(true);
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
            System.out.println(entity.toString());
            this.cryptPassword(entity);
            System.out.println(entity.toString());
            lastParticipant.setPassword(entity.getPassword());
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

    public Participant findByPseudoOrEmail(String pseudo, String email) {
        return this.participantDAO.searchByPseudoOrEmail(pseudo, email);
    }

    public void cryptPassword(Participant participant) {
        participant.setPassword(this.passwordEncoder.encode(participant.getPassword()));
    }
}
