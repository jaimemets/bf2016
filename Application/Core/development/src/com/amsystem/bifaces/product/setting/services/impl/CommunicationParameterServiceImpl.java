package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.PropertyCommunicationLevelDao;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevel;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevelPK;
import com.amsystem.bifaces.product.setting.services.CommunicationParameterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: CommunicationParameterServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/08/2017.
 */

@Service("communicationParameterService")
public class CommunicationParameterServiceImpl implements CommunicationParameterService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private PropertyCommunicationLevelDao propertyCommunicationLevelDao;

    @Override
    public PropertyCommunicationLevel findCommunicationParameterByPK(PropertyCommunicationLevelPK pk) {
        return propertyCommunicationLevelDao.loadPropertyToCommunication(pk);
    }
}
