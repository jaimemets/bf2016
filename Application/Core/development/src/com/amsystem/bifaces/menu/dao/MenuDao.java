package com.amsystem.bifaces.menu.dao;

import com.amsystem.bifaces.menu.model.Menu;

import java.util.List;

/**
 * Title: MenuDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */


public interface MenuDao {

    boolean save(Menu menu);

    boolean updateMenu(Menu menu);

    boolean delete(Integer menuId);

    Menu getById(Integer menuId);

    List<Menu> loadAllMenu();

    List<Menu> loadMenuByStatus(Integer status);
}
