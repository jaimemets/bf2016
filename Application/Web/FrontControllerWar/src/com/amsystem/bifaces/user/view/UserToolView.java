package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserOperation;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.OperationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Title: UserToolView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/12/2016.
 */

@ViewScoped
@ManagedBean(name = "userToolView")
public class UserToolView implements Serializable {

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(UserToolView.class.getName());

    //Lista de todos los usuarios en el sistema
    private List<User> allUsers;

    //Propiedad actual seleccionada en la tabla
    private User selectedUser;

    //Tipo de operacion a ejecutarse
    private OperationType operation;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;

    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    @PostConstruct
    public void init() {
        allUsers = userOperation.loadAllUsers();
    }


    /**
     * Elimina un usuario del sistema
     */
    public void deleteUser() {
        userOperation.deleteUser(selectedUser);
        allUsers = userOperation.loadAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }


}
