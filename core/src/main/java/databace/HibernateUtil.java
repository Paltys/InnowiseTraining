package databace;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.jetbrains.annotations.NotNull
    public static Session getSession(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static  void closeSession(Session session){
        if (session !=null){
            try (session) {
                session.getTransaction().commit();
            }
        }
    }
}
