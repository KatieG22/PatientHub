package com.patienthub.data;

import java.util.List;

import java.util.Optional;

public interface Dao<T> {

    /** Can be empty or populated */
    Optional<T> get(long id);

    List<T> getAll();

    boolean save(T t);

    void update(T t);

    void delete(T t);

    // boolean exists(T t);

}