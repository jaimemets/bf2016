package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.PlanDao;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: PlanDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 10/06/2017.
 */

@Repository("planDao")
public class PlanDaoImpl extends AbstractDao<Integer, Plan> implements PlanDao {

    @Override
    @Transactional
    public boolean save(Plan plan) {
        return persist(plan);
    }

    @Override
    @Transactional
    public boolean updatePlan(Plan plan) {
        return update(plan);
    }

    @Override
    @Transactional
    public boolean delete(Integer pk) {
        Plan plan = getByKey(pk);
        if (plan != null) {
            return delete(plan);
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Plan loadPlanById(Integer pk) {
        Plan plan = getByKey(pk);
        if (plan != null) {
            Hibernate.initialize(plan.getPcBehavior().getProductTemplateLevelSet());
        }

        return plan;
    }

    @Override
    @Transactional(readOnly = true)
    public Plan loadPlanProductConfigById(Integer idPlan) {
        Plan plan = loadPlanById(idPlan);
        if (plan != null) {
            Hibernate.initialize(plan.getPcBehavior().getProductTemplateLevelSet());
//            Hibernate.initialize(plan.getPcBehavior().getProductTemplateLevelSet());
        }
        return plan;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plan> loadAllPlan() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Plan>) criteria.list();
    }
}
