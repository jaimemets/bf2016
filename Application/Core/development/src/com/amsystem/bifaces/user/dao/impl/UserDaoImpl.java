package com.amsystem.bifaces.user.dao.impl;

import com.amsystem.bifaces.user.dao.UserDao;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class.getName());


    @Transactional(readOnly = false)
    public boolean save(User user) {
        return persist(user);
    }

    @Transactional(readOnly = false)
    public boolean deleteBySSO(String sso) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userName", sso));
        User user = (User) criteria.uniqueResult();
        return delete(user);
    }

    @Transactional(readOnly = false)
    public User loadUserById(int id) {
        User user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getProfiles());
        }
        return user;
    }

    @Transactional(readOnly = false)
    public User loadUserBySSO(String sso) {
        log.info("SSO : {}" + sso);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", sso));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getProfiles());
            Hibernate.initialize(user.getMenuItems());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public List<User> loadAllUser() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
        return users;
    }

}

