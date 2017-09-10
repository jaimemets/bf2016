package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevel;
import com.amsystem.bifaces.product.setting.model.PropertyCommunicationLevelPK;

/**
 * Title: CommunicationParameterService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/08/2017.
 */


public interface CommunicationParameterService {

    PropertyCommunicationLevel findCommunicationParameterByPK(PropertyCommunicationLevelPK pk);
}
