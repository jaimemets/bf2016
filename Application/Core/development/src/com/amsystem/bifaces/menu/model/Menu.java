package com.amsystem.bifaces.menu.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Title: Menu.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENU")
    private Integer menuId;

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

    @Column(name = "ID_SUBMENU")
    private Menu subMenu;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MENU")
    private Set<MenuItem> itemSet;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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

    public Menu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    public Set<MenuItem> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<MenuItem> itemSet) {
        this.itemSet = itemSet;
    }
}
