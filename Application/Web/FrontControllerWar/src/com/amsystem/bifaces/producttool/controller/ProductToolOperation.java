package com.amsystem.bifaces.producttool.controller;

import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.dynamictemplate.setting.services.TemplateService;
import com.amsystem.bifaces.product.setting.model.*;
import com.amsystem.bifaces.product.setting.services.CommunicationService;
import com.amsystem.bifaces.product.setting.services.PCBService;
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
    private PlanService planService;

    @Autowired
    private PCBService pcbService;

    @Autowired
    private CommunicationService communicationService;

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
     * @return arbol de producto
     */
    public TreeNode createProductConfigTree(Plan plan) {
        log.debug("**Inicio Arbol Estructura de Producto **");
        ProductConfigBehavior pcBehavior = null;
        List<ProductTemplateLevel> productTemplateLevelList = null;

        pcBehavior = pcbService.findProductConfigBehaviorById(plan.getPcBehavior().getPcbID());
        plan.setPcBehavior(pcBehavior);

        TreeNode productRoot = new DefaultTreeNode(new TreeNodeData(plan.getProduct().getProductId(), null, null, TreeNodeType.ROOT), null);
        LevelProduct[] levelProducts = LevelProduct.values();
        TreeNode parentNode;
        TreeNode childNodeItem;
        TreeNodeData dataNode;
        Iterator<ProductTemplateLevel> templateLevelIterator;

        for (LevelProduct lp : levelProducts) {
            log.debug("Nivel: " + lp.getLabel());
            parentNode = new DefaultTreeNode(TreeNodeType.PARENT.getLabel(), new TreeNodeData(lp.getValue(), rb.getString(lp.getLabel()), 1, TreeNodeType.PARENT), productRoot);

            if (!pcBehavior.getProductTemplateLevelSet().isEmpty()) {
                templateLevelIterator = pcBehavior.getProductTemplateLevelSet().iterator();
                ProductTemplateLevel ptl;
                while (templateLevelIterator.hasNext()) {
                    ptl = templateLevelIterator.next();
                    if (lp.getValue() == ptl.getLevel()) {
                        dataNode = new TreeNodeData(ptl.getTemplate().getTemplateId(), ptl.getTemplate().getName(), ptl.getTemplate().getStatus(), TreeNodeType.CHILD);
                        childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), dataNode, parentNode);
                    }
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
                childNodeItem = new DefaultTreeNode(TreeNodeType.CHILD.getLabel(), templateNode, rootProductNode);
            } else {
                MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_duplicate_TT"));
            }
        } else {
            MessageUtil.showMessage(NotificationType.ERROR, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), rb.getString("template_duplicate_TT"));
        }

    }

    /**
     * Elimina la asociacion de la plantilla con el nivel del producto
     *
     * @param selectedNode
     * @param plan
     */
    public void removeChildProductTree(TreeNode selectedNode, Plan plan) {
        TreeNodeData templateNode = (TreeNodeData) selectedNode.getData();
        TreeNodeData rootNodeData = (TreeNodeData) selectedNode.getParent().getData();

        //TODO: Validar si existen registro de negocios. Bastaria con consultar algun registro en la tabla POLICYSTATIC
        //TODO: para el producto y plan seleccionado.

        if (planService.deleteTemplateConfigurationLevel(plan, templateNode.getId(), rootNodeData.getId())) {
            TreeNode parent = selectedNode.getParent();
            parent.getChildren().remove(selectedNode);
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.INFO.getLabel().concat("_GRL")), rb.getString("template_deleted_success_TT"));
        } else {
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), "ERROR");
        }

    }

    /**
     * Carga el Plan seleccionado
     *
     * @param idPlan
     * @return
     */
    public Plan findPlanConfigById(int idPlan) {
        return planService.findPlanById(idPlan);
    }

    /**
     * Busca la configuracion del nivel seleccionado
     *
     * @param productTemplateLevelSet
     * @param level
     * @return
     */
    public ProductTemplateLevel findProductTemplateLevel(Set<ProductTemplateLevel> productTemplateLevelSet, int level) {
        boolean found = false;
        ProductTemplateLevel productTemplateLevel = null;
        log.debug("Buscando nivel..." + level);

        if (!productTemplateLevelSet.isEmpty()) {
            Iterator<ProductTemplateLevel> templateLevelIterator = productTemplateLevelSet.iterator();

            while (templateLevelIterator.hasNext() && !found) {
                productTemplateLevel = templateLevelIterator.next();
                found = (productTemplateLevel.getLevel().intValue() == level);
            }
        }

        return productTemplateLevel;
    }

    public List<LevelProduct> getConfiguredProductLevel(Set<ProductTemplateLevel> productTemplateLevelSet) {
        List<LevelProduct> configuredProductLevel = new ArrayList<>();
        ProductTemplateLevel productTemplateLevel;
        if (!productTemplateLevelSet.isEmpty()) {
            Iterator<ProductTemplateLevel> templateLevelIterator = productTemplateLevelSet.iterator();

            while (templateLevelIterator.hasNext()) {
                productTemplateLevel = templateLevelIterator.next();
                LevelProduct levelProduct = LevelProduct.valueOf(productTemplateLevel.getLevel());
                if (levelProduct != null)
                    configuredProductLevel.add(levelProduct);
            }
        }

        return configuredProductLevel;
    }

    /**
     * @param pcBehavior
     * @return
     */
    public List<String> findTemplatePropertyAssociateList(ProductConfigBehavior pcBehavior) {
        List<String> propertiesName = new ArrayList<>();
        if (!pcBehavior.getProductTemplateLevelSet().isEmpty()) {
            ProductTemplateLevel productTemplateLevel;
            List<Integer> idTemplateList = new ArrayList<>();
            Iterator<ProductTemplateLevel> templateLevelIterator = pcBehavior.getProductTemplateLevelSet().iterator();
            while (templateLevelIterator.hasNext()) {
                productTemplateLevel = templateLevelIterator.next();
                Template template = productTemplateLevel.getTemplate();
                log.debug("Template : " + template.getName());
                idTemplateList.add(template.getTemplateId());
                template = templateService.findHibTemplatePropertiesByName(template.getName());
                Iterator<Property> propertyIterator = template.getPropertySet().iterator();
                while (propertyIterator.hasNext()) {
                    Property property = propertyIterator.next();
                    propertiesName.add(property.getName());
                }
            }
        }
        propertiesName.add("PropTest");
        return propertiesName;
    }

    /**
     * Carga las listas de comunicaciones disponibles (WS y Procedimientos) segun la configuracion existente en el nivel seleccionado
     *
     * @param communicationBridgeMap
     * @param sourceCBWSList
     * @param sourceCBPROCList
     * @param wsList
     * @param procList
     */
    public void loadCommunicationList(HashMap<String, CommunicationBridge> communicationBridgeMap, List<CommunicationBridge> sourceCBWSList,
                                      List<CommunicationBridge> sourceCBPROCList, List<CommunicationBridge> wsList, List<CommunicationBridge> procList) {


        List<CommunicationBridge> bridgeList = communicationService.findAllCommunicationBridge();

        List<Integer> idWsList = new ArrayList<>();
        List<Integer> idProcList = new ArrayList<>();
        for (CommunicationBridge cb : wsList) {
            idWsList.add(cb.getCbId());
        }

        for (CommunicationBridge cb : procList) {
            idProcList.add(cb.getCbId());
        }

        for (CommunicationBridge communicationBridge : bridgeList) {
            if (communicationBridge.getCategory() == CommunicationType.PROCEDURE.getValue() && !idProcList.contains(communicationBridge.getCbId())) {
                sourceCBPROCList.add(communicationBridge);
            } else if (communicationBridge.getCategory() == CommunicationType.WEB_SERVICE.getValue() && !idWsList.contains(communicationBridge.getCbId())) {
                sourceCBWSList.add(communicationBridge);
            }
            communicationBridgeMap.put(communicationBridge.getCbId().toString().concat(communicationBridge.getCategory().toString()), communicationBridge);
        }

    }

    /**
     * Carga las listas de comunicaciones configuradas (WS y Procedimientos) para el nivel seleccionado
     *
     * @param productTemplateLevel
     * @param targetCBWSList
     * @param targetCBPROCList
     */
    public void loadSettingCommunicationList(ProductTemplateLevel productTemplateLevel, List<CommunicationBridge> targetCBWSList, List<CommunicationBridge> targetCBPROCList) {

        if (productTemplateLevel.getCommunicationBridgeSet() != null) {
            Iterator<CommunicationBridge> bridgeIterator = productTemplateLevel.getCommunicationBridgeSet().iterator();
            CommunicationBridge communicationBridge;
            while (bridgeIterator.hasNext()) {
                communicationBridge = bridgeIterator.next();
                if (communicationBridge.getCategory() == CommunicationType.PROCEDURE.getValue()) {
                    targetCBPROCList.add(communicationBridge);
                } else {
                    targetCBWSList.add(communicationBridge);
                }
            }

        }
    }

    /**
     * Actualiza los datos para el nivel de producto seleccionado
     *
     * @param plan
     * @param productTemplateLevel
     * @param targetCBWSList
     */
    public void saveUpdateProductLevel(Plan plan, ProductTemplateLevel productTemplateLevel, List<CommunicationBridge> targetCBWSList) {
        boolean found = false;
/*
        for(CommunicationBridge cb : targetCBWSList){
            CommunicationBridge onlyCommunication = communicationService.findOnlyCommunication(cb.getCbId());
            onlyCommunication.getProductTemplateLevelSet().add(productTemplateLevel);
            cb.setProductTemplateLevelSet(onlyCommunication.getProductTemplateLevelSet());

        }
*/
        Set<CommunicationBridge> cbSet = new HashSet<>(targetCBWSList);
        productTemplateLevel.setCommunicationBridgeSet(cbSet);

        if (pcbService.updateProductTemplateLevel(productTemplateLevel)) {
            ProductConfigBehavior pcBehavior = plan.getPcBehavior();

            Iterator<ProductTemplateLevel> levelIterator = pcBehavior.getProductTemplateLevelSet().iterator();
            ProductTemplateLevel templateLevel;
            while (levelIterator.hasNext() && !found) {
                templateLevel = levelIterator.next();
                if (templateLevel.getPk().equals(productTemplateLevel.getPk())) {
                    log.debug("Actualizando objeto ProductTemplateLevel");
                    pcBehavior.getProductTemplateLevelSet().remove(templateLevel);
                    pcBehavior.getProductTemplateLevelSet().add(productTemplateLevel);
                    found = true;
                }
            }

        } else {
            MessageUtil.showMessage(NotificationType.INFO, rb.getString(NotificationType.ERROR.getLabel().concat("_GRL")), "ERROR");
        }

    }
}
