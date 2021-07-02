package dao;

import entity.ContactEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
public class ContactDao extends AbstractDao<ContactEntity> {

    @Override
    public Optional<ContactEntity> getById(int id) {
        ContactEntity contactEntity = getSession().get(ContactEntity.class, id);
        return Optional.ofNullable(contactEntity);
    }

    @Override
    public Serializable create(ContactEntity obj) {
        return super.create(obj);
    }

    @Override
    public void delete(ContactEntity obj) {
        super.delete(obj);
    }

    @Override
    public void update(ContactEntity obj) {
        super.update(obj);
    }

    @Override
    public List<ContactEntity> getAll(int size, int number) {
        int maxResult = size;
        int startCount = number * size;

        String sql = "FROM ContactEntity";
        List<ContactEntity> list = getSession().createQuery(sql).setMaxResults(maxResult).setFirstResult(startCount).list();
        return list;
    }

    public int count() {
        String sql = "FROM ContactEntity";

        int allsize = getSession().createQuery(sql).list().size();
        return allsize;
    }


    @Override
    public void closeCurrentSession() {
        super.closeCurrentSession();
    }
}
