package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.LieuDAOImp;
import fr.eni.projetsortie.dao.ParticipantDAOImp;
import fr.eni.projetsortie.dao.SortieDAOImp;
import fr.eni.projetsortie.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class SortieServiceImp implements Service<Sortie>{
    @Autowired
    private SortieDAOImp sortieDAO;

    @Autowired
    private LieuDAOImp lieuDAO;

    @Autowired
    private ParticipantDAOImp participantDAO;

    @Transactional
    @Override
    public int save(Sortie entity) {
        return this.sortieDAO.save(entity);
    }

    @Override
    public Sortie get(Object id) {
        return this.sortieDAO.get(id);
    }

    @Override
    public List<Sortie> list() {
        return this.sortieDAO.list();
    }

    @Override
    public void update(Sortie entity) {
        Sortie lastSortie = this.get(entity.getId());
        entity.setLieu(this.lieuDAO.get(entity.getLieu().getId()));
        entity.setOrganisateur(this.participantDAO.get(entity.getOrganisateur().getId()));
        lastSortie.setOrganisateur(entity.getOrganisateur());
        lastSortie.setLieu(entity.getLieu());



        this.sortieDAO.update(entity);
    }

    @Override
    public void delete(Object id) {
        this.sortieDAO.delete(id);
    }
}
