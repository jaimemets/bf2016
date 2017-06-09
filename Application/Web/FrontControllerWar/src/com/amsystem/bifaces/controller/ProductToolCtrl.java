package com.amsystem.bifaces.controller;

import com.amsystem.bifaces.util.OperationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ResourceBundle;

/**
 * Title: ProductToolCtrl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/06/2017.
 */

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class ProductToolCtrl extends GeneralCtrl {

    private static final Logger log = LogManager.getLogger(ProductToolCtrl.class.getName());

    @Autowired
    private ResourceBundle rb;

    /**
     * Mapea la accion de edicion del producto y la Redirecciona
     * <p>Recibe como parametro la concatenacion del identificador del producto con el identificador del plan</p>
     *
     * @param idProdPlan identificador del producto y plan
     * @param model
     * @return ruta hacia la estructura del producto
     */
    @RequestMapping(value = {"/editProduct-{idProdPlan}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String idProdPlan, ModelMap model) {

        model.addAttribute("requestProdPlan", idProdPlan);
        model.addAttribute("operation", rb.getString("registryUpdate_GRL"));
        model.addAttribute("operationType", OperationType.EDIT);

        model.addAttribute("loggedinuser", getPrincipal());
        return "producttool/productGeneralConfig";


    }

}
