package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.ProductConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

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

    @Override
    @Transactional(readOnly = true)
    public ProductConfigBehavior loadProductConfigBehaviorById(Integer pcbID) {
        ProductConfigBehavior productConfigBehavior = getByKey(pcbID);
        if (productConfigBehavior != null) {
            Hibernate.initialize(productConfigBehavior.getProductTemplateLevelSet());

            if (!productConfigBehavior.getProductTemplateLevelSet().isEmpty()) {
                Iterator<ProductTemplateLevel> levelIterator = productConfigBehavior.getProductTemplateLevelSet().iterator();
                ProductTemplateLevel templateLevel;
                while (levelIterator.hasNext()) {
                    templateLevel = levelIterator.next();
                    Hibernate.initialize(templateLevel.getProductConfigBehavior());
                    Hibernate.initialize(templateLevel.getTemplate());
                    Hibernate.initialize(templateLevel.getCommunicationBridgeSet());
                }
            }

        }

        return productConfigBehavior;
    }
}
