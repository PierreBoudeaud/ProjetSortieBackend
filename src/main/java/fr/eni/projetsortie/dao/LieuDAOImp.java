package fr.eni.projetsortie.dao;

import fr.eni.projetsortie.model.Lieu;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LieuDAOImp implements DAO<Lieu> {
    @Autowired
    private SessionFactory sessionFactory;



    @Transactional
    @Override
    public int save(Lieu entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().save(entity);
        openSession.closeSession();
        return entity.getId();
    }

    @Override
    public Lieu get(Object id) {
        Lieu lieu;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        int idLieu = (int) id;
        lieu = openSession.getSession().get(Lieu.class, idLieu);

        return lieu;
    }

    public List<Lieu> listByVille(int idVille) {
        List<Lieu> lieux;
        OpenSession openSession = new OpenSession(this.sessionFactory);
        CriteriaBuilder builder = openSession.getSession().getCriteriaBuilder();
        CriteriaQuery<Lieu> query = builder.createQuery(Lieu.class);
        Root<Lieu> root = query.from(Lieu.class);
        query.select(root).where(builder.equal(root.get("ville"), idVille));
        Query<Lieu> q = openSession.getSession().createQuery(query);
        lieux = q.list();
        return lieux;
    }

    @Override
    public List<Lieu> list() {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        List<Lieu> lieus;
        try{
            lieus = openSession.getSession().createQuery("select v from Lieu v").list();
        } catch(Exception ex){
            lieus = new ArrayList<>();
        }
        openSession.closeSession();
        return lieus;
    }

    @Transactional
    @Override
    public void update(Lieu entity) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        openSession.getSession().update(entity);
        openSession.closeSession();
    }

    @Override
    public void delete(Object id) {
        OpenSession openSession = new OpenSession(this.sessionFactory);
        Lieu lieu = this.get(id);
        openSession.getSession().delete(lieu);
        openSession.closeSession();
    }
}
