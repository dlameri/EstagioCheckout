package com.ideais.spring.dao;

public interface GenericDao<T> {

    void saveOrUpdate(T object);

    void remove(Object object);
    
}