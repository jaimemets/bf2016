package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.PlanConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.model.PlanConfigBehavior;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;
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
public class PCBDaoImpl extends AbstractDao<Integer, PlanConfigBehavior> implements PlanConfigBehaviorDao {

    private static final Logger log = LogManager.getLogger(PCBDaoImpl.class.getName());

    @Override
    @Transactional
    public void updatePCB(PlanConfigBehavior pcb) {
        PlanConfigBehavior entity = loadProductConfigBehaviorById(pcb.getPcbID());

        if (entity != null) {
            entity.setStatus(pcb.getStatus());
            entity.setNumColumn(pcb.getNumColumn());
            entity.getTemplatePlanLevelSet().clear();
            entity.setTemplatePlanLevelSet(pcb.getTemplatePlanLevelSet());
        }

    }

    @Override
    @Transactional
    public boolean saveUpdatePCB(PlanConfigBehavior pcb) {
        return saveOrUpdate(pcb);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanConfigBehavior loadProductConfigBehaviorById(Integer pcbID) {
        PlanConfigBehavior planConfigBehavior = getByKey(pcbID);
        if (planConfigBehavior != null) {
            Hibernate.initialize(planConfigBehavior.getTemplatePlanLevelSet());

            if (!planConfigBehavior.getTemplatePlanLevelSet().isEmpty()) {
                Iterator<TemplatePlanLevel> levelIterator = planConfigBehavior.getTemplatePlanLevelSet().iterator();
                TemplatePlanLevel templateLevel;
                while (levelIterator.hasNext()) {
                    templateLevel = levelIterator.next();
                    Hibernate.initialize(templateLevel.getProductConfigBehavior());
                    Hibernate.initialize(templateLevel.getTemplate());
                    Hibernate.initialize(templateLevel.getCommunicationBridgeSet());
                }
            }

        }

        return planConfigBehavior;
    }
}
