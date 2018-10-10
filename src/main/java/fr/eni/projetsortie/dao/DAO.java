package fr.eni.projetsortie.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DAO<T> {
    Object save(T entity);
    T get(Object id);
    List<T> list();
    void update(T entity);
    void delete(Object id);
}
