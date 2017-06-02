package com.amsystem.bifaces.systembp;

import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;
import com.amsystem.bifaces.util.SystemModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Title: SystemBPView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@ManagedBean(name = "systemBPView")
@ViewScoped
public class SystemBPView implements Serializable{

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(SystemBPView.class.getName());

    //Propiedad actual seleccionada en la tabla
    private SystemBusinessProperties selectedProp;

    //Propiedad actual seleccionada en la tabla
    private List<SystemBusinessProperties> systemBPList;

    //Propiedad actual seleccionada en la tabla
    private List<SystemBusinessProperties> systemBPFilterList;

    //Nombre de la nueva propiedad a registrar
    private String value;

    @ManagedProperty("#{systemBPOperation}")
    private SystemBPOperation service;

    @PostConstruct
    public void init() {
        systemBPList = service.systemBusinessPropertyList();
    }


    public void onRowEdit(RowEditEvent event) {
        SystemBusinessProperties systemBP = (SystemBusinessProperties) event.getObject();
        service.updateChange(systemBP);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((SystemBusinessProperties) event.getObject()).getCodSbp());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public SystemModules[] getSystemModulesList(){
        return SystemModules.values();
    }

    public SystemBusinessProperties getSelectedProp() {
        return selectedProp;
    }

    public void setSelectedProp(SystemBusinessProperties selectedProp) {
        this.selectedProp = selectedProp;
    }

    public List<SystemBusinessProperties> getSystemBPList() {
        return systemBPList;
    }

    public void setSystemBPList(List<SystemBusinessProperties> systemBPList) {
        this.systemBPList = systemBPList;
    }

    public List<SystemBusinessProperties> getSystemBPFilterList() {
        return systemBPFilterList;
    }

    public void setSystemBPFilterList(List<SystemBusinessProperties> systemBPFilterList) {
        this.systemBPFilterList = systemBPFilterList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SystemBPOperation getService() {
        return service;
    }

    public void setService(SystemBPOperation service) {
        this.service = service;
    }
}
