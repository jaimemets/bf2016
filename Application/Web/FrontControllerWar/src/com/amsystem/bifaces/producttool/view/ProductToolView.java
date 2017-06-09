package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.producttool.controller.ProductToolOperation;
import com.amsystem.bifaces.util.OperationType;
import com.amsystem.bifaces.util.SymbolType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

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


    //Arbol de Menues existentes en el sistema
    private TreeNode root;

    private TreeNode selectedNode;

    //Tipo de operacion a ejecutarse
    private OperationType operation;

    @ManagedProperty("#{productToolOperation}")
    private ProductToolOperation productToolOperation;

    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    @PostConstruct
    public void init() {
        //allProduct = productToolOperation.findAllProductPlan();
        root = productToolOperation.createTreeTable();
    }

    /**
     * Concatena el identificador del producto con el plan seleccionado
     *
     * @return identificador producto plan
     */
    public String getIdProdPlan() {
        String idProd, idPlan;
        idProd = idPlan = "";

        if (selectedNode != null) {
            idProd = String.valueOf(((ProductNodeData) selectedNode.getParent().getData()).getId());
            idPlan = String.valueOf(((ProductNodeData) selectedNode.getData()).getId());
        }
        return idProd.concat(SymbolType.MINUS.getValue()).concat(idPlan);
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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
