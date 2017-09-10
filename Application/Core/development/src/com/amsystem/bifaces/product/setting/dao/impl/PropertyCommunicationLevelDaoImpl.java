package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.PropertyCommunicationLevelDao;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevel;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevelPK;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: PropertyCommunicationLevelDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 24/07/2017.
 */

@Repository("propertyCommunicationLevelDao")
public class PropertyCommunicationLevelDaoImpl extends AbstractDao<PropertyCommunicationLevelPK, PropertyCommunicationLevel> implements PropertyCommunicationLevelDao {
    private static final Logger log = LogManager.getLogger(PropertyCommunicationLevelDaoImpl.class.getName());


    @Override
    @Transactional
    public boolean savePropertyToCommunication(PropertyCommunicationLevel propertyCommunicationLevel) {
        this.flag = true;
        try {
            persist(propertyCommunicationLevel);
        } catch (Exception ex) {
            flag = false;
            log.error("ERROR - JRA : " + ex.getMessage());
        }
        return this.flag;
    }

    @Override
    @Transactional
    public boolean updatePropertyToCommunication(PropertyCommunicationLevel propertyCommunicationLevel) {
        return update(propertyCommunicationLevel);
    }

    @Override
    @Transactional
    public boolean deletePropertyToCommunication(PropertyCommunicationLevelPK pk) {
        PropertyCommunicationLevel pcl = getByKey(pk);
        if (pcl != null) {
            return delete(pcl);
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public PropertyCommunicationLevel loadPropertyToCommunication(PropertyCommunicationLevelPK pk) {
        return getByKey(pk);
    }
}
