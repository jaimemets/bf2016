package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.TemplatePlanLevelDao;
import com.amsystem.bifaces.product.setting.model.PlanTemplateLevelPK;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: ProductTemplateDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */

@Repository("productTemplateDao")
public class TemplatePlanLevelDaoImpl extends AbstractDao<PlanTemplateLevelPK, TemplatePlanLevel> implements TemplatePlanLevelDao {

    private static final Logger log = LogManager.getLogger(TemplatePlanLevelDaoImpl.class.getName());

    @Override
    @Transactional
    public boolean saveTemplateToPlanLevel(TemplatePlanLevel templateLevel) {
        this.flag = true;
        try {
            persist(templateLevel);
        } catch (Exception ex) {
            flag = false;
            log.error("ERROR - JRA : " + ex.getMessage());
        }
        return this.flag;
    }

    @Override
    @Transactional
    public boolean updateProductTemplate(TemplatePlanLevel templatePlanLevel) {
        return update(templatePlanLevel);
    }

    @Override
    @Transactional
    public boolean deleteProductTemplate(PlanTemplateLevelPK pk) {
        TemplatePlanLevel ptl = getByKey(pk);
        if (ptl != null)
            return delete(ptl);

        return false;
    }


    @Override
    @Transactional(readOnly = true)
    public TemplatePlanLevel loadProductTemplateLevel(PlanTemplateLevelPK pk) {
        TemplatePlanLevel templateLevel = getByKey(pk);
        if (templateLevel != null) {
            Hibernate.initialize(templateLevel.getProductConfigBehavior());
            Hibernate.initialize(templateLevel.getTemplate());
            Hibernate.initialize(templateLevel.getCommunicationBridgeSet());
        }
        return templateLevel;
    }
}
