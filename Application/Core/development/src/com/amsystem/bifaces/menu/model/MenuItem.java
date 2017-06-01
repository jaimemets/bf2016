package com.amsystem.bifaces.menu.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Title: MenuItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Entity
@Table(name = "MENU_ITEM")
public class MenuItem implements Serializable {

    @EmbeddedId
    private MenuItemPK menuItemPK;

    @Column(name = "ORDER")
    private Integer order;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "VISIBLE")
    private Integer visible;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COD_I18N")
    private String codI18n;

    @Column(name = "PATH")
    private String path;

    public MenuItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;

        MenuItem menuItem = (MenuItem) o;

        if (!menuItemPK.equals(menuItem.menuItemPK)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return menuItemPK.hashCode();
    }

    public MenuItemPK getMenuItemPK() {
        return menuItemPK;
    }

    public void setMenuItemPK(MenuItemPK menuItemPK) {
        this.menuItemPK = menuItemPK;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodI18n() {
        return codI18n;
    }

    public void setCodI18n(String codI18n) {
        this.codI18n = codI18n;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
