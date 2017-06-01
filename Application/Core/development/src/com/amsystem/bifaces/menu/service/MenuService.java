package com.amsystem.bifaces.menu.service;

import com.amsystem.bifaces.menu.model.Menu;

import java.util.List;

/**
 * Title: MenuService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */


public interface MenuService {

    Menu findById(int id);

    List<Menu> findAllMenuAppWeb();
}
