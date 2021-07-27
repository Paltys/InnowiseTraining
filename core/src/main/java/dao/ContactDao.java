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
import java.time.LocalDate;
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

    public List<ContactEntity> getAll() {
        String sql = "FROM ContactEntity";
        Session session = getSession();
        List<ContactEntity> list = session.createQuery(sql).list();
        closeCurrentSession(session);
        return list;
    }

    public List<ContactEntity> getBirthdayContact(LocalDate today){
        String sql = "FROM ContactEntity WHERE dataBirthday="+today;
        Session session = getSession();
        List<ContactEntity> list = session.createQuery(sql).list();
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
        Query query = session.createQuery(criteriaQuery).setMaxResults(size).setFirstResult(number*size);
        List<ContactEntity> result = query.getResultList();
        return result;
    }

    @NotNull
    private List<Predicate> buildPredicateList(SearchContactDto searchContactDto, CriteriaBuilder criteriaBuilder, Root<ContactEntity> rootContact) {
        List<Predicate> allPredicates = new ArrayList<>();
        if (searchContactDto.getSearchFirstName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("firstName"), searchContactDto.getSearchFirstName()));
        else if (searchContactDto.getSearchLastName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("lastName"), searchContactDto.getSearchLastName()));
        else if (searchContactDto.getSearchMiddleName()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("middleName"), searchContactDto.getSearchMiddleName()));
        else if (searchContactDto.getSearchDataBirthday()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("dataBirthday"), searchContactDto.getSearchDataBirthday()));
        else if (searchContactDto.getSearchGender()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("gender"), searchContactDto.getSearchGender()));
        else if (searchContactDto.getSearchCitizenship()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("citizenship"), searchContactDto.getSearchCitizenship()));
        else if (searchContactDto.getSearchMaritalStatus()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("maritalStatus"), searchContactDto.getSearchMaritalStatus()));
        else if (searchContactDto.getSearchWebsite()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("website"), searchContactDto.getSearchWebsite()));
        else if (searchContactDto.getSearchEmail()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("email"), searchContactDto.getSearchEmail()));
        else if (searchContactDto.getSearchWorkplace()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("workplace"), searchContactDto.getSearchWorkplace()));
        else if (searchContactDto.getSearchCountry()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("country"), searchContactDto.getSearchCountry()));
        else if (searchContactDto.getSearchTown()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("town"), searchContactDto.getSearchTown()));
        else if (searchContactDto.getSearchStreet()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("street"), searchContactDto.getSearchStreet()));
        else if (searchContactDto.getSearchHouse()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("house"), searchContactDto.getSearchHouse()));
        else if (searchContactDto.getSearchFlat()!=null)
            allPredicates.add(criteriaBuilder.like(rootContact.get("flat"), searchContactDto.getSearchFlat()));
        else if (searchContactDto.getSearchAddressIndex()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("addressIndex"), searchContactDto.getSearchAddressIndex()));
        else if (searchContactDto.getSearchAvatarUrl()!=null)
            allPredicates.add(criteriaBuilder.equal(rootContact.get("avatarUrl"), searchContactDto.getSearchAvatarUrl()));
        return allPredicates;
    }

    @Override
    public void closeCurrentSession(Session session) {
        super.closeCurrentSession(session);
    }
}
