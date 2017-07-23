package com.amsystem.bifaces.product;

import com.amsystem.bifaces.configuration.AppRootConfig;
import com.amsystem.bifaces.configuration.HibernateConfiguration;
import com.amsystem.bifaces.configuration.PrincipalConf;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.product.setting.model.CommunicationBridge;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;
import com.amsystem.bifaces.product.setting.services.CommunicationService;
import com.amsystem.bifaces.product.setting.services.PCBService;
import com.amsystem.bifaces.util.LevelProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: PlanConfigBehaviorTest.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 20/07/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppRootConfig.class, HibernateConfiguration.class, PrincipalConf.class})
public class PlanConfigBehaviorTest {

    private static final Logger log = LogManager.getLogger(PlanConfigBehaviorTest.class.getName());

    @Autowired
    PCBService pcbService;

    @Autowired
    TemplateService templateService;

    @Autowired
    CommunicationService communicationService;


    /**
     * Agregar una plantilla en un nivel partiendo de una configuracion de un plan existente
     * Casos de prueba: Plantilla Registrada en BD, Plantilla No Registrada en BD  y Duplicar plantilla en nivel
     */
    @Test
    public void addTemplateToPlanLevel() {
        log.info("** JRA Iniciando Metodo de Prueba para agregar plantilla a nivel ***");

        ProductConfigBehavior pcb = pcbService.findProductConfigBehaviorById(Integer.valueOf(1));
        Assert.assertNotNull(pcb);

        //Plantilla Cliente Gral
        boolean flag = pcbService.addTemplateToPlanLevel(pcb, Integer.valueOf(10), Integer.valueOf(3));
        Assert.assertTrue("No se pudo agregar la plantilla al nivel del plan", flag);


    }

    /**
     * Eliminar una plantilla de un nivel partiendo de una configuracion de un plan existente
     * Casos de prueba: Plantilla asociada al nivel, plantilla no asociada al nivel
     */
    @Test
    public void removeTemplateToPlanLevel() {
        log.info("** JRA Iniciando Metodo de Prueba para agregar plantilla a nivel ***");

        ProductConfigBehavior pcb = pcbService.findProductConfigBehaviorById(Integer.valueOf(1));
        Assert.assertNotNull(pcb);

        //Plantilla Cliente Gral
        boolean flag = pcbService.deleteTemplateToPlanLevel(pcb, Integer.valueOf(10), Integer.valueOf(3));
        Assert.assertTrue("No se pudo eliminar la plantilla al nivel del plan", flag);


    }


    /**
     * Agrega un puente de comunicacion a un nivel especifico.
     * Casos de prueba: Nuevo puente de comunicacion, puente de comunicacion no registrado en BD y Puente de comunicacion
     * duplicado.
     */
    @Test
    public void addCommunicationToPlanLevel() {

        ProductConfigBehavior pcb = pcbService.findProductConfigBehaviorById(1);
        List<TemplatePlanLevel> levelList = new ArrayList<>(pcb.getTemplatePlanLevelSet());

        //Buscando una plantilla en un nivel especifico
        TemplatePlanLevel templatePlanLevel = levelList.get(LevelProduct.PRODUCT.getValue());
        Assert.assertNotNull(templatePlanLevel);

        //Cargando un puente de comunicacion existente en el sistema
        CommunicationBridge communication = communicationService.findOnlyCommunication(8);
        Assert.assertNotNull(communication);

        templatePlanLevel.getCommunicationBridgeSet().add(communication);
        pcbService.updateTemplateToPlanLevel(templatePlanLevel);

    }

    /**
     * Elimina un puente de comunicacion asociado a un nivel especifico.
     * Casos de prueba: Puente de comunicacion asociado, puente de comunicacion no registrado en BD
     */
    @Test
    public void deleteCommunicationProductLevel() {

        ProductConfigBehavior pcb = pcbService.findProductConfigBehaviorById(1);
        //Certificanco que carga el pcb
        Assert.assertNotNull(pcb);
        List<TemplatePlanLevel> levelList = new ArrayList<>(pcb.getTemplatePlanLevelSet());

        //Buscando una plantilla en un nivel especifico
        TemplatePlanLevel templatePlanLevel = levelList.get(LevelProduct.PRODUCT.getValue());
        Assert.assertNotNull(templatePlanLevel);

        //Cargando un puente de comunicacion existente en el sistema
        CommunicationBridge communication = communicationService.findOnlyCommunication(2);
        Assert.assertNotNull(communication);

        templatePlanLevel.getCommunicationBridgeSet().remove(communication);
        pcbService.updateTemplateToPlanLevel(templatePlanLevel);

    }


}
