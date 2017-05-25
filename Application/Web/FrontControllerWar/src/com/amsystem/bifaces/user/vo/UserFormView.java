package com.amsystem.bifaces.user.vo;

import com.amsystem.bifaces.user.UserOperation;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.util.OperationType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

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

    private List<String> selectedProfile;

    private OperationType operation;

    private boolean expirable;

    private static List<String> roleList;

    private static Map<String, UserProfile> roles;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;


    @PostConstruct
    public void init() {
        List<UserProfile> userProfiles = userOperation.allProfile();
        roles = new LinkedHashMap<>();
        roleList = new ArrayList<>();
        for (UserProfile up : userProfiles) {
            roles.put(up.getType(), up);
            roleList.add(up.getType());
        }

        //Para la edicion de usuario
        User requestUsr = (User) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestUsr");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        if(requestUsr != null) {
            userData = requestUsr;
            if(requestUsr.getUserId() != null) editUser();
        }

    }

    public void editUser() {
        expirable = userData.getExpirable() > 0 ? Boolean.TRUE : Boolean.FALSE;
        Iterator<UserProfile> iterator = userData.getUserProfiles().iterator();
        selectedProfile = new ArrayList<>();
        while (iterator.hasNext()) {
            int i = 0;
            UserProfile nextProfile = iterator.next();
            selectedProfile.add(nextProfile.getType());
            i++;
        }
    }

    public void saveUser(){
        userData.setExpirable(expirable == Boolean.TRUE ? 1 : 0);
        HashSet<UserProfile> rol = new HashSet<UserProfile>();
        for (String userProfile : selectedProfile) {
            rol.add(roles.get(userProfile));
        }

        userData.setUserProfiles(rol);

        if (operation == OperationType.CREATE) {
            userData.setExpeditionDate(new Date());
            userOperation.saveUser(userData);
        } else {
            userOperation.updateUser(userData);
        }

    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public List<String> getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(List<String> selectedProfile) {
        this.selectedProfile = selectedProfile;
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

    public List<String> getRoleList() {
        return roleList;
    }

    public static void setRoleList(List<String> roleList) {
        UserFormView.roleList = roleList;
    }

    public static Map<String, UserProfile> getRoles() {
        return roles;
    }

    public static void setRoles(Map<String, UserProfile> roles) {
        UserFormView.roles = roles;
    }

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }
}
