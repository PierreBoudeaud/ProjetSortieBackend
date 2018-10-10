package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.VilleDAOImp;
import fr.eni.projetsortie.dao.SiteDAOImp;
import fr.eni.projetsortie.model.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class VilleServiceImp implements Service<Ville>{
    @Autowired
    private VilleDAOImp villeDAO;

    @Transactional
    @Override
    public Object save(Ville entity) {
        return this.villeDAO.save(entity);
    }

    @Override
    public Ville get(Object id) {
        return this.villeDAO.get(id);
    }

    @Override
    public List<Ville> list() {
        return this.villeDAO.list();
    }

    @Transactional
    @Override
    public void update(Ville entity) {
        this.villeDAO.update(entity);
    }

    @Override
    public void delete(Object id) {
        this.villeDAO.delete(id);
    }
}
