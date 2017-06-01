package com.amsystem.bifaces.menu.service;

import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;

import java.util.List;

/**
 * Title: MenuItemService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/05/2017.
 */


public interface MenuItemService {

    MenuItem findById(MenuItemPK pk);

    List<MenuItem> findByIds(List<MenuItemPK> itemPKs);
}
