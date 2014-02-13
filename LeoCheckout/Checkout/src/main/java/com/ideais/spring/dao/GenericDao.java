package com.ideais.spring.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> findAll();

    T findById(Long id);

    void saveOrUpdate(T object);

    void remove(Object object);
}