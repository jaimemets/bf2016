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
 * Title: UserFormSection.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 25/05/2017.
 */

@ViewScoped
@ManagedBean(name = "userFormView")
public class UserFormView implements Serializable{

    private User userData;

    private List<UserProfile> selectedProfiles;

    private OperationType operation;

    private boolean expirable;

    private boolean saved;

    private static List<UserProfile> profileList;

    //Raiz del arbol de plantillas
    private TreeNode root;

    private TreeNode[] selectedNodes;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;

    @PostConstruct
    public void init() {
        profileList = userOperation.allProfile();

        //Para la edicion de usuario
        User requestUsr = (User) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestUsr");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        if(requestUsr != null) {
            userData = requestUsr;
            if(requestUsr.getUserId() != null) editUser();
        }

        root = userOperation.createTree(requestUsr);

    }

    public void editUser() {
        expirable = userData.getExpirable() > 0 ? Boolean.TRUE : Boolean.FALSE;
        selectedProfiles = new ArrayList<>();
        selectedProfiles.addAll(userData.getUserProfiles());
    }

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
