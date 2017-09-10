package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateHibDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.product.setting.dao.CommunicationBridgeDao;
import com.amsystem.bifaces.product.setting.dao.PlanConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.dao.PropertyCommunicationLevelDao;
import com.amsystem.bifaces.product.setting.dao.TemplatePlanLevelDao;
import com.amsystem.bifaces.product.setting.model.*;
import com.amsystem.bifaces.product.setting.services.CommunicationService;
import com.amsystem.bifaces.product.setting.services.PCBService;
import com.amsystem.bifaces.util.CommunicationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    PropertyDao propertyDao;

    @Autowired
    CommunicationBridgeDao commBridgeDao;

    @Autowired
    PropertyCommunicationLevelDao propertyCommunicationLevelDao;

    @Autowired
    CommunicationService communicationService;


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
    public PlanConfigBehavior findProductConfigBehaviorById(Integer pcbID) {
        return pcbDao.loadProductConfigBehaviorById(pcbID);
    }


    @Override
    public boolean addTemplateToPlanLevel(PlanConfigBehavior pcb, Integer idTemplate, int level) {
        boolean flag = false;
        try {
            //Obteniendo la plantilla a vincular con el nivel
            Template template = templateHibDao.loadTemplateById(idTemplate);

            if (template != null) {
                TemplatePlanLevel ptl = new TemplatePlanLevel(pcb, template, Integer.valueOf(level), Integer.valueOf(2), CommunicationType.NOT_ANY.getValue());
                flag = tplDao.saveTemplateToPlanLevel(ptl);

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
    public boolean updateTemplateToPlanLevel(TemplatePlanLevel templatePlanLevel, HashMap<Integer, List<Property>> propertyListAndWSMap) {
        if (tplDao.updateProductTemplate(templatePlanLevel)) {
            log.debug("Actualizado TemplatePlanLevel...");
            return updatePropertyToCommunicationBridge(templatePlanLevel, propertyListAndWSMap);
        } else return false;
    }

    @Override
    public TemplatePlanLevel findTemplatePlanLevelByPk(PlanTemplateLevelPK pk) {
        return tplDao.loadProductTemplateLevel(pk);
    }

    @Override
    public boolean updatePropertyToCommunicationBridge(TemplatePlanLevel templatePlanLevel, HashMap<Integer, List<Property>> propertyListAndWSMap) {
        boolean flag = true;

        CommunicationBridge cb = null;

        Set<CommunicationBridge> communicationBridgeSet = templatePlanLevel.getCommunicationBridgeSet();
        if (!communicationBridgeSet.isEmpty()) {
            Iterator<CommunicationBridge> communicationBridgeIterator = communicationBridgeSet.iterator();
            while (communicationBridgeIterator.hasNext()) {
                try {
                    cb = communicationBridgeIterator.next();
                    CommunicationBridge entityCB = communicationService.findCommunicationAndParameter(cb.getCbId());
                    List<Property> propertyList = propertyListAndWSMap.get(cb.getCbId());
                    if (propertyList != null || !propertyList.isEmpty()) {
                        List<PropertyCommunicationLevel> pclList = new ArrayList<>();
                        entityCB.getPropertyCommunicationLevelSet().clear();

                        for (Property prop : propertyList) {
                            PropertyCommunicationLevelPK pclPK = new PropertyCommunicationLevelPK(cb, prop, templatePlanLevel.getPk());
                            pclList.add(new PropertyCommunicationLevel(pclPK));
                        }

                        entityCB.getPropertyCommunicationLevelSet().addAll(pclList);
                        flag = commBridgeDao.updateCommunicationBridge(entityCB);
                    }

                } catch (Exception ex) {
                    flag = false;
                    log.error("No se pudo actualizar el Puente de Comunicacion: " + cb.getCbId() + "\t" + cb.getDescription());
                    log.error("Msj: " + ex.getMessage());
                    templatePlanLevel.getCommunicationBridgeSet().remove(cb);
                    templatePlanLevel.getCommunicationBridgeSet().add(commBridgeDao.loadCommunicationAndParameter(cb.getCbId()));
                }
            }

        }

        return flag;
    }


}
