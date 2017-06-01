package com.amsystem.bifaces.menu.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Title: MenuItemPK.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Embeddable
public class MenuItemPK implements Serializable {

    @Column(name = "ID_MENU")
    protected Integer menuId;

    @Column(name = "ID_MI")
    protected Integer menuItemId;


    public MenuItemPK() {
    }

    public MenuItemPK(Integer menuId, Integer menuItemId) {
        this.menuId = menuId;
        this.menuItemId = menuItemId;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemPK)) return false;

        MenuItemPK that = (MenuItemPK) o;

        if (!menuId.equals(that.menuId)) return false;
        if (!menuItemId.equals(that.menuItemId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuItemId.hashCode();
        result = 31 * result + menuId.hashCode();
        return result;
    }
}
