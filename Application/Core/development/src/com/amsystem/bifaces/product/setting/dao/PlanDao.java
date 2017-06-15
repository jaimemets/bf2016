package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.Plan;

import java.util.List;

/**
 * Title: PlanDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 26/05/2017.
 */


public interface PlanDao {

    boolean save(Plan plan);

    boolean updatePlan(Plan plan);

    boolean delete(Integer idPlan);

    Plan loadPlanById(Integer idPlan);

    Plan loadPlanProductConfigById(Integer idPlan);

    List<Plan> loadAllPlan();


}
