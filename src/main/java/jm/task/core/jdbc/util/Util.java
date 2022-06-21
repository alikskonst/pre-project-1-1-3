package jm.task.core.jdbc.util;

import jm.task.core.jdbc.conf.ConfProvider;
import jm.task.core.jdbc.connection.QuerySingleton;
import jm.task.core.jdbc.connection.SettingsSingleton;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Map;

public class Util {

    public void init() {
        ConfProvider confProvider = new ConfProvider();
        SettingsSingleton.instance(confProvider.connection());
        QuerySingleton.instance(confProvider.queries());
    }

    public SessionFactory getSessionFactory() {
        return createSessionFactory(SettingsSingleton.instance(null).getSettings());
    }

    private SessionFactory createSessionFactory(Map<String, String> settings) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSources.buildMetadata();
        //SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
        //Session session = sessionFactory.openSession();
        //return sessionFactory.getCurrentSession();
    }
}
