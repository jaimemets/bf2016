package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.PlanConfigBehavior;

/**
 * Title: ProductConfigBehaviorDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface PlanConfigBehaviorDao {

    void updatePCB(PlanConfigBehavior pcb);

    boolean saveUpdatePCB(PlanConfigBehavior pcb);

    PlanConfigBehavior loadProductConfigBehaviorById(Integer pcbID);
}
