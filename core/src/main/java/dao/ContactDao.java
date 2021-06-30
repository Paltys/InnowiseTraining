package dao;

import entity.ContactEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
public class ContactDao extends AbstractDao<ContactEntity>{

    @Override
    public Optional<ContactEntity> getById(long id) {
        ContactEntity contactEntity= getSession().get(ContactEntity.class, id);
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
//    public List<ContactEntity> getAll() {
//        List<ContactEntity> list =getSession().createQuery("FROM ContactEntity").list();
//        //Query query = entityManager.createQuery("SELECT e FROM User e");
//        return list;
//    }

    public List<ContactEntity> getAll(int count, int page) {

        int maxResult = count;
        int startCount = page*count;


       // String sql = "SELECT * FROM contact LIMIT "+startCount+ "," + maxResult;
        String sql = "FROM ContactEntity";
        List<ContactEntity> list =getSession().createQuery(sql).setMaxResults(maxResult).setFirstResult(startCount).list();

//        PaginationResult<ContactEntity> result = new PaginationResult<ContactEntity>(query, page, maxResult, maxNavigationResult);
       return list;
    }

    @Override
    public void closeCurrentSession() {
        super.closeCurrentSession();
    }
}
