package com.amsystem.bifaces.systembusiness.dao.impl;

import com.amsystem.bifaces.systembusiness.dao.SystemBusinessPropertiesDao;
import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;
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
 * Title: SystemBusinessPropertiesDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@Repository("systemBPDao")
public class SystemBusinessPropertiesDaoImpl extends AbstractDao<String, SystemBusinessProperties> implements SystemBusinessPropertiesDao{
    private static final Logger log = LogManager.getLogger(SystemBusinessPropertiesDaoImpl.class.getName());

    @Transactional(readOnly = false)
    @Override
    public boolean save(SystemBusinessProperties systemBusinessProperties) {
        return persist(systemBusinessProperties);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateSystemBP(SystemBusinessProperties systemBusinessProperties) {
        return update(systemBusinessProperties);
    }

    @Override
    public boolean delete(String codSystemBP) {
        SystemBusinessProperties systemBP = getByKey(codSystemBP);
        if(systemBP != null)
            return delete(systemBP);

        return false;
    }

    @Transactional(readOnly = false)
    @Override
    public SystemBusinessProperties loadSystemBPByCod(String codSystemBP) {
        SystemBusinessProperties systemBP = getByKey(codSystemBP);
        if(systemBP != null)
            Hibernate.initialize(systemBP.getLanguage());

        return systemBP;
    }

    @Transactional(readOnly = false)
    @Override
    public List<SystemBusinessProperties> loadAllSystemBP() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("codSbp"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<SystemBusinessProperties> systemBusinessPropertiesList = (List<SystemBusinessProperties>) criteria.list();
        return systemBusinessPropertiesList;
    }

    @Transactional(readOnly = false)
    @Override
    public List<SystemBusinessProperties> loadSystemBPByModule(String codModule) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("codSbp"));
        criteria.add(Restrictions.eq("codSm", codModule));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<SystemBusinessProperties> systemBusinessPropertiesList = (List<SystemBusinessProperties>) criteria.list();
        return systemBusinessPropertiesList;
    }
}
