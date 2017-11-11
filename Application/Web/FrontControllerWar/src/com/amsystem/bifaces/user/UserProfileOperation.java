package com.amsystem.bifaces.user;

import com.amsystem.bifaces.menu.model.Menu;
import com.amsystem.bifaces.menu.model.MenuItem;
import com.amsystem.bifaces.menu.model.MenuItemPK;
import com.amsystem.bifaces.menu.service.MenuItemService;
import com.amsystem.bifaces.menu.service.MenuService;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.service.ProfileService;
import com.amsystem.bifaces.user.service.UserService;
import com.amsystem.bifaces.user.view.TreeNodeMenu;
import com.amsystem.bifaces.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
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
@ManagedBean(name = "userProfileOperation")
public class UserProfileOperation implements Serializable {

    private static final Logger log = LogManager.getLogger(UserProfileOperation.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

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
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario registrado exitosamente") ;
            success = true;
        } else {
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
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario eliminado exitosamente");
            ComponentOperation.updateComponent("apUserProfile:formUser:userDT");
        } else {
            MessageUtil.showModalMessage(NotificationType.INFO, "Error");
        }
    }


    public TreeNode createTreeProfile() {
        log.debug("**Construyendo arbol de perfiles **");

        TreeNode root = new DefaultTreeNode(new Profile("root"), null);
        TreeNode profilesNode = new DefaultTreeNode(new Profile("Perfiles"), root);
        TreeNode childNodeItem;

        for (Profile profile : getAllProfile()) {
            childNodeItem = new DefaultTreeNode(NodeType.PROPERTY.getLabel(), profile, profilesNode);
            //profilesNode.getChildren().add(new DefaultTreeNode(profile));
        }
        profilesNode.setExpanded(true);
        return root;
    }


    public void addChildProfile(TreeNode root, Profile child) {
        TreeNode childNodeItem = new DefaultTreeNode(NodeType.PROPERTY.getLabel(), child, root.getChildren().get(0));
    }


    /**
     * @param requestUsr
     * @return
     */
    public TreeNode createTree(User requestUsr) {
        log.debug("**Construyendo arbol de permisos para usuario **");
        log.debug("JRA  Usuario: " + requestUsr.getUserName());
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
    public Set<Profile> getProfileIds(List<String> selectedProfiles) {
        String strProfile;
        Set<Profile> profileSet = null;

        if (!selectedProfiles.isEmpty()) {
            List<Integer> codProfile = new ArrayList<>();
            for (int i = 0; i < selectedProfiles.size(); i++) {
                strProfile = String.valueOf(selectedProfiles.get(i));
                codProfile.add(Integer.valueOf(strProfile));

            }
            profileSet = new HashSet<>(profileService.findProfileByIdList(codProfile));
        }
        return profileSet;
    }

    /**
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        return userService.findBySSO(userName);
    }


    /**
     * @return
     */
    public List<Profile> getAllProfile() {
        return profileService.findAllProfile();
    }


    public List<User> getAllUser() {
        return userService.findAllUsers();
    }

    public void addProfile(TreeNode root, Profile profile) {
        if (profileService.saveProfile(profile)) {
            addChildProfile(root, profile);
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Perfil ingresado exitosamente");
            ComponentOperation.updateComponent("apUserProfile:formProfile:profileTree");
        } else {
            MessageUtil.showModalMessage(NotificationType.INFO, "Error");

        }
    }

    public void deleteProfile(Profile selectedProfile) {
        if (profileService.deleteProfileById(selectedProfile.getIdProfile())) {
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), "Usuario ingresado exitosamente");
            ComponentOperation.updateComponent("apUserProfile:formUser:userDT");
        } else {
            MessageUtil.showModalMessage(NotificationType.INFO, "Error");

        }
    }


    public Set<User> getAllUserByProfile(TreeNode selectedNode) {
        Profile profile = (Profile) selectedNode.getData();
        profile = profileService.findFullProfileById(profile.getIdProfile());
        return profile.getUserSet();


    }
}
