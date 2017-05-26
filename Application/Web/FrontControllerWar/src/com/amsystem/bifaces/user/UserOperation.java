package com.amsystem.bifaces.user;

import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserProfileService;
import com.amsystem.bifaces.user.service.UserService;
import com.amsystem.bifaces.util.ComponentOperation;
import com.amsystem.bifaces.util.MessageUtil;
import com.amsystem.bifaces.util.NotificationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.ResourceBundle;

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
public class UserOperation {

    private static final Logger log = LogManager.getLogger(UserOperation.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

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

    /**
     *
     * @return
     */
    public List<User> loadAllUsers() {
        return userService.findAllUsers();
    }

    /**
     *
     * @return
     */
    public List<UserProfile> allProfile() {
        return userProfileService.findAll();
    }
}
