package com.amsystem.bifaces.menu.dao.impl;

import com.amsystem.bifaces.menu.dao.MenuDao;
import com.amsystem.bifaces.menu.model.Menu;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: MenuDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Repository("menuDao")
@Transactional
public class MenuDaoImpl extends AbstractDao<Integer, Menu> implements MenuDao {

    private static final Logger log = LogManager.getLogger(MenuDaoImpl.class.getName());

    @Override
    public boolean save(Menu menu) {
        return persist(menu);
    }

    @Override
    public boolean updateMenu(Menu menu) {
        return update(menu);
    }

    @Override
    public boolean delete(Integer menuId) {
        Menu menu = getById(menuId);
        if (menu != null)
            return delete(menu);

        return false;
    }

    @Override
    public Menu getById(Integer menuId) {
        return getByKey(menuId);
    }

    @Override
    public List<Menu> loadAllMenu() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("order"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return criteria.list();
    }

    @Override
    public List<Menu> loadMenuByStatus(Integer status) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("order"));
        criteria.add(Restrictions.eq("status", status));
        return criteria.list();
    }
}
