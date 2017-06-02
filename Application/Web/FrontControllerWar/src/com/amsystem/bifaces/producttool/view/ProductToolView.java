package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.producttool.controller.ProductToolOperation;
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
 * Title: ProductToolView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 01/06/2017.
 */

@ViewScoped
@ManagedBean(name = "productToolView")
public class ProductToolView implements Serializable {

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(ProductToolView.class.getName());

    //Lista de todos los usuarios en el sistema
    private List<Product> allProduct;

    //Propiedad actual seleccionada en la tabla
    private Product selectedProduct;

    //Tipo de operacion a ejecutarse
    private OperationType operation;

    @ManagedProperty("#{productToolOperation}")
    private ProductToolOperation productToolOperation;

    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    @PostConstruct
    public void init() {
        allProduct = productToolOperation.findAllProduct();
    }

    public List<Product> getAllProduct() {
        return allProduct;
    }

    public void setAllProduct(List<Product> allProduct) {
        this.allProduct = allProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public ProductToolOperation getProductToolOperation() {
        return productToolOperation;
    }

    public void setProductToolOperation(ProductToolOperation productToolOperation) {
        this.productToolOperation = productToolOperation;
    }
}
