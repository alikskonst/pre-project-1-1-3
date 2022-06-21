package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.connection.QuerySingleton;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Util util;

    public UserDaoHibernateImpl() {
        this.util = new Util();
        util.init();
    }

    @Override
    public void createUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery(QuerySingleton.instance(null).getQuery("userCreateTable"));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery(QuerySingleton.instance(null).getQuery("userDropTable"));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery(QuerySingleton.instance(null).getQuery("userCreate"), User.class);
            nativeQuery.setParameter(1, name);
            nativeQuery.setParameter(2, lastName);
            nativeQuery.setParameter(3, age);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<Integer> query = session.createNativeQuery(QuerySingleton.instance(null).getQuery("userRemove"), Integer.class);
            query.setParameter(1, id);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery(QuerySingleton.instance(null).getQuery("userFindAll"), User.class);
            session.getTransaction().commit();
            return nativeQuery.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery(QuerySingleton.instance(null).getQuery("userRemoveAll"));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

//    private void executeQuery(String key) {
//        try {
//            session = util.getSession();
//            session.beginTransaction();
//            session.createNativeQuery(QuerySingleton.instance(null).getQuery(key));
//            session.getTransaction().commit();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//    }

//    private void executeQuery(String key, String... args) {
//        try {
//            session = util.getSession();
//            session.beginTransaction();
//            NativeQuery query = session.createNativeQuery(QuerySingleton.instance(null).getQuery(key));
//            for (int i = 0; i < args.length; i++) {
//                query.setParameter(i + 1, args[i]);
//            }
//            session.getTransaction().commit();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//    }
}
