package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserProfileOperation;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.NodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: ProfileToolView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 16/10/2017.
 */

@ViewScoped
@ManagedBean(name = "profileToolView")
public class ProfileToolView implements Serializable {

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(UserToolView.class.getName());

    //Raiz del arbol de perfiles
    private TreeNode root;

    //Nodo seleccionado en el arbol de perfiles
    private TreeNode selectedNode;

    //Lista de todos los perfiles en el sistema
    private List<Profile> allProfile;

    //Lista de usuarios pertenecientes a un perfil seleccionado
    private List<User> allUserProfile;

    //Perfil seleccionado de la tabla perfiles-descripcion
    private Profile selectedProfile;

    //Usuario seleccionado de la tabla usuario-perfil
    private User selectedUser;

    //Campo nombre perfil
    private String nameProfile;

    //Campo nombre usuario
    private String userStr;

    @ManagedProperty("#{userProfileOperation}")
    private UserProfileOperation userProfileOperation;

    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    @PostConstruct
    public void init() {
        allProfile = userProfileOperation.getAllProfile();
        allUserProfile = new ArrayList<>();
        root = userProfileOperation.createTreeProfile();
    }


    public void addProfile() {
        log.debug("Agregando el Perfil: " + nameProfile);
        Profile profile = new Profile(nameProfile.toUpperCase());
        userProfileOperation.addProfile(getFirstChild(), profile);
        setNameProfile(null);

    }


    /**
     * Elimina un <tt>OptionItem</tt> de una propiedad seleccionada
     */
    public void deleteProfile() {
        log.debug("Eliminando el perfil: " + selectedProfile.getName());
        userProfileOperation.deleteProfile(selectedProfile);
        setSelectedProfile(null);

    }

    public void onNodeSelect(NodeSelectEvent event) {
        allUserProfile.clear();
        if (event.getTreeNode().getType().equalsIgnoreCase(NodeType.PROPERTY.getLabel())) {
            allUserProfile.addAll(userProfileOperation.getAllUserByProfile(event.getTreeNode()));
        }
        log.debug("allUserProfile size: " + allUserProfile.size());

    }

    public void onRowEdit(RowEditEvent event) {
        Profile profile = (Profile) event.getObject();
        userProfileOperation.updateProfile(profile);
    }


    private TreeNode getFirstChild() {
        return root.getChildren().get(0);
    }

    public List<User> getUserProfile() {
        return allUserProfile;
    }

    public List<Profile> getAllProfile() {
        return allProfile;
    }

    public void setAllProfile(List<Profile> allProfile) {
        this.allProfile = allProfile;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public UserProfileOperation getUserProfileOperation() {
        return userProfileOperation;
    }

    public void setUserProfileOperation(UserProfileOperation userProfileOperation) {
        this.userProfileOperation = userProfileOperation;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<User> getAllUserProfile() {
        return allUserProfile;
    }

    public void setAllUserProfile(List<User> allUserProfile) {
        this.allUserProfile = allUserProfile;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }
}
