package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.DAO;
import fr.eni.projetsortie.dao.ParticipantDAOImp;
import fr.eni.projetsortie.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class ParticipantServiceImp implements Service<Participant>{
    @Autowired
    private ParticipantDAOImp participantDAO;

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
        this.participantDAO.update(entity);
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
