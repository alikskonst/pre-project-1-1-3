package jm.task.core.jdbc.util;

import jm.task.core.jdbc.conf.ConfProvider;
import jm.task.core.jdbc.connection.QuerySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Map;

public class Util {

    public void init() {
        ConfProvider confProvider = new ConfProvider();
        //SessionSingleton.instance(createSession(confProvider.connection()));
        QuerySingleton.instance(confProvider.queries());
    }

    public Session getSession() {
        ConfProvider confProvider = new ConfProvider();
        return createSession(confProvider.connection());
    }

    private Session createSession(Map<String, String> connectionMap) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(connectionMap).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        //Session session = sessionFactory.openSession();
        return sessionFactory.getCurrentSession();
    }
}
