package dao;

import entity.ContactEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface InterfaceDao<T> {
    Serializable create (T entity);
    void delete(T entity);
    void update(T entity);
    Optional<T> getById(int id);
    List<T> getAll(int count, int page);
    int count();


}
