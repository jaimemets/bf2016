package com.amsystem.bifaces.menu.dao.impl;

import com.amsystem.bifaces.menu.dao.MenuItemDao;
import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;
import com.amsystem.bifaces.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: MenuItemDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Repository("menuItemDao")
@Transactional
public class MenuItemDaoImpl extends AbstractDao<MenuItemPK, MenuItem> implements MenuItemDao {

    @Override
    public boolean save(MenuItem menuItem) {
        return persist(menuItem);
    }

    @Override
    public boolean updateMenuItem(MenuItem menuItem) {
        return update(menuItem);
    }

    @Override
    public boolean delete(MenuItemPK menuItemId) {
        MenuItem menuItem = getByKey(menuItemId);
        if (menuItem != null)
            return delete(menuItem);

        return false;
    }

    @Override
    public MenuItem getById(MenuItemPK menuItemId) {
        return getByKey(menuItemId);
    }

    @Override
    public List<MenuItem> loadAllMenuItem() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("order"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return criteria.list();

    }

    @Override
    public List<MenuItem> loadMenuItemByIds(List<MenuItemPK> itemPKs) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("order"));
        criteria.add(Restrictions.in("menuItemPK", itemPKs));
        return criteria.list();
    }
}
