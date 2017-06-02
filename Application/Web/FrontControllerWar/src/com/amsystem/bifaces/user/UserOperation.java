package com.amsystem.bifaces.user;

import com.amsystem.bifaces.menu.model.Menu;
import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;
import com.amsystem.bifaces.menu.service.MenuItemService;
import com.amsystem.bifaces.menu.service.MenuService;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserProfileService;
import com.amsystem.bifaces.user.service.UserService;
import com.amsystem.bifaces.user.view.TreeNodeMenu;
import com.amsystem.bifaces.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Title: UserOperation.java <br>
 *
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/12/2016.
 */
@Controller
@ViewScoped
@ManagedBean(name = "userOperation")
public class UserOperation implements Serializable {

    private static final Logger log = LogManager.getLogger(UserOperation.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuItemService menuItemService;


    @Autowired
    private ResourceBundle rb;

    /**
     * @param selectedUser
     */
    public boolean saveUser(User selectedUser) {
        boolean success = false;
        if (userService.saveUser(selectedUser)) {
            //MessageUtil.showMessage("Usuario registrado exitosamente", ErrorType.INFO);
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario registrado exitosamente") ;
            success = true;
            //ComponentOperation.updateComponent("formUser:userDT");
        } else {
            //MessageUtil.showMessage("Error guardando usuario", ErrorType.ERROR);
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")),"Error guardando usuario");
        }

        return success;
    }

    /**
     *
     * @param selectedUser
     */
    public boolean updateUser(User selectedUser) {
        boolean success = false;


        if (userService.updateUser(selectedUser)) {
            //MessageUtil.showMessage("Usuario actualizando exitosamente", ErrorType.INFO);
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario actualizando exitosamente") ;
            success = true;
        } else {
            //MessageUtil.showMessage("Error actualizando usuario", ErrorType.ERROR);
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")),"Error actualizando usuario");
        }
        return success;
    }

    /**
     *
     * @param selectedUser
     */
    public void deleteUser(User selectedUser) {
        if (userService.deleteUserBySSO(selectedUser.getUserName())) {
            //MessageUtil.showModalMessage(NotificationType.INFO, "Usuario eliminado exitosamente");
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario eliminado exitosamente");
            ComponentOperation.updateComponent("formUser:userDT");
        } else {
            MessageUtil.showModalMessage(NotificationType.INFO, "Error");
        }
    }


    public List<User> loadAllUsers() {
        return userService.findAllUsers();
    }


    public List<UserProfile> allProfile() {
        return userProfileService.findAll();
    }

    /**
     * @param requestUsr
     * @return
     */
    public TreeNode createTree(User requestUsr) {
        log.debug("**Inicio Arbol de Plantillas **");
        TreeNode root = new CheckboxTreeNode(new TreeNodeMenu(-1, null, NodeTypeMenu.ROOT), null);
        List<Menu> menuList = menuService.findAllMenuAppWeb();
        Set<MenuItem> userMenuItemSet = null;
        TreeNode parentNodeMenu;
        TreeNode childNodeItem;
        TreeNodeMenu dataNode;

        if (requestUsr != null) {
            userMenuItemSet = requestUsr.getMenuItems();
        }

        for (Menu m : menuList) {
            parentNodeMenu = new CheckboxTreeNode(NodeTypeMenu.MENU.getLabel(), new TreeNodeMenu(m.getMenuId(), rb.getString(m.getCodI18n().concat("_MNU")), NodeTypeMenu.MENU), root);
            log.debug("Objeto creado: " + parentNodeMenu.getClass().toString());

            if (!m.getItemSet().isEmpty()) {
                log.debug("Menu: " + m.getCodI18n());
                MenuItem menuItem;

                Iterator<MenuItem> itemIterator = m.getItemSet().iterator();
                while (itemIterator.hasNext()) {
                    menuItem = itemIterator.next();
                    log.debug("Item: " + menuItem.getDescription());
                    dataNode = new TreeNodeMenu(menuItem.getMenuItemPK().getMenuItemId(), rb.getString(menuItem.getCodI18n().concat("_MNU")), NodeTypeMenu.ITEM);
                    childNodeItem = new CheckboxTreeNode(NodeTypeMenu.ITEM.getLabel(), dataNode, parentNodeMenu);
                    if (userMenuItemSet != null)
                        childNodeItem.setSelected(userMenuItemSet.contains(menuItem));
                }

            }


        }
        return root;
    }

    /**
     * @param selectedNodes
     * @return
     */
    public Set<MenuItem> getMenuItem(TreeNode[] selectedNodes) {
        Set<MenuItem> menuItems = null;
        List<MenuItemPK> itemPKs;

        if (selectedNodes.length > 0) {
            itemPKs = new ArrayList<>();
            for (TreeNode node : selectedNodes) {
                if (node.isLeaf()) {
                    TreeNodeMenu parent = (TreeNodeMenu) node.getParent().getData();
                    TreeNodeMenu child = (TreeNodeMenu) node.getData();
                    itemPKs.add(new MenuItemPK(parent.getId(), child.getId()));
                }
            }
            menuItems = new HashSet<>(menuItemService.findByIds(itemPKs));
        }
        return menuItems;
    }

    /**
     *
     * @param selectedProfiles
     * @return
     */
    public Set<UserProfile> getProfiles(List<UserProfile> selectedProfiles) {
        String strProfile = null;
        Set<UserProfile> userProfileSet = null;

        if (!selectedProfiles.isEmpty()) {
            List<Integer> idRoles = new ArrayList<>();
            int beginIndex;
            for (int i = 0; i < selectedProfiles.size(); i++) {
                strProfile = String.valueOf(selectedProfiles.get(i));
                beginIndex = strProfile.indexOf(SymbolType.EQUALS_SYMBOL.getValue());
                idRoles.add(Integer.valueOf(strProfile.substring(beginIndex + 1, beginIndex + 2)));
            }
            userProfileSet = new HashSet<>(userProfileService.finProfilesByIds(idRoles));
        }
        return userProfileSet;
    }

    /**
     * @param userName
     * @return
     */
    public User findUser(String userName) {
        return userService.findBySSO(userName);
    }
}
