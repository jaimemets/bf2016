package com.amsystem.bifaces.producttool.controller;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.services.PlanService;
import com.amsystem.bifaces.product.setting.services.ProductService;
import com.amsystem.bifaces.producttool.view.TreeNodeData;
import com.amsystem.bifaces.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    private TemplateService templateService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ResourceBundle rb;


    /**
     * Construye la estructura tabla de arbol para mostrar los prodcutos con sus planes
     *
     * @return
     */
    public TreeNode createTreeTable() {
        log.debug("**Inicio Arbol Productos Planes **");
        TreeNode root = new DefaultTreeNode(new TreeNodeData(-1, null, null, TreeNodeType.ROOT), null);
        List<Product> productList = productService.findAllProductPlan();
        // Set<Plan> planSet = null;
        TreeNode parentNode;
        TreeNode childNodeItem;
        TreeNodeData dataNode;


        for (Product pr : productList) {
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new TreeNodeData(pr.getProductId(), pr.getName(), pr.getStatus(), TreeNodeType.PARENT), root);
            log.debug("Producto: " + pr.getName());
            if (!pr.getPlanSet().isEmpty()) {
                Plan plan;
                Iterator<Plan> planIterator = pr.getPlanSet().iterator();

                while (planIterator.hasNext()) {
                    plan = planIterator.next();
                    log.debug("Plan: " + plan.getName());
                    dataNode = new TreeNodeData(plan.getPlanId(), plan.getName(), plan.getStatus(), TreeNodeType.CHILD);
                    childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, parentNode);

                }
            }
        }
        return root;
    }

    /**
     * Construye la estructura de arbol para el producto con sus niveles
     *
     * @param idProdPlan Identificador oncatenado del producto con el plan
     * @return arbol de producto
     */
    public TreeNode createProductConfigTree(String idProdPlan) {
        log.debug("**Inicio Arbol Estructura de Producto **");
        int idProduct = Integer.valueOf(idProdPlan.split(SymbolType.MINUS.getValue())[0]);
        int idPlan = Integer.valueOf(idProdPlan.split(SymbolType.MINUS.getValue())[1]);
        Plan plan = null;
        ProductConfigBehavior pcBehavior = null;
        List<ProductTemplateLevel> productTemplateLevelList = null;

        Product product = productService.findProductPlanById(idPlan, idProduct);
        plan = product.getPlanSet().iterator().next();
        pcBehavior = plan.getPcBehavior();
        productTemplateLevelList = new ArrayList<>(pcBehavior.getProductTemplateLevelSet());


        TreeNode productRoot = new DefaultTreeNode(new TreeNodeData(product.getProductId(), null, null, TreeNodeType.ROOT), null);
        LevelProduct[] levelProducts = LevelProduct.values();
        TreeNode parentNode;
        TreeNode childNodeItem;
        TreeNodeData dataNode;


        for (LevelProduct lp : levelProducts) {
            log.debug("Nivel: " + lp.getLabel());
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new TreeNodeData(lp.getValue(), lp.getLabel(), 1, TreeNodeType.PARENT), productRoot);

            for (ProductTemplateLevel ptl : productTemplateLevelList) {
                if (lp.getValue() == ptl.getLevel()) {
                    dataNode = new TreeNodeData(ptl.getTemplate().getTemplateId(), ptl.getTemplate().getName(), ptl.getTemplate().getStatus(), TreeNodeType.CHILD);
                    childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, parentNode);
                }
            }

        }
        return productRoot;
    }

    /**
     * Construye la estructura inicial de arbol para las plantillas
     *
     * @return arbol de plantillas con un solo nodo
     */
    public TreeNode createTemplateTree() {
        log.debug("**Inicio Arbol Estructura de Plantillas **");
        TreeNode templateRoot = new DefaultTreeNode(new TreeNodeData(-1, null, null, TreeNodeType.ROOT), null);
        TreeNode parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new TreeNodeData(0, "Plantillas", 1, TreeNodeType.PARENT), templateRoot);
        return templateRoot;
    }

    /**
     * Recarga el arbol de plantillas segun el nodo seleccionado en el arbol de productos.
     * <p>Las plantillas que se cargan corresponde a la relacion entre identificadores de <tt>TemplateCategory</tt> con
     * <tt>LevelProduct</tt>
     * </p>
     *
     * @param templateRoot nodo en el que se agregaran las plantillas obtenidas
     * @param nodeSelect   nodo seleccionado perteneciente al arbol de producto
     */
    public void loadTemplateByCategory(TreeNode templateRoot, TreeNode nodeSelect) {

        TreeNodeData dataNode;
        TreeNode childNodeItem;
        Integer nodeId = ((TreeNodeData) nodeSelect.getData()).getId();
        TemplateCategory templateCategory = TemplateCategory.valueOf(nodeId);
        List<Template> allTemplateByCategory = templateService.findAllTemplateByCategory(templateCategory);

        if (templateRoot.getChildCount() > 0) {
            templateRoot.getChildren().clear();
        }

        for (Template template : allTemplateByCategory) {
            dataNode = new TreeNodeData(template.getTemplateId(), template.getName(), template.getStatus(), TreeNodeType.CHILD);
            childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, templateRoot);
        }

    }

    /**
     * @param rootProductNode
     * @param selectedTemplateNode
     * @param plan
     */
    public void addChildProductTree(TreeNode rootProductNode, TreeNode selectedTemplateNode, Plan plan) {
        TreeNode childNodeItem;
        TreeNodeData templateNode = (TreeNodeData) selectedTemplateNode.getData();
        TreeNodeData rootNodeData = (TreeNodeData) rootProductNode.getData();

        if (rootProductNode.getChildren().isEmpty()) { //Validacion para solo una plantilla por nivel

            if (planService.addTemplateConfigurationLevel(plan, templateNode.getId(), rootNodeData.getId())) {
                MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("template_save_success_TT"));
            } else {
                MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_duplicate_TT"));
            }
        } else {
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_duplicate_TT"));
        }

    }

    /**
     * @param selectedNode
     * @param plan
     */
    public void removeChildProductTree(TreeNode selectedNode, Plan plan) {
        TreeNodeData templateNode = (TreeNodeData) selectedNode.getData();
        TreeNodeData rootNodeData = (TreeNodeData) selectedNode.getParent().getData();

        if (planService.deleteTemplateConfigurationLevel(plan, templateNode.getId(), rootNodeData.getId())) {
            TreeNode parent = selectedNode.getParent();
            parent.getChildren().remove(selectedNode);
        }


    }

    /**
     *
     * @param idPlan
     * @return
     */
    public Plan findPlanConfigById(int idPlan) {
        return planService.findPlanById(idPlan);
    }
}
