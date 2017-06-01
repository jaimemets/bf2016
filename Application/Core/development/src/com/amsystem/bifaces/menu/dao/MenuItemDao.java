package com.amsystem.bifaces.menu.dao;

import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;

import java.util.List;

/**
 * Title: MenuItemDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */


public interface MenuItemDao {

    boolean save(MenuItem menuItem);

    boolean updateMenuItem(MenuItem menuItem);

    boolean delete(MenuItemPK menuItemId);

    MenuItem getById(MenuItemPK menuItemId);

    List<MenuItem> loadAllMenuItem();

    List<MenuItem> loadMenuItemByIds(List<MenuItemPK> itemPKs);
}
