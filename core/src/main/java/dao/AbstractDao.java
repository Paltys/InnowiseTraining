package dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import databace.HibernateUtil;
import org.hibernate.Session;


public abstract class AbstractDao<T> implements InterfaceDao<T> {

    private final Class<T> entityClass;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
//    @Autowired
//    private SessionFactory sessionFactory;

    protected Session getSession() {
        //return this.sessionFactory.getCurrentSession();
        return HibernateUtil.getSession();
    }

    public Serializable create(T obj) {
        return getSession().save(obj);
    }

    public void delete(T obj) {
         getSession().delete(obj);
    }

    public void update(T obj) {
        getSession().update(obj);
    }

    @SuppressWarnings("unchecked")
    abstract public List<T> getAll(int count, int page);
    //{
//        List<T> list;
//        Session session = HibernateUtil.getSession();
//        Query<T> query = session.createQuery("FROM ContactEntity ");
//        list = query.list();
//        HibernateUtil.closeSession(session);
        //return (List<T>) getSession().getCriteriaBuilder().createQuery();
        //return getSession().createCriteria(this.entytiClass).list();
  //  }

    @Override
    public Optional<T> getById(int id) {
        return (Optional<T>) getSession().get(this.entityClass, id);
    }

   public void closeCurrentSession(){
        HibernateUtil.closeSession(getSession());
   }
}
