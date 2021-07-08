package dao;

import entity.ContactEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
public class ContactDao extends AbstractDao<ContactEntity> {

    @Override
    public Optional<ContactEntity> getById(int id) {
        Session session = getSession();
        ContactEntity contactEntity = session.get(ContactEntity.class, id);
        closeCurrentSession(session);
        return Optional.ofNullable(contactEntity);
    }

    @Override
    public Serializable create(ContactEntity obj) {
        Session session = getSession();
        Serializable id=session.save(obj);
        closeCurrentSession(session);
        return id;
    }

    @Override
    public void delete(ContactEntity obj) {
        Session session = getSession();
        session.delete(obj);
        closeCurrentSession(session);
    }

    @Override
    public void update(ContactEntity obj) {
        Session session = getSession();
        session.update(obj);
        closeCurrentSession(session);
    }

    @Override
    public List<ContactEntity> getAll(int size, int number) {
        int maxResult = size;
        int startCount = number * size;

        String sql = "FROM ContactEntity";
        Session session = getSession();
        List<ContactEntity> list = session.createQuery(sql).setMaxResults(maxResult).setFirstResult(startCount).list();
        closeCurrentSession(session);
        return list;
    }

    public int count() {
        String sql = "FROM ContactEntity";
        Session session = getSession();
        int allsize = session.createQuery(sql).list().size();
        closeCurrentSession(session);
        return allsize;
    }


    @Override
    public void closeCurrentSession(Session session) {
        super.closeCurrentSession(session);
    }
}
