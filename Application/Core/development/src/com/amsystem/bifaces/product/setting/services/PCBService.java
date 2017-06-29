package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;

/**
 * Title: PCBService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface PCBService {

    boolean updatePCB(ProductConfigBehavior pcb);

    ProductConfigBehavior findProductConfigBehaviorById(Integer pcbID);

    boolean deleteProductTemplate(ProductTemplateLevelPK pk);

    boolean saveUpdate(ProductTemplateLevel productTemplateLevel);

    ProductTemplateLevel findProductTemplateLevelByPk(ProductTemplateLevelPK pk);

    boolean updateProductTemplateLevel(ProductTemplateLevel productTemplateLevel);
}
