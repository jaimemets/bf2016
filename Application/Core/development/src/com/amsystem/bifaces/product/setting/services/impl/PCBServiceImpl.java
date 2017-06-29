package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.ProductConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.dao.ProductTemplateDao;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;
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

    @Autowired
    private ProductTemplateDao productTemplateDao;

    @Override
    public boolean updatePCB(ProductConfigBehavior pcb) {
        return pcbDao.updatePCB(pcb);
    }

    @Override
    public ProductConfigBehavior findProductConfigBehaviorById(Integer pcbID) {
        return pcbDao.loadProductConfigBehaviorById(pcbID);
    }

    @Override
    public boolean deleteProductTemplate(ProductTemplateLevelPK pk) {
        return productTemplateDao.deleteProductTemplate(pk);
    }

    @Override
    public boolean saveUpdate(ProductTemplateLevel productTemplateLevel) {
        ProductTemplateLevel entity = productTemplateDao.loadProductTemplateLevel(productTemplateLevel.getPk());
        boolean flag = false;
        if (entity != null) {
            log.debug("Actualizando registros de PTL");
            entity.setCommunicationBridgeSet(productTemplateLevel.getCommunicationBridgeSet());
            entity.setCommunicationType(productTemplateLevel.getCommunicationType());
            entity.setNumColumn(productTemplateLevel.getNumColumn());
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateProductTemplateLevel(ProductTemplateLevel productTemplateLevel) {
        return productTemplateDao.updateProductTemplate(productTemplateLevel);
    }

    @Override
    public ProductTemplateLevel findProductTemplateLevelByPk(ProductTemplateLevelPK pk) {
        return productTemplateDao.loadProductTemplateLevel(pk);
    }
}
