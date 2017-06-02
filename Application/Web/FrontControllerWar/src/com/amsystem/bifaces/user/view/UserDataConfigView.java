package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserOperation;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
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
    private List<UserProfile> selectedProfiles;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    //Indicador de expiracion del usuario
    private boolean expirable;

    //Indicador si la operacion fue almacenada con exito
    private boolean saved;

    //Lista de perfiles existente en el sistema
    private static List<UserProfile> profileList;

    //Arbol de Menues existentes en el sistema
    private TreeNode root;

    //Items del menu seleccionados
    private TreeNode[] selectedNodes;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;

    @PostConstruct
    public void init() {
        profileList = userOperation.allProfile();

        //Para la edicion de usuario
        String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestUsrName");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        userData = (userName != null) ? userOperation.findUser(userName) : new User();

        if (userData.getUserId() != null) {
            editUser();
        }

        //Se construye el arbol de menues
        root = userOperation.createTree(userData);

    }

    /**
     * Metodo para la actualizacion y edicion de un usuario seleccionado
     */
    public void editUser() {
        expirable = userData.getExpirable() > 0 ? Boolean.TRUE : Boolean.FALSE;
        selectedProfiles = new ArrayList<>();
        selectedProfiles.addAll(userData.getUserProfiles());
    }

    /**
     * Metodo para el registro de un nuevo usuario en el sistema o la actualizacion de un usuario seleccionado
     * segun el tipo de operacion <tt>operation</tt>
     */
    public void saveUser(){
        userData.setExpirable(expirable == Boolean.TRUE ? 1 : 0);
        userData.setUserProfiles(userOperation.getProfiles(selectedProfiles));
        userData.setMenuItems(userOperation.getMenuItem(selectedNodes));

        if (operation == OperationType.CREATE) {
            userData.setExpeditionDate(new Date());
            saved = userOperation.saveUser(userData);
        } else {
            saved = userOperation.updateUser(userData);
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

    public List<UserProfile> getRoleList() {
        return profileList;
    }

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
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

    public List<UserProfile> getSelectedProfiles() {
        return selectedProfiles;
    }

    public void setSelectedProfiles(List<UserProfile> selectedProfiles) {
        this.selectedProfiles = selectedProfiles;
    }
}
