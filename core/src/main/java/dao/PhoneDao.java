package dao;


import dto.SearchContactDto;
import entity.PhoneEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class PhoneDao extends AbstractDao<PhoneEntity> {

    @Override
    public Serializable create(PhoneEntity obj) {
        Session session = null;
        Serializable id = null;
        try {
            session = getSession();
            id = session.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
        return id;
    }

    @Override
    public void delete(PhoneEntity obj) {
        Session session = null;
        try {
            session = getSession();
            session.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
    }

    @Override
    public void update(PhoneEntity obj) {
        Session session = null;
        try {
            session = getSession();
            session.update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
    }

    @Override
    public Optional<PhoneEntity> getById(int id) {
        Session session = null;
        PhoneEntity phoneEntity = null;
        try {
            session = getSession();
            phoneEntity = session.get(PhoneEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
        return Optional.ofNullable(phoneEntity);
    }

    @Override
    public List<PhoneEntity> getAll(int count, int page) {
        return null;
    }

    @Override
    public List<PhoneEntity> getAll() {
        return null;
    }

    public List<PhoneEntity> getByContactId(int id) {
        String sql = "FROM PhoneEntity where contactEntity.id =" + id;
        List<PhoneEntity> list = null;
        Session session = null;
        try {
            session = getSession();
            list = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
        return list;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<PhoneEntity> findBy(int count, int page, SearchContactDto searchContactDto) {
        return null;
    }
}
