package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;

/**
 * Title: ProductTemplateDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */


public interface ProductTemplateDao {

    boolean deleteProductTemplate(ProductTemplateLevelPK pk);

    boolean updateProductTemplate(ProductTemplateLevel productTemplateLevel);

    ProductTemplateLevel loadProductTemplateLevel(ProductTemplateLevelPK pk);
}
