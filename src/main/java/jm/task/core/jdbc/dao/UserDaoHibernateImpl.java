package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.connection.QuerySingleton;
import jm.task.core.jdbc.connection.SessionSingleton;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Session session;

    public UserDaoHibernateImpl() {
        new Util().init();
        this.session = SessionSingleton.instance(null).getSession();
    }

    @Override
    public void createUsersTable() {
        executeQuery("userCreateTable");
    }

    @Override
    public void dropUsersTable() {
        executeQuery("userDropTable");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        executeQuery("userCreate", name, lastName, String.valueOf(age));
//        try {
//            session.beginTransaction();
//            session.createQuery(QuerySingleton.instance(null).getQuery("userCreate"));
//            session.getTransaction().commit();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void removeUserById(long id) {
        executeQuery("userRemove", String.valueOf(id));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            //session.beginTransaction();
            Query<User> query = session.createQuery(QuerySingleton.instance(null).getQuery("userFindAll"));
            return query.list();
            //session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        executeQuery("userRemoveAll");
    }

    private void executeQuery(String key) {
        try {
            session.beginTransaction();
            session.createQuery(QuerySingleton.instance(null).getQuery(key));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    private void executeQuery(String key, String... args) {
        try {
            session.beginTransaction();
            Query query = session.createQuery(QuerySingleton.instance(null).getQuery(key));
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i + 1, args[i]);
            }
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

//    private List<User> executeQueryToList(String key) {
//
//    }
}
