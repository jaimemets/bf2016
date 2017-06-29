package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateHibDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.product.setting.dao.PlanDao;
import com.amsystem.bifaces.product.setting.dao.ProductConfigBehaviorDao;
import com.amsystem.bifaces.product.setting.dao.ProductTemplateDao;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * Title: PlanServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */

@Service("planService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private ProductConfigBehaviorDao pcbDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private TemplateHibDao templateHibDao;

    @Autowired
    ProductTemplateDao productTemplateDao;

    @Override
    public boolean updatePlan(Plan plan) {
        return planDao.updatePlan(plan);
    }

    @Override
    public Plan findPlanById(Integer idPlan) {
        return planDao.loadPlanById(idPlan);
    }

    @Override
    public boolean addTemplateConfigurationLevel(Plan plan, Integer idTemplate, int level) {
        //Plan plan = planDao.loadPlanProductConfigById(idPlan);
        ProductConfigBehavior pcBehavior = plan.getPcBehavior();

        //Agregando nuevo nivel a la configuracion
        Template template = templateHibDao.loadTemplateById(idTemplate);
        ProductTemplateLevel newLevel = new ProductTemplateLevel();

        Set<ProductTemplateLevel> productTemplateLevelSet = template.getProductTemplateLevelSet();

        newLevel.setProductConfigBehavior(pcBehavior);
        newLevel.setLevel(Integer.valueOf(level));
        newLevel.setNumColumn(Integer.valueOf(2));
        newLevel.setCommunicationType(Integer.valueOf(0));

        productTemplateLevelSet.add(newLevel);
        template.setProductTemplateLevelSet(productTemplateLevelSet);

        newLevel.setTemplate(template);

        pcBehavior.getProductTemplateLevelSet().add(newLevel);

        return pcbDao.saveUpdatePCB(pcBehavior);
    }

    @Override
    public boolean deleteTemplateConfigurationLevel(Plan plan, Integer idTemplate, int level) {
        ProductConfigBehavior pcBehavior = plan.getPcBehavior();
        boolean found = false;
        Iterator<ProductTemplateLevel> templateLevelIterator = pcBehavior.getProductTemplateLevelSet().iterator();
        ProductTemplateLevel productTemplateLevel = null;

        while (templateLevelIterator.hasNext() && !found) {
            productTemplateLevel = templateLevelIterator.next();
            if (productTemplateLevel.getLevel() == level &&
                    productTemplateLevel.getTemplate().getTemplateId() == idTemplate) {
                productTemplateDao.deleteProductTemplate(productTemplateLevel.getPk());
                pcBehavior.getProductTemplateLevelSet().remove(productTemplateLevel);
            }
        }


        return pcbDao.saveUpdatePCB(pcBehavior);
    }


    @Override
    public boolean updateProductLevel(Plan plan, ProductTemplateLevel productTemplateLevel) {
        return false;
    }
}
