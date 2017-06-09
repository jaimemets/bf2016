package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.producttool.controller.ProductToolOperation;
import com.amsystem.bifaces.util.OperationType;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Title: ProductConfigView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 06/06/2017.
 */

@ViewScoped
@ManagedBean(name = "productConfigView")
public class ProductConfigView implements Serializable {
    //Arbol estructura del producto
    private TreeNode productRoot;

    //Arbol estructura de las plantillas del sistema
    private TreeNode templateRoot;

    //Nodo del arbol del producto seleccionado
    private TreeNode selectProductNode;

    //Nodo del arbol de las plantillas seleccionado
    private TreeNode selectTemplateNode;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    @ManagedProperty("#{productToolOperation}")
    private ProductToolOperation productToolOperation;

    @PostConstruct
    public void init() {

        //Para la edicion de usuario
        String idProdPlan = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestProdPlan");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");


        //Se construye la estructura de arbol del producto y la estructura inicial de arbol de plantillas
        productRoot = productToolOperation.createProductConfigTree(idProdPlan);
        templateRoot = productToolOperation.createTemplateTree();

    }

    public TreeNode getProductRoot() {
        return productRoot;
    }

    /**
     * Recarga el arbol de plantillas cada ve que se selecciona un nodo del arbol de producto
     *
     * @param event nodo seleccioado
     */
    public void onNodeSelect(NodeSelectEvent event) {
        productToolOperation.loadTemplateByCategory(getOnlyChild(), event.getTreeNode());
    }

    private TreeNode getOnlyChild() {
        return templateRoot.getChildren().get(0);
    }

    public void setProductRoot(TreeNode productRoot) {
        this.productRoot = productRoot;
    }

    public TreeNode getTemplateRoot() {
        return templateRoot;
    }

    public void setTemplateRoot(TreeNode templateRoot) {
        this.templateRoot = templateRoot;
    }

    public TreeNode getSelectProductNode() {
        return selectProductNode;
    }

    public void setSelectProductNode(TreeNode selectProductNode) {
        this.selectProductNode = selectProductNode;
    }

    public TreeNode getSelectTemplateNode() {
        return selectTemplateNode;
    }

    public void setSelectTemplateNode(TreeNode selectTemplateNode) {
        this.selectTemplateNode = selectTemplateNode;
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
