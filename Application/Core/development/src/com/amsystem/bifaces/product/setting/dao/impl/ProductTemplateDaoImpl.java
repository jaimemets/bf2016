package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.ProductTemplateDao;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevelPK;
import com.amsystem.bifaces.util.AbstractDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: ProductTemplateDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */

@Repository("productTemplateDao")
public class ProductTemplateDaoImpl extends AbstractDao<ProductTemplateLevelPK, ProductTemplateLevel> implements ProductTemplateDao {

    @Override
    @Transactional
    public boolean deleteProductTemplate(ProductTemplateLevelPK pk) {
        ProductTemplateLevel ptl = getByKey(pk);
        if (ptl != null)
            return delete(ptl);

        return false;
    }
}
