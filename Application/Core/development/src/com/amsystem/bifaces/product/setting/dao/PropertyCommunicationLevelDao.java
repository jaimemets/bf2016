package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevel;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevelPK;

/**
 * Title: PropertyCommunicationLevelDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 24/07/2017.
 */


public interface PropertyCommunicationLevelDao {

    boolean savePropertyToCommunication(PropertyCommunicationLevel propertyCommunicationLevel);

    boolean updatePropertyToCommunication(PropertyCommunicationLevel propertyCommunicationLevel);

    boolean deletePropertyToCommunication(PropertyCommunicationLevelPK pk);

    PropertyCommunicationLevel loadPropertyToCommunication(PropertyCommunicationLevelPK pk);
}
