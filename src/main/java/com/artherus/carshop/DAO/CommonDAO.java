package com.artherus.carshop.DAO;


public interface CommonDAO<T> {
    void addEntity(T entity);
    void updateEntity(T entity);
    void deleteEntity(T entity);
}
