package com.amsystem.bifaces.producttool.controller;

import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.product.setting.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Title: ProductToolOperation.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 01/06/2017.
 */

@Controller
@ViewScoped
@ManagedBean(name = "productToolOperation")
public class ProductToolOperation implements Serializable {

    private static final Logger log = LogManager.getLogger(ProductToolOperation.class.getName());

    @Autowired
    private ProductService productService;

    @Autowired
    private ResourceBundle rb;


    public List<Product> findAllProduct() {
        return productService.findAllProduct();
    }


}
