package fr.eni.projetsortie.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OpenSession {
    Session session;
    boolean open;

    public OpenSession(SessionFactory sessionFactory) {
        try{
            this.session = sessionFactory.getCurrentSession();
            this.open = false;
        } catch(HibernateException ex) {
            this.session = sessionFactory.openSession();
            this.open = true;
        }
    }

    public Session getSession() {
        return session;
    }

    public boolean isOpen() {
        return open;
    }

    public void closeSession() {
        if(this.isOpen()){
            this.session.close();
        }
    }
}
