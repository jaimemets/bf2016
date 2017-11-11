package com.amsystem.bifaces.user.dao.impl;

import com.amsystem.bifaces.user.dao.ProfileDao;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
public class ProfileDaoImpl extends AbstractDao<Integer, Profile> implements ProfileDao {

    @Override
    @Transactional(readOnly = false)
    public boolean saveRecord(Profile profile) {
        return persist(profile);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateRecord(Profile profile) {
        return update(profile);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteById(Integer profileId) {
        Profile profile = getByKey(profileId);
        if (profile != null) {
            return delete(profile);
        }
        return false;

    }

    @Override
    @Transactional(readOnly = true)
    public Profile loadById(Integer profileId) {
        return getByKey(profileId);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile loadFullById(Integer profileId) {
        Profile profile = getByKey(profileId);
        if (profile != null) {
            Hibernate.initialize(profile.getUserSet());
        }
        return profile;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> loadAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        return (List<Profile>) criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> loadByIdList(List<Integer> profileId) {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        criteria.add(Restrictions.in("idProfile", profileId));
        return criteria.list();
    }
}

