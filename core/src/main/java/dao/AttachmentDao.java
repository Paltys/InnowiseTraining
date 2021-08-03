package dao;

import dto.SearchContactDto;
import entity.AttachmentEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class AttachmentDao extends AbstractDao<AttachmentEntity> {

    @Override
    public Serializable create(AttachmentEntity obj) {
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
    public void delete(AttachmentEntity obj) {
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
    public void update(AttachmentEntity obj) {
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
    public Optional<AttachmentEntity> getById(int id) {
        Session session = null;
        AttachmentEntity attachmentEntity = null;
        try {
            session = getSession();
            attachmentEntity = session.get(AttachmentEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCurrentSession(session);
        }
        return Optional.ofNullable(attachmentEntity);
    }

    @Override
    public List<AttachmentEntity> getAll() {
        return null;
    }

    @Override
    public List<AttachmentEntity> getAll(int count, int page) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<AttachmentEntity> findBy(int count, int page, SearchContactDto searchContactDto) {
        return null;
    }

    @Override
    public List<AttachmentEntity> getByContactId(int id) {
        String contactId = "contact_" + id;
        String sql = "FROM AttachmentEntity A  WHERE A.type=" + "'" + contactId + "'";
        Session session = null;
        List<AttachmentEntity> list = null;
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
}
