package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.ProductConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.services.PCBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: PCBServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */

@Service("pcbService")
public class PCBServiceImpl implements PCBService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductConfigBehaviorDao pcbDao;

    @Override
    public boolean updatePCB(ProductConfigBehavior pcb) {
        return pcbDao.updatePCB(pcb);
    }
}
