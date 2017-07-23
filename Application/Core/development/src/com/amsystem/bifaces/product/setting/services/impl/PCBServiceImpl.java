package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateHibDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.product.setting.dao.PlanConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.dao.TemplatePlanLevelDao;
import com.amsystem.bifaces.product.setting.model.PlanConfigBehavior;
import com.amsystem.bifaces.product.setting.model.PlanTemplateLevelPK;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;
import com.amsystem.bifaces.product.setting.services.PCBService;
import com.amsystem.bifaces.util.CommunicationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Title: PCBServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */

@Service("pcbService")
public class PCBServiceImpl implements PCBService {

    private static final Logger log = LogManager.getLogger(PCBServiceImpl.class.getName());

    @Autowired
    private PlanConfigBehaviorDao pcbDao;

    @Autowired
    private TemplatePlanLevelDao tplDao;

    @Autowired
    private TemplateHibDao templateHibDao;

    @Override
    public boolean updatePCB(PlanConfigBehavior pcb) {
        boolean flag = true;
        try {
            pcbDao.updatePCB(pcb);
        } catch (Exception ex) {
            flag = false;
            if (ex instanceof NonUniqueObjectException) {
                log.error("JRA - NUOE: " + ex.getMessage());
            } else {
                log.error("JRA: " + ex.getMessage());
            }
        }
        return flag;
    }


    @Override
    public boolean addTemplateToPlanLevel(PlanConfigBehavior pcb, Integer idTemplate, int level) {
        boolean flag = false;
        try {
            //Agregando nuevo nivel a la configuracion
            Template template = templateHibDao.loadTemplateById(idTemplate);

            if (template != null) {
                TemplatePlanLevel ptl = new TemplatePlanLevel(pcb, template, Integer.valueOf(level), Integer.valueOf(2), CommunicationType.NOT_ANY.getValue());
                flag = tplDao.saveTemplateToPlanLevel(ptl);
                //pcbDao.updatePCB(pcb);
                pcb.getTemplatePlanLevelSet().add(ptl);
                template.getTemplatePlanLevelSet().add(ptl);
            }
        } catch (Exception ex) {
            flag = false;
            log.error("JRA: " + ex.getMessage());
        }

        return flag;
    }

    @Override
    public boolean deleteTemplateToPlanLevel(PlanConfigBehavior pcb, Integer idTemplate, int level) {
        boolean found = false;
        boolean flag = false;

        Iterator<TemplatePlanLevel> templateLevelIterator = pcb.getTemplatePlanLevelSet().iterator();
        TemplatePlanLevel templatePlanLevel;

        while (templateLevelIterator.hasNext() && !found) {
            templatePlanLevel = templateLevelIterator.next();
            if (templatePlanLevel.getLevel() == level &&
                    templatePlanLevel.getTemplate().getTemplateId() == idTemplate) {

                try {
                    flag = tplDao.deleteProductTemplate(templatePlanLevel.getPk());
                    pcb.getTemplatePlanLevelSet().remove(templatePlanLevel);

                } catch (Exception ex) {
                    flag = false;
                    log.error("[ERROR]  : " + ex.getMessage());
                }

            }
        }


        return flag;
    }

    @Override
    public PlanConfigBehavior findProductConfigBehaviorById(Integer pcbID) {
        return pcbDao.loadProductConfigBehaviorById(pcbID);
    }



    @Override
    public boolean saveUpdate(TemplatePlanLevel templatePlanLevel) {
        TemplatePlanLevel entity = tplDao.loadProductTemplateLevel(templatePlanLevel.getPk());
        boolean flag = false;
        if (entity != null) {
            log.debug("Actualizando registros de PTL");
            entity.setCommunicationBridgeSet(templatePlanLevel.getCommunicationBridgeSet());
            entity.setCommunicationType(templatePlanLevel.getCommunicationType());
            entity.setNumColumn(templatePlanLevel.getNumColumn());
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateTemplateToPlanLevel(TemplatePlanLevel templatePlanLevel) {
        return tplDao.updateProductTemplate(templatePlanLevel);
    }

    @Override
    public TemplatePlanLevel findTemplatePlanLevelByPk(PlanTemplateLevelPK pk) {
        return tplDao.loadProductTemplateLevel(pk);
    }
}
