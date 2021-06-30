package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface InterfaceDao<T> {
    Serializable create (T entity);
    void delete(T entity);
    void update(T entity);
    Optional<T> getById(long id);
    List<T> getAll(int count, int page);


}
