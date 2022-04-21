package com.artherus.carshop.DAO;


import java.util.Collection;

public interface CommonDAO<T> {
    void addEntity(T entity);
    void updateEntity(T entity);
    void deleteEntity(T entity);
    Collection<T> getAll();
}
