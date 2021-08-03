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
        Session session = getSession();
        Serializable id = session.save(obj);
        closeCurrentSession(session);
        return id;
    }

    @Override
    public void delete(PhoneEntity obj) {
        Session session = getSession();
        session.delete(obj);
        closeCurrentSession(session);
    }

    @Override
    public void update(PhoneEntity obj) {
        Session session = getSession();
        session.update(obj);
        closeCurrentSession(session);
    }


    @Override
    public Optional<PhoneEntity> getById(int id) {
        Session session = getSession();
        PhoneEntity phoneEntity = session.get(PhoneEntity.class, id);
        closeCurrentSession(session);
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
        Session session = getSession();
        List<PhoneEntity> list = session.createQuery(sql).list();
        closeCurrentSession(session);
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
