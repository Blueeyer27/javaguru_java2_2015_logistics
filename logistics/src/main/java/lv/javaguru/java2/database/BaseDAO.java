package lv.javaguru.java2.database;

import java.util.List;

public interface BaseDAO<T> {

    void create(T type) throws DBException;

    T getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(T type) throws DBException;

    List<T> getAll() throws DBException;

}
