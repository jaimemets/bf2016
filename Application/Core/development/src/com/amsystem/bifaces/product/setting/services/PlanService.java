package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.Plan;

/**
 * Title: PlanService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 13/06/2017.
 */


public interface PlanService {

    boolean updatePlan(Plan plan);

    Plan findPlanById(Integer idPlan);
}
