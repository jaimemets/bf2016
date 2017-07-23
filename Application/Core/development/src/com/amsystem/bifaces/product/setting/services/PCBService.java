package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;

/**
 * Title: PCBService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface PCBService {

    boolean updatePCB(ProductConfigBehavior pcb);

    ProductConfigBehavior findProductConfigBehaviorById(Integer pcbID);

    boolean addTemplateToPlanLevel(ProductConfigBehavior pcb, Integer idTemplate, int level);

    boolean deleteTemplateToPlanLevel(ProductConfigBehavior pcb, Integer idTemplate, int level);

    boolean updateTemplateToPlanLevel(TemplatePlanLevel templatePlanLevel);

    boolean saveUpdate(TemplatePlanLevel templatePlanLevel);

    TemplatePlanLevel findTemplatePlanLevelByPk(ProductTemplateLevelPK pk);
}
