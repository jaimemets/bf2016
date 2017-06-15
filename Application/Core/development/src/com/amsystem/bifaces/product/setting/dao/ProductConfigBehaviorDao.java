package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;

/**
 * Title: ProductConfigBehaviorDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface ProductConfigBehaviorDao {

    boolean updatePCB(ProductConfigBehavior pcb);

    boolean saveUpdatePCB(ProductConfigBehavior pcb);
}
