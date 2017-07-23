package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.PlanDao;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public boolean updatePlan(Plan plan) {
        return planDao.updatePlan(plan);
    }

    @Override
    public Plan findPlanById(Integer idPlan) {
        return planDao.loadPlanById(idPlan);
    }

}
