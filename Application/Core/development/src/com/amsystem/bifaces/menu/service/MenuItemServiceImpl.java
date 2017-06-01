package com.amsystem.bifaces.menu.service;

import com.amsystem.bifaces.menu.dao.MenuItemDao;
import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: MenuItemServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/05/2017.
 */

@Service("menuItemService")
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemDao menuItemDao;

    @Override
    public MenuItem findById(MenuItemPK pk) {
        return null;
    }

    @Override
    public List<MenuItem> findByIds(List<MenuItemPK> itemPKs) {
        return menuItemDao.loadMenuItemByIds(itemPKs);
    }
}
