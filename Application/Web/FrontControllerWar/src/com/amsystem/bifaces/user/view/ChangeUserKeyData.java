package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserOperation;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.OperationType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Title: ChangeUserKeyData.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 14/09/2017.
 */

@ViewScoped
@ManagedBean(name = "changeUserKey")
public class ChangeUserKeyData implements Serializable {
    //Dato del usuario
    private User userData;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;

    @PostConstruct
    public void init() {

        //Para la edicion de usuario
        String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestUsrName");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        userData = (userName != null) ? userOperation.findUser(userName) : new User();

    }

    /**
     * Metodo para el registro de un nuevo usuario en el sistema o la actualizacion de un usuario seleccionado
     * segun el tipo de operacion <tt>operation</tt>
     */
    public void updateUser() {
        userOperation.updateUser(userData);
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

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }
}
