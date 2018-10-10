package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.model.Site;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SiteDAOImp implements DAO<Site> {
    @Autowired
    private SessionFactory sessionFactory;



    @Transactional
    @Override
    public Object save(Site entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Site get(Object id) {
        Site site;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        int idSite = (int) id;
        site = openSession.getSession().get(Site.class, idSite);

        return site;
    }

    @Override
    public List<Site> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Site> sites;
        try{
            sites = openSession.getSession().createQuery("select s from Site s").list();
        } catch(Exception ex){
            sites = new ArrayList<>();
        }
        openSession.closeSession();
        return sites;
    }

    @Transactional
    @Override
    public void update(Site entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Site site = this.get(id);
        openSession.getSession().delete(site);
        openSession.closeSession();
    }
}
