package jm.task.core.jdbc.connection;

import org.hibernate.Session;

public class SessionSingleton {

    private static SessionSingleton instance;
    private final Session session;

    private SessionSingleton(Session session) {
        this.session = session;
    }

    public static SessionSingleton instance(Session session) {
        if (instance == null) {
            instance = new SessionSingleton(null);
        }
        if (session != null) {
            instance = new SessionSingleton(session);
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }
}
