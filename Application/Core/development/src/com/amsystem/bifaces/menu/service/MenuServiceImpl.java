package com.amsystem.bifaces.menu.service;

import com.amsystem.bifaces.menu.dao.MenuDao;
import com.amsystem.bifaces.menu.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: MenuServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu findById(int id) {
        return menuDao.getById(id);
    }

    @Override
    public List<Menu> findAllMenuAppWeb() {
        return menuDao.loadAllMenu();
    }
}
