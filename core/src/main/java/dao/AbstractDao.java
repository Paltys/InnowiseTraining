package dao;
import java.io.Serializable;
import java.util.List;

import databace.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public abstract class AbstractDao<T> {
    abstract public T get(long id);

    public long insert(T obj) {
        Session session = HibernateUtil.getSession();
        Serializable sss = session.save(obj);
        long newId = (long)sss;
        HibernateUtil.closeSession(session);
        return newId;
    }

    public void delete(T obj) {
        Session session = HibernateUtil.getSession();
        session.delete(obj);
        HibernateUtil.closeSession(session);
    }

    public void update(T obj) {
        Session session = HibernateUtil.getSession();
        session.update(obj);
        HibernateUtil.closeSession(session);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll(){
        List<T> list;
        Session session = HibernateUtil.getSession();
        Query<T> query = session.createQuery("FROM ContactEntity ");
        list = query.list();
        HibernateUtil.closeSession(session);
        return list;
    }
}
