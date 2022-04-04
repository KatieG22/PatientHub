package com.patienthub.data;

import java.util.List;

import java.util.Optional;

/**
 * Abstract representaion of what a DAO
 * should contain
 * class gurantees that a basic DAO can
 * perform basic CRUD operations like
 * 
 * @implNote
 *           Create -- > save
 *           Retrive --> get and getAll where get retursn
 *           as single object of type T and getAll returns
 *           a List of objects type T.
 *           Update --> update
 *           delete -- > delete
 */
public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    boolean save(T t);

    void update(T t);

    void delete(T t);

}