package dao;

import dto.SearchContactDto;
import entity.ContactEntity;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
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
    public List<ContactEntity> findBy (int size, int number, SearchContactDto searchContactDto){
        Session session = getSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<ContactEntity> criteriaQuery = criteriaBuilder.createQuery(ContactEntity.class);
        Root<ContactEntity> rootContact = criteriaQuery.from(ContactEntity.class);
        List<Predicate> predicateList = buildPredicateList(searchContactDto, criteriaBuilder, rootContact);
        criteriaQuery.select(rootContact).where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
        Query query = session.createQuery(criteriaQuery);
        List<ContactEntity> result = query.getResultList();
        return result;
    }

    @NotNull
    private List<Predicate> buildPredicateList(SearchContactDto searchContactDto, CriteriaBuilder criteriaBuilder, Root<ContactEntity> rootContact) {
        List<Predicate> allPredicates = new ArrayList<>();
        if (searchContactDto.getFirstName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("firstName"), searchContactDto.getFirstName()));
        else if (searchContactDto.getLastName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("lastName"), searchContactDto.getLastName()));
        else if (searchContactDto.getMiddleName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("middleName"), searchContactDto.getMiddleName()));
        else if (searchContactDto.getDataBirthday()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("dataBirthday"), searchContactDto.getDataBirthday()));
        else if (searchContactDto.getGender()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("gender"), searchContactDto.getGender()));
        else if (searchContactDto.getCitizenship()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("citizenship"), searchContactDto.getCitizenship()));
        else if (searchContactDto.getMaritalStatus()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("maritalStatus"), searchContactDto.getMaritalStatus()));
        else if (searchContactDto.getWebsite()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("website"), searchContactDto.getWebsite()));
        else if (searchContactDto.getEmail()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("email"), searchContactDto.getEmail()));
        else if (searchContactDto.getWorkplace()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("workplace"), searchContactDto.getWorkplace()));
        else if (searchContactDto.getCountry()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("country"), searchContactDto.getCountry()));
        else if (searchContactDto.getTown()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("town"), searchContactDto.getTown()));
        else if (searchContactDto.getStreet()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("street"), searchContactDto.getStreet()));
        else if (searchContactDto.getHouse()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("house"), searchContactDto.getHouse()));
        else if (searchContactDto.getFlat()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("flat"), searchContactDto.getFlat()));
        else if (searchContactDto.getAddressIndex()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("addressIndex"), searchContactDto.getAddressIndex()));
        return allPredicates;
    }

    @Override
    public void closeCurrentSession(Session session) {
        super.closeCurrentSession(session);
    }
}
