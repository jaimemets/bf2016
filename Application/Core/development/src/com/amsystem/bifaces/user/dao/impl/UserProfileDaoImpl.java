package com.amsystem.bifaces.user.dao.impl;

import com.amsystem.bifaces.user.dao.UserProfileDao;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserProfileDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Repository("userProfileDao")
@Transactional
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

    public UserProfile loadProfileById(int id) {
        return getByKey(id);
    }


    public UserProfile loadProfileByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }


    public List<UserProfile> loadAllProfile() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>)crit.list();
    }

    @Override
    public List<UserProfile> loadProfileByIds(List<Integer> idList) {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("type"));
        criteria.add(Restrictions.in("roleId", idList));
        return criteria.list();
    }
}

