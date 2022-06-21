package jm.task.core.jdbc.connection;

import jm.task.core.jdbc.conf.ConfProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Map;

public class SessionSingleton {

    public Session getCurrentSession() {
        Map<String, String> connectionMap = new ConfProvider().connection();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(connectionMap).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory.getCurrentSession();
    }
}
