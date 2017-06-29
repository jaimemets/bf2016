package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.CommunicationBridge;

import java.util.List;

/**
 * Title: CommunicationService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */


public interface CommunicationService {

    boolean saveCommunication(CommunicationBridge communicationBridge);

    boolean updateCommunication(CommunicationBridge communicationBridge);

    boolean deleteCommunication(Integer cbId);

    CommunicationBridge findOnlyCommunication(Integer cbId);

    CommunicationBridge findCommunicationAndParameter(Integer cbId);

    List<CommunicationBridge> findAllCommunicationBridge();

    List<CommunicationBridge> findAllCommunicationAndParameterByType(Integer type);

    List<CommunicationBridge> findAllCommunicationAndParameterByCategory(Integer category);

}
