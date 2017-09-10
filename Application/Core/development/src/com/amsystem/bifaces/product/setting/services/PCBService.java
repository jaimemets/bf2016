package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.product.setting.model.PlanConfigBehavior;
import com.amsystem.bifaces.product.setting.model.PlanTemplateLevelPK;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;

import java.util.HashMap;
import java.util.List;

/**
 * Title: PCBService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface PCBService {

    boolean updatePCB(PlanConfigBehavior pcb);

    PlanConfigBehavior findProductConfigBehaviorById(Integer pcbID);

    boolean addTemplateToPlanLevel(PlanConfigBehavior pcb, Integer idTemplate, int level);

    boolean deleteTemplateToPlanLevel(PlanConfigBehavior pcb, Integer idTemplate, int level);

    boolean updateTemplateToPlanLevel(TemplatePlanLevel templatePlanLevel, HashMap<Integer, List<Property>> propertyListAndWSMap);

    boolean saveUpdate(TemplatePlanLevel templatePlanLevel);

    TemplatePlanLevel findTemplatePlanLevelByPk(PlanTemplateLevelPK pk);

    boolean updatePropertyToCommunicationBridge(TemplatePlanLevel templatePlanLevel, HashMap<Integer, List<Property>> propertyListAndWSMap);
}
