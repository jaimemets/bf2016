package com.amsystem.bifaces.product;

import com.amsystem.bifaces.configuration.AppRootConfig;
import com.amsystem.bifaces.configuration.HibernateConfiguration;
import com.amsystem.bifaces.configuration.PrincipalConf;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.product.setting.services.CommunicationService;
import com.amsystem.bifaces.product.setting.services.PCBService;
import com.amsystem.bifaces.product.setting.services.PlanService;
import com.amsystem.bifaces.product.setting.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Title: ProductDaoTest.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/06/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppRootConfig.class, HibernateConfiguration.class, PrincipalConf.class})
public class PlanDaoTest {

    private static final Logger log = LogManager.getLogger(PlanDaoTest.class.getName());

    @Autowired
    ProductService productService;

    @Autowired
    PlanService planService;

    @Autowired
    PCBService pcbService;

    @Autowired
    CommunicationService communicationService;

    @Autowired
    TemplateService templateService;


    /**
     *
     */
    @Test
    public void loadProductById() {
        Product product = productService.findProductAllPlanById(Integer.valueOf(1));
        //Certificanco que carga el producto
        Assert.assertNotNull(product);

        //Certificando la inicializacion de los planes asociados al prodcuto
        for (Plan plan : product.getPlanSet()) {
            Assert.assertNotNull(plan);
        }
    }


}
