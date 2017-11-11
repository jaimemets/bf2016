package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserProfileOperation;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.OperationType;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title: UserDataConfigView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 25/05/2017.
 */

@ViewScoped
@ManagedBean(name = "userDataConfigView")
public class UserDataConfigView implements Serializable {

    //Dato del usuario
    private User userData;

    //Lista de perfiles vinculado a un usuario
    private List<String> selectedProfiles;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    //Indicador de expiracion del usuario
    private boolean expirable;

    //Indicador si la operacion fue almacenada con exito
    private boolean saved;

    //Lista de perfiles existente en el sistema
    private static List<Profile> profileList;

    //Arbol de Menues existentes en el sistema
    private TreeNode root;

    //Items del menu seleccionados
    private TreeNode[] selectedNodes;

    @ManagedProperty("#{userProfileOperation}")
    private UserProfileOperation userProfileOperation;

    @PostConstruct
    public void init() {
        profileList = userProfileOperation.getAllProfile();

        //Para la edicion de usuario
        String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestUsrName");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        userData = (userName != null) ? userProfileOperation.getUserByName(userName) : new User();

        if (userData.getUserId() != null) {
            editUser();
        }

        //Se construye el arbol de menues
        root = userProfileOperation.createTree(userData);

    }

    /**
     * Metodo para la actualizacion y edicion de un usuario seleccionado
     */
    public void editUser() {
        expirable = userData.getExpirable() > 0 ? Boolean.TRUE : Boolean.FALSE;
        selectedProfiles = new ArrayList<>();
        for (Profile profile : userData.getProfiles()) {
            selectedProfiles.add(String.valueOf(profile.getIdProfile()));
        }
    }

    /**
     * Metodo para el registro de un nuevo usuario en el sistema o la actualizacion de un usuario seleccionado
     * segun el tipo de operacion <tt>operation</tt>
     */
    public void saveUser(){
        userData.setExpirable(expirable == Boolean.TRUE ? 1 : 0);
        userData.setProfiles(userProfileOperation.getProfileIds(selectedProfiles));
        userData.setMenuItems(userProfileOperation.getMenuItem(selectedNodes));

        if (operation == OperationType.CREATE) {
            userData.setExpeditionDate(new Date());
            userData.setPassword("1234");
            saved = userProfileOperation.saveUser(userData);
        } else {
            saved = userProfileOperation.updateUser(userData);
        }

    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public boolean isExpirable() {
        return expirable;
    }

    public void setExpirable(boolean expirable) {
        this.expirable = expirable;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public List<Profile> getRoleList() {
        return profileList;
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

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public List<String> getSelectedProfiles() {
        return selectedProfiles;
    }

    public void setSelectedProfiles(List<String> selectedProfiles) {
        this.selectedProfiles = selectedProfiles;
    }
}
