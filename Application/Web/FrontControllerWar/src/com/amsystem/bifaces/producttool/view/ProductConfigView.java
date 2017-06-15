package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.producttool.controller.ProductToolOperation;
import com.amsystem.bifaces.util.OperationType;
import com.amsystem.bifaces.util.SymbolType;
import com.amsystem.bifaces.util.TreeNodeType;
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

    private String idProdPlan;

    Plan plan;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    @ManagedProperty("#{productToolOperation}")
    private ProductToolOperation productToolOperation;

    @PostConstruct
    public void init() {

        //Para la edicion de usuario
        idProdPlan = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestProdPlan");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        int idPlan = Integer.valueOf(idProdPlan.split(SymbolType.MINUS.getValue())[1]);
        plan = productToolOperation.findPlanConfigById(idPlan);

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
        if (event.getTreeNode().getType().equalsIgnoreCase(TreeNodeType.PARENT.getLabel()))
            productToolOperation.loadTemplateByCategory(getOnlyChild(), event.getTreeNode());
    }

    public void associateProductTemplate() {
        productToolOperation.addChildProductTree(selectProductNode, selectTemplateNode, plan);
    }

    public void disassociateProductTemplate() {
        productToolOperation.removeChildProductTree(selectProductNode, plan);
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

    public String getIdProdPlan() {
        return idProdPlan;
    }

    public void setIdProdPlan(String idProdPlan) {
        this.idProdPlan = idProdPlan;
    }

    public ProductToolOperation getProductToolOperation() {
        return productToolOperation;
    }

    public void setProductToolOperation(ProductToolOperation productToolOperation) {
        this.productToolOperation = productToolOperation;
    }
}
