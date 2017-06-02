package com.amsystem.bifaces.systembp;

import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;
import com.amsystem.bifaces.systembusiness.sevices.SystemBusinessPropertyServices;
import com.amsystem.bifaces.util.MessageUtil;
import com.amsystem.bifaces.util.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Title: SystemBPCtrl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@Controller
@ViewScoped
@ManagedBean(name = "systemBPOperation")
public class SystemBPOperation implements Serializable{

    @Autowired
    private SystemBusinessPropertyServices systemBPService;

    @Autowired
    private ResourceBundle rb;



    /**
     * Busca todas las configuraciones de propiedades para el sistema y negocio
     *
     * @return Lista de propiedades asociadas al sistema y negocio
     */
    public List<SystemBusinessProperties> systemBusinessPropertyList() {
        return systemBPService.findAllSystemBP();
    }

    /**
     * Actualiza el valor de una propiedad seleccionada por el usuario
     * @param systemBP propiedad a ser actualizada
     */
    public void updateChange(SystemBusinessProperties systemBP) {
        if(systemBPService.updateSystemBP(systemBP)){
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("property_save_success_TT")) ;
        }else{
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("property_error_save_TT"));
        }
    }


    public SystemBusinessPropertyServices getSystemBPService() {
        return systemBPService;
    }

    public void setSystemBPService(SystemBusinessPropertyServices systemBPService) {
        this.systemBPService = systemBPService;
    }


}
