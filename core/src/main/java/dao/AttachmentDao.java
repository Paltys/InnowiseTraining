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
        Session session = getSession();
        Serializable id = session.save(obj);
        closeCurrentSession(session);
        return id;
    }

    @Override
    public void delete(AttachmentEntity obj) {
        Session session = getSession();
        session.delete(obj);
        closeCurrentSession(session);
    }

    @Override
    public void update(AttachmentEntity obj) {
        Session session = getSession();
        session.update(obj);
        closeCurrentSession(session);
    }


    @Override
    public Optional<AttachmentEntity> getById(int id) {
        Session session = getSession();
        AttachmentEntity attachmentEntity = session.get(AttachmentEntity.class, id);
        closeCurrentSession(session);
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
}
