package fr.eni.projetsortie.service;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service<T> {
    int save(T entity);
    T get(Object id);
    List<T> list();
    void update(T entity);
    void delete(Object id);
}
