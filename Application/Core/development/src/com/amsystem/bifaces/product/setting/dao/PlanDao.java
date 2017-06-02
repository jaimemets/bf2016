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

    boolean updateProduct(Plan plan);

    boolean delete(String planName);

    Plan getById(Integer planId);

    List<Plan> loadAllPlan();


}
