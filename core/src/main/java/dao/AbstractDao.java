package dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import databace.HibernateUtil;
import dto.SearchContactDto;
import org.hibernate.Session;


public abstract class AbstractDao<T> implements InterfaceDao<T> {

    private final Class<T> entityClass;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    public Serializable create(T obj) {
        return getSession().save(obj);

    }

    public void delete(T obj) {
         getSession().delete(obj);
    }

    public void update(T obj) {
        getSession().update(obj);
    }

    @SuppressWarnings("unchecked")
    abstract public List<T> getAll(int count, int page);

    @Override
    public Optional<T> getById(int id) {
        return (Optional<T>) getSession().get(this.entityClass, id);
    }

    @Override
    abstract public List<T> findBy(int count, int page, SearchContactDto searchContactDto);

    public void closeCurrentSession(Session session){
        HibernateUtil.closeSession(session);
   }
}
