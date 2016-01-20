package com.denm.server.dao;

import com.denm.server.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.logging.Logger;

/**
 * Created by DENM on 20.01.2016.
 */
public class UserDao {
    private final Logger logger = Logger.getGlobal();
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }

    public User get(long id) throws HibernateException {
        logger.info("UserDao get = " + id );
        User user = (User) session.get(User.class, id);
        return user != null ? user : null;
    }

    public long getUserId(String name) throws HibernateException {
        logger.info("UserDao getUserId = " + name );
        Criteria criteria = session.createCriteria(User.class);
        User user = ((User) criteria.add(Restrictions.eq("name", name)).uniqueResult());
        return user != null ? user.getId() : -1;
    }

    public long insertUser(String name, String password) throws HibernateException {
        logger.info("UserDao insertUser = " + name + ", " + password );
        return (Long) session.save(new User(name, password));
    }
}
