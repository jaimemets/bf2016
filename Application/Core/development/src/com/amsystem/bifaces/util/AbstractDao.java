package com.amsystem.bifaces.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Title: AbstractDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    public boolean flag;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataSource dataSource;

    private static final Logger log = LogManager.getLogger(AbstractDao.class.getName());


    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return getSession().get(persistentClass, key);
    }

    public boolean persist(T entity) {
        flag = true;
        try {
            getSession().persist(entity);
        } catch (Exception ex) {
            flag = false;
            log.error("ERROR - JRA : " + ex.getMessage());
        }
        return flag;
    }

    public boolean update(T entity) {
        flag = true;
        try {
            getSession().update(entity);
            getSession().flush();
        }catch (Exception ex) {
            flag = false;
            log.error("ERROR : " + ex.getMessage());

        } finally {
            log.debug("Hibernate haciendo roollback");
            // closeSession();
        }
        return flag;
    }

    public boolean saveOrUpdate(T entity) {
        flag = true;
        try {
            getSession().saveOrUpdate(entity);
        } catch (Exception ex) {
            flag = false;
            log.error("ERROR : " + ex.getMessage());
            return flag;
        }
        return flag;
    }

    public boolean delete(T entity) {
        flag = true;
        try {
            getSession().delete(entity);
        } catch (Exception ex) {
            flag = false;
            log.error("ERROR : " + ex.getMessage());
            return flag;
        }
        return flag;
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    private void closeSession() {
        if (getSession().isOpen()) {
            getSession().close();
        }
    }


}
