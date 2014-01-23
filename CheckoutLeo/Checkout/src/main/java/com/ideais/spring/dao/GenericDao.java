package com.ideais.spring.dao;

import java.util.List;

public interface GenericDao {

    List<Object> findAll();

    Object findById(Long id);

    void saveOrUpdate(Object object);

    void remove(Object object);
}