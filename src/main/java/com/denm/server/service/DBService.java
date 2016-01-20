package com.denm.server.service;

import com.denm.server.dao.UserDao;
import com.denm.server.exception.DBException;
import com.denm.server.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";
    private static volatile DBService instance = null;

    private final SessionFactory sessionFactory;

    private DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    public static DBService getInstance () {
        if (instance == null) {
            synchronized (AuthenticationServiceImpl.class) {
                if (instance == null) {
                    instance = new DBService();
                }
            }
        }
        return instance;
    }


    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }


    public User getUser(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UserDao dao = new UserDao(session);
            User user = dao.get(id);
            session.close();
            return user;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long getUserId(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UserDao dao = new UserDao(session);
            long id = dao.getUserId(name);
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String name, String password) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDao dao = new UserDao(session);
            long id = dao.insertUser(name, password);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}