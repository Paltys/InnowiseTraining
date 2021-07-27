package dao;
import dto.SearchContactDto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Serializable create (T entity);
    void delete(T entity);
    void update(T entity);
    Optional<T> getById(int id);
    List<T> getAll(int count, int page);
    int count();
    List<T> findBy(int count, int page, SearchContactDto searchContactDto);

    List<T> getAll();
}
