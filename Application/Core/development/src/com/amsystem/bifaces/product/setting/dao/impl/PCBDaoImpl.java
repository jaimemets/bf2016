package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.ProductConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: PCBDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */

@Repository("pcbDao")
public class PCBDaoImpl extends AbstractDao<Integer, ProductConfigBehavior> implements ProductConfigBehaviorDao {

    private static final Logger log = LogManager.getLogger(PCBDaoImpl.class.getName());

    @Override
    @Transactional
    public boolean updatePCB(ProductConfigBehavior pcb) {
        return update(pcb);
    }

    @Override
    @Transactional
    public boolean saveUpdatePCB(ProductConfigBehavior pcb) {
        return saveOrUpdate(pcb);
    }
}
