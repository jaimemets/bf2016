package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.CommunicationBridgeDao;
import com.amsystem.bifaces.product.setting.model.CommunicationBridge;
import com.amsystem.bifaces.product.setting.services.CommunicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: CommunicationServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */

@Service("communicationService")
public class CommunicationServiceImpl implements CommunicationService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private CommunicationBridgeDao communicationBridgeDao;

    @Override
    public boolean saveCommunication(CommunicationBridge communicationBridge) {
        return communicationBridgeDao.save(communicationBridge);
    }

    @Override
    public boolean updateCommunication(CommunicationBridge communicationBridge) {
        return communicationBridgeDao.updateCommunicationBridge(communicationBridge);
    }

    @Override
    public boolean deleteCommunication(Integer cbId) {
        return communicationBridgeDao.deleteCommunicationBridge(cbId);
    }

    @Override
    public CommunicationBridge findOnlyCommunication(Integer cbId) {
        return communicationBridgeDao.loadOnlyCommunication(cbId);
    }

    @Override
    public CommunicationBridge findCommunicationAndParameter(Integer cbId) {
        return communicationBridgeDao.loadCommunicationAndParameter(cbId);
    }

    @Override
    public List<CommunicationBridge> findAllCommunicationBridge() {
        return communicationBridgeDao.loadAllCommunicationBridge();
    }

    @Override
    public List<CommunicationBridge> findAllCommunicationAndParameterByType(Integer type) {
        return communicationBridgeDao.loadAllCommunicationAndParameterByType(type);
    }

    @Override
    public List<CommunicationBridge> findAllCommunicationAndParameterByCategory(Integer category) {
        return communicationBridgeDao.loadAllCommunicationAndParameterByCategory(category);
    }
}
