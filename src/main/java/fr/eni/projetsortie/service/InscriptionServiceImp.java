package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.InscriptionDAOImp;
import fr.eni.projetsortie.model.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class InscriptionServiceImp implements Service<Inscription>{

    @Autowired
    private InscriptionDAOImp inscriptionDAO;

    @Transactional
    @Override
    public Object save(Inscription entity){return this.inscriptionDAO.save(entity);}

    @Transactional
    @Override
    public Inscription get(Object id) { return this.inscriptionDAO.get(id); }

    @Transactional
    @Override
    public List<Inscription> list() {
        return this.inscriptionDAO.list();
    }

    @Override
    public void update(Inscription entity) {

    }

    @Override
    public void delete(Object id) {
        this.inscriptionDAO.delete(id);
    }
}
