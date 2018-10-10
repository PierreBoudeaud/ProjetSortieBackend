package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.LieuDAOImp;
import fr.eni.projetsortie.dao.VilleDAOImp;
import fr.eni.projetsortie.model.Lieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class LieuServiceImp implements Service<Lieu>{
    @Autowired
    private LieuDAOImp lieuDAO;

    @Autowired
    private VilleDAOImp villeDAOImp;

    @Transactional
    @Override
    public Object save(Lieu entity) {
        return this.lieuDAO.save(entity);
    }

    @Override
    public Lieu get(Object id) {
        return this.lieuDAO.get(id);
    }

    @Override
    public List<Lieu> list() {
        return this.lieuDAO.list();
    }

    public List<Lieu> listByVille(int idVille) {
        return this.lieuDAO.listByVille(idVille);
    }

    @Transactional
    @Override
    public void update(Lieu entity) {
        entity.setVille(this.villeDAOImp.get(entity.getVille().getId()));
        this.lieuDAO.update(entity);
    }

    @Override
    public void delete(Object id) {
        this.lieuDAO.delete(id);
    }
}
