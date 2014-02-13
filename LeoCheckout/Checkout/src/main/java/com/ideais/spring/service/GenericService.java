package com.ideais.spring.service;

import java.util.List;

public interface GenericService<T> {

    List<T> listObjects();

    T find(Long id);

    void save(T object);

    void remove(T object);
}