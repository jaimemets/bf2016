package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.CommunicationBridge;

import java.util.List;

/**
 * Title: CommunicationServiceDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */


public interface CommunicationBridgeDao {

    boolean save(CommunicationBridge communicationBridge);

    boolean updateCommunicationBridge(CommunicationBridge communicationBridge);

    boolean deleteCommunicationBridge(Integer csId);

    CommunicationBridge loadOnlyCommunication(Integer csId);

    CommunicationBridge loadCommunicationAndParameter(Integer cbId);

    List<CommunicationBridge> loadAllCommunicationBridge();

    List<CommunicationBridge> loadAllCommunicationAndParameterByType(Integer type);

    List<CommunicationBridge> loadAllCommunicationAndParameterByCategory(Integer category);
}
