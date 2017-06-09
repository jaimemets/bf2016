package com.amsystem.bifaces.producttool.controller;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.product.setting.model.ProductConfigBehavior;
import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import com.amsystem.bifaces.product.setting.services.ProductService;
import com.amsystem.bifaces.producttool.view.ProductNodeData;
import com.amsystem.bifaces.util.LevelProduct;
import com.amsystem.bifaces.util.SymbolType;
import com.amsystem.bifaces.util.TemplateCategory;
import com.amsystem.bifaces.util.TreeNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

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
    private ResourceBundle rb;


    /**
     * Construye la estructura tabla de arbol para mostrar los prodcutos con sus planes
     *
     * @return
     */
    public TreeNode createTreeTable() {
        log.debug("**Inicio Arbol Productos Planes **");
        TreeNode root = new DefaultTreeNode(new ProductNodeData(-1, null, null, TreeNodeType.ROOT), null);
        List<Product> productList = productService.findAllProductPlan();
        Set<Plan> planSet = null;
        TreeNode parentNode;
        TreeNode childNodeItem;
        ProductNodeData dataNode;


        for (Product pr : productList) {
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new ProductNodeData(pr.getProductId(), pr.getName(), pr.getStatus(), TreeNodeType.PARENT), root);
            log.debug("Producto: " + pr.getName());
            if (!pr.getPlanSet().isEmpty()) {
                Plan plan;
                Iterator<Plan> planIterator = pr.getPlanSet().iterator();

                while (planIterator.hasNext()) {
                    plan = planIterator.next();
                    log.debug("Plan: " + plan.getName());
                    dataNode = new ProductNodeData(plan.getProductPlanPK().getPlanId(), plan.getName(), plan.getStatus(), TreeNodeType.CHILD);
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
        boolean foundPlan = false;
        Plan plan = null;
        ProductConfigBehavior pcBehavior = null;
        List<ProductTemplateLevel> productTemplateLevelList = null;


        Product product = productService.findById(Integer.valueOf(idProduct));
        Iterator<Plan> planIterator = product.getPlanSet().iterator();
        while (planIterator.hasNext() && !foundPlan) {
            plan = planIterator.next();
            if (plan.getProductPlanPK().getPlanId() == idPlan) foundPlan = true;

        }
        if (plan != null)
            pcBehavior = plan.getPcBehavior();

        if (pcBehavior != null)
            productTemplateLevelList = new ArrayList<>(pcBehavior.getProductTemplateLevelSet());


        TreeNode productRoot = new DefaultTreeNode(new ProductNodeData(product.getProductId(), null, null, TreeNodeType.ROOT), null);
        //List<Product> productList = ;
        LevelProduct[] levelProducts = LevelProduct.values();
        //Set<Plan> planSet = null;
        TreeNode parentNode;
        TreeNode childNodeItem;
        ProductNodeData dataNode;


        for (LevelProduct lp : levelProducts) {
            log.debug("Nivel: " + lp.getLabel());
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new ProductNodeData(lp.getValue(), lp.getLabel(), 1, TreeNodeType.PARENT), productRoot);
            /*
            if (!pr.getPlanSet().isEmpty()) {
                Plan plan;
                Iterator<Plan> planIterator = pr.getPlanSet().iterator();

                while (planIterator.hasNext()) {
                    plan = planIterator.next();
                    log.debug("Plan: " + plan.getName());
                    dataNode = new ProductNodeData(plan.getProductPlanPK().getPlanId(), plan.getName(), plan.getStatus(), TreeNodeType.CHILD);
                    childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, parentNode);

                }
            }
            */
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
        TreeNode templateRoot = new DefaultTreeNode(new ProductNodeData(-1, null, null, TreeNodeType.ROOT), null);
        TreeNode parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new ProductNodeData(0, "Plantillas", 1, TreeNodeType.PARENT), templateRoot);

        /*
        //Product product = productService.findById(Integer.valueOf(idProduct));
        //List<Product> productList = ;
        TemplateCategory[] templateCategories = TemplateCategory.values();
        List<Template> allTemplate = templateService.findAllTemplate();
        //Set<Plan> planSet = null;
        TreeNode parentNode;
        TreeNode childNodeItem;
        ProductNodeData dataNode;
        List<Template> templatesAdded = new ArrayList<>();


        for (TemplateCategory lp : templateCategories) {
            log.debug("Nivel: " + lp.getLabel());
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new ProductNodeData(lp.getValue(), lp.getLabel(), 1, TreeNodeType.PARENT), templateRoot);

            log.debug("Size allTemplate: " + allTemplate.size());
            for (Template template : allTemplate){
                if(template.getCategoryId() == lp.getValue()){
                    dataNode = new ProductNodeData(template.getTemplateId(), template.getName(), template.getStatus(), TreeNodeType.CHILD);
                    childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, parentNode);
                    templatesAdded.add(template);
                }
            }

            if (!templatesAdded.isEmpty()){
                allTemplate.removeAll(templatesAdded);
                templatesAdded.clear();
            }
        }
        */
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

        TreeNode childNodeItem;
        ProductNodeData dataNode;
        Integer nodeId = ((ProductNodeData) nodeSelect.getData()).getId();
        TemplateCategory templateCategory = TemplateCategory.valueOf(nodeId);

        List<Template> allTemplateByCategory = templateService.findAllTemplateByCategory(templateCategory);

        if (templateRoot.getChildCount() > 0) {
            templateRoot.getChildren().clear();
        }

        for (Template template : allTemplateByCategory) {
            //if(template.getCategoryId() == lp.getValue()){
            dataNode = new ProductNodeData(template.getTemplateId(), template.getName(), template.getStatus(), TreeNodeType.CHILD);
            childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, templateRoot);
            //  templatesAdded.add(template);
            //}
        }

    }
}
