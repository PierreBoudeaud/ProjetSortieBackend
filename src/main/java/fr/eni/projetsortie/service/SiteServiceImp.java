package fr.eni.projetsortie.service;

import fr.eni.projetsortie.dao.DAO;
import fr.eni.projetsortie.dao.SiteDAOImp;
import fr.eni.projetsortie.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class SiteServiceImp implements Service<Site>{
    @Autowired
    private SiteDAOImp siteDAO;

    @Transactional
    @Override
    public Object save(Site entity) {
        return this.siteDAO.save(entity);
    }

    @Override
    public Site get(Object id) {
        return this.siteDAO.get(id);
    }

    @Override
    public List<Site> list() {
        return this.siteDAO.list();
    }

    @Transactional
    @Override
    public void update(Site entity) {
        this.siteDAO.update(entity);
    }

    @Override
    public void delete(Object id) {
        this.siteDAO.delete(id);
    }
}
