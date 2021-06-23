package dao;

import databace.HibernateUtil;
import entity.ContactEntity;
import org.hibernate.Session;

import java.util.List;

public class ContactDao extends AbstractDao<ContactEntity>{
    @Override
    public ContactEntity get(long id) {
        Session session = HibernateUtil.getSession();
        ContactEntity contactDao = session.get(ContactEntity.class, id);
        HibernateUtil.closeSession(session);
        return contactDao;
    }

    @Override
    public long insert(ContactEntity obj) {
        return super.insert(obj);
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
    public List<ContactEntity> getAll() {
        return super.getAll();
    }
}
