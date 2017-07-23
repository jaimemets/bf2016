package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;

/**
 * Title: ProductTemplateDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */


public interface TemplatePlanLevelDao {

    boolean saveTemplateToPlanLevel(TemplatePlanLevel templateLevel);

    boolean updateProductTemplate(TemplatePlanLevel templatePlanLevel);

    boolean deleteProductTemplate(ProductTemplateLevelPK pk);

    TemplatePlanLevel loadProductTemplateLevel(ProductTemplateLevelPK pk);
}
