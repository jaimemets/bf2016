package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.product.setting.model.CommunicationBridge;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.PlanConfigBehavior;
import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;
import com.amsystem.bifaces.producttool.controller.ProductToolOperation;
import com.amsystem.bifaces.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Title: ProductConfigView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 06/06/2017.
 */

@ViewScoped
@ManagedBean(name = "productConfigView")
public class ProductConfigView implements Serializable {

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(ProductConfigView.class.getName());

    //Arbol estructura del producto
    private TreeNode productRoot;

    //Arbol estructura de las plantillas del sistema
    private TreeNode templateRoot;

    //Nodo del arbol del producto seleccionado
    private TreeNode selectProductNode;

    //Nodo del arbol de las plantillas seleccionado
    private TreeNode selectTemplateNode;

    //Nivel del producto seleccionado
    private LevelProduct selectLevel;

    //Lista de niveles con plantillas asociadas
    private List<LevelProduct> configuredProductLevel;

    //Numero de columna configuradas en el nivel
    private int numColumnLevel;

    //Tipo de comunicacion configurada en el nivel (NA, WS, PROC)
    private String communicationType;

    //Web Service seleccionado
    private String selectWS;

    //Numero de parametros maximos permitidos para el Ws seleccionado
    private int parameterWS;

    //Procedimiento seleccionado
    private CommunicationBridge selectPROC;

    //Numero de parametros maximos permitidos para el PROC seleccionado
    private int parameterPROC;

    //Lista de Web Service Disponibles
    private List<CommunicationBridge> sourceCBWSList;

    //Lista de Web Service Disponibles
    private List<CommunicationBridge> targetCBWSList;

    //Lista de Procedimientos Disponibles
    private List<CommunicationBridge> sourceCBPROCList;

    //Lista de Procedimientos Disponibles
    private List<CommunicationBridge> targetCBPROCList;

    //Listado de los servicios web disponibles y configurados
    private DualListModel<CommunicationBridge> communicationBridgeWs;

    //Listado de los procedimientos disponibles y configurados
    private DualListModel<CommunicationBridge> communicationBridgeProc;

    //Lista de propiedades disponibles para el WS seleccionado
    private List<Property> sourcePROPWSList;

    //Lista de propiedades configuradas para el WS seleccionado
    private List<Property> targetPROPWSList;

    //Lista de propiedades disponibles para el Proc seleccionado
    private List<Property> sourcePROPRList;

    //Lista de propiedades configuradas para el Proc seleccionado
    private List<Property> targetPROPRList;

    //Lista de propiedades de todas las plantillas asociadas al producto disponibles y configurdas
    private DualListModel<Property> propertiesWs;

    //Lista de propiedades de todas las plantillas asociadas al producto disponibles y configurdas
    private DualListModel<Property> propertiesProc;

    //Plan del producto seleccionado
    private PlanConfigBehavior planConfigBehavior;

    //Identificador concatenado del producto y plan (PROD-PLAN)
    private String idProdPlan;

    //Tipo de operacion en ejecucion
    private OperationType operation;

    //Nivel cargado en la vista
    private TemplatePlanLevel templatePlanLevel;

    private HashMap<String, CommunicationBridge> communicationBridgeMap;

    private HashMap<Integer, CommunicationBridge> cbProcMap;

    @ManagedProperty("#{productToolOperation}")
    private ProductToolOperation productToolOperation;

    @PostConstruct
    public void init() {

        //Para la edicion de usuario
        idProdPlan = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("requestProdPlan");
        operation = (OperationType) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("operationType");
        int idPlan = Integer.valueOf(idProdPlan.split(SymbolType.MINUS.getValue())[1]);
        Plan plan = productToolOperation.findPlanById(idPlan);
        planConfigBehavior = productToolOperation.findPlanConfigBehaviorById(plan.getPcBehavior().getPcbID());

        //Se construye la estructura de arbol del producto y la estructura inicial de arbol de plantillas
        productRoot = productToolOperation.createProductConfigTree(plan);
        templateRoot = productToolOperation.createTemplateTree();


        selectLevel = LevelProduct.PRODUCT; //Nivel seleccionado para cargar por defecto
        communicationType = CommunicationType.NOT_ANY.getLabel();
        targetCBWSList = new ArrayList<>();
        targetCBPROCList = new ArrayList<>();
        communicationBridgeMap = new HashMap<>();


        templatePlanLevel = productToolOperation.findProductTemplateLevel(planConfigBehavior.getTemplatePlanLevelSet(), selectLevel.getValue());
        if (templatePlanLevel != null) {
            numColumnLevel = templatePlanLevel.getNumColumn();
            communicationType = CommunicationType.valueOf(templatePlanLevel.getCommunicationType()).getLabel();
            productToolOperation.loadSettingCommunicationList(templatePlanLevel, targetCBWSList, targetCBPROCList);
        }

        //Cargando lista de WS y Proc
        sourceCBWSList = new ArrayList<>();
        sourceCBPROCList = new ArrayList<>();
        productToolOperation.loadCommunicationList(communicationBridgeMap, sourceCBWSList, sourceCBPROCList, targetCBWSList, targetCBPROCList);

        communicationBridgeWs = new DualListModel<>(sourceCBWSList, targetCBWSList);
        communicationBridgeProc = new DualListModel<>(sourceCBPROCList, targetCBPROCList);

        List<String> propertiesSource = new ArrayList<>();
        List<String> propertiesTarget = new ArrayList<>();


        //propertiesSource = productToolOperation.findTemplatePropertyAssociateList(plan.getPcBehavior());
        // properties = new DualListModel<>(propertiesSource, propertiesTarget);


    }

    /**
     * Busca el primer nodo del arbol de plantillas
     *
     * @return primer nodo
     */
    private TreeNode getOnlyChild() {
        return templateRoot.getChildren().get(0);
    }


    /**
     * Recarga el arbol de plantillas cada ve que se selecciona un nodo del arbol de producto
     * @param event nodo seleccioado
     */
    public void onNodeSelect(NodeSelectEvent event) {
        if (event.getTreeNode().getType().equalsIgnoreCase(TreeNodeType.PARENT.getLabel()))
            productToolOperation.loadTemplateByCategory(getOnlyChild(), event.getTreeNode());
    }

    /**
     * Asocia un plantilla al nivel del arbol seleccionado
     */
    public void associateProductTemplate() {
        productToolOperation.addChildProductTree(selectProductNode, selectTemplateNode, planConfigBehavior);
    }

    /**
     * Desasocia la plantilla del nivel seleccionado
     */
    public void disassociateProductTemplate() {
        productToolOperation.removeChildProductTree(selectProductNode, planConfigBehavior);
    }

    /**
     *
     */
    public void saveProductTemplateLevel() {

        templatePlanLevel.setCommunicationType(CommunicationType.stringValueOf(communicationType).getValue());
        templatePlanLevel.setNumColumn(numColumnLevel);
        productToolOperation.saveUpdateProductLevel(planConfigBehavior, templatePlanLevel, targetCBWSList);

    }

    /**
     * Busca todos los niveles que componen el arbol de producto
     *
     * @return
     */
    public List<LevelProduct> getLevelProductList() {
        return productToolOperation.getConfiguredProductLevel(planConfigBehavior.getTemplatePlanLevelSet());

        //return plan.getPcBehavior().getProductTemplateLevelSet();
    }


    /**
     * Recarga la configuracion segun el nivel seleccionado
     * <p>Por defecto aparece visualizado en primera instancia el nivel del producto</p>
     */
    public void onLevelProductChange() {
        targetCBWSList.clear();
        targetCBPROCList.clear();
        sourceCBWSList.clear();
        sourceCBPROCList.clear();
        templatePlanLevel = null;
        templatePlanLevel = productToolOperation.findProductTemplateLevel(planConfigBehavior.getTemplatePlanLevelSet(), selectLevel.getValue());
        if (templatePlanLevel != null) {
            numColumnLevel = templatePlanLevel.getNumColumn();
            communicationType = CommunicationType.valueOf(templatePlanLevel.getCommunicationType()).getLabel();
            productToolOperation.loadSettingCommunicationList(templatePlanLevel, targetCBWSList, targetCBPROCList);
        }

        productToolOperation.loadCommunicationList(communicationBridgeMap, sourceCBWSList, sourceCBPROCList, targetCBWSList, targetCBPROCList);

        communicationBridgeWs = new DualListModel<>(sourceCBWSList, targetCBWSList);
        communicationBridgeProc = new DualListModel<>(sourceCBPROCList, targetCBPROCList);
    }

    /**
     * Carga los parametros configurados por cada <tt>CommunicationBridge</tt> WS seleccionado
     */
    public void onWSChange() {
        //og.debug("ID componente: " + event.getComponent().getId());
        if (selectWS != null && !selectWS.isEmpty()) {
            CommunicationBridge communicationBridge = getCommunicationById(selectWS.toString());
            parameterWS = communicationBridge.getNumParameter();
        } else {
            parameterWS = 0;
        }
    }

    /**
     * Carga los parametros configurados por cada <tt>CommunicationBridge</tt> Proc seleccionado
     */
    public void onPROChange() {
        if (selectPROC != null) {
            CommunicationBridge communicationBridge = getCommunicationById(selectPROC.toString());
            parameterPROC = communicationBridge.getNumParameter();
        } else {
            parameterPROC = 0;
        }
    }

    /**
     * Busca el objeto <tt>CommunicationBridge</tt> por el Id compuesto recibido
     *
     * @param cbID Identificador compuesto por el ID del objeto concatenado con la categoria (WS o Proc)
     * @return
     */
    private CommunicationBridge getCommunicationById(String cbID) {
        //cbID = cbID.substring(1,cbID.length()-1);
        return communicationBridgeMap.get(cbID.trim());
    }

    /**
     * Actualiza la lista de Comunicaciones disponible de WS o Proc.
     *
     * @param event
     */
    public void onTransferWS(TransferEvent event) {
        log.debug("Transf WS...");
        targetCBWSList.clear();
        targetCBPROCList.clear();
        CommunicationBridge communicationBridge;
        String strTarget = communicationBridgeWs.getTarget().toString();
        strTarget = strTarget.substring(1, strTarget.length() - 1);
        if (!strTarget.isEmpty()) {
            String[] split = strTarget.split(",");
            for (String cb : split) {
                log.debug("CB : " + cb);
                communicationBridge = communicationBridgeMap.get(cb.trim());
                if (communicationBridge != null)//Identificar el componente WS o Proc
                    targetCBWSList.add(communicationBridge);
                //else{communicationBridge != null && comparar con el id del componente de Proc}
                //targetCBPROCList.add(communicationBridge);
            }

        }
        log.debug("List target : " + targetCBWSList.size());
    }


    // getter and setter


    public TreeNode getProductRoot() {
        return productRoot;
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

    public LevelProduct getSelectLevel() {
        return selectLevel;
    }

    public void setSelectLevel(LevelProduct selectLevel) {
        this.selectLevel = selectLevel;
    }

    public int getNumColumnLevel() {
        return numColumnLevel;
    }

    public void setNumColumnLevel(int numColumnLevel) {
        this.numColumnLevel = numColumnLevel;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getSelectWS() {
        return selectWS;
    }

    public void setSelectWS(String selectWS) {
        this.selectWS = selectWS;
    }

    public int getParameterWS() {
        return parameterWS;
    }

    public void setParameterWS(int parameterWS) {
        this.parameterWS = parameterWS;
    }

    public CommunicationBridge getSelectPROC() {
        return selectPROC;
    }

    public void setSelectPROC(CommunicationBridge selectPROC) {
        this.selectPROC = selectPROC;
    }

    public int getParameterPROC() {
        return parameterPROC;
    }

    public void setParameterPROC(int parameterPROC) {
        this.parameterPROC = parameterPROC;
    }

    public List<CommunicationBridge> getSourceCBWSList() {
        return sourceCBWSList;
    }

    public void setSourceCBWSList(List<CommunicationBridge> sourceCBWSList) {
        this.sourceCBWSList = sourceCBWSList;
    }

    public List<CommunicationBridge> getTargetCBWSList() {
        return targetCBWSList;
    }

    public void setTargetCBWSList(List<CommunicationBridge> targetCBWSList) {
        this.targetCBWSList = targetCBWSList;
    }

    public List<CommunicationBridge> getSourceCBPROCList() {
        return sourceCBPROCList;
    }

    public void setSourceCBPROCList(List<CommunicationBridge> sourceCBPROCList) {
        this.sourceCBPROCList = sourceCBPROCList;
    }

    public List<CommunicationBridge> getTargetCBPROCList() {
        return targetCBPROCList;
    }

    public void setTargetCBPROCList(List<CommunicationBridge> targetCBPROCList) {
        this.targetCBPROCList = targetCBPROCList;
    }

    public DualListModel<CommunicationBridge> getCommunicationBridgeWs() {
        return communicationBridgeWs;
    }

    public void setCommunicationBridgeWs(DualListModel<CommunicationBridge> communicationBridgeWs) {
        this.communicationBridgeWs = communicationBridgeWs;
    }

    public DualListModel<CommunicationBridge> getCommunicationBridgeProc() {
        return communicationBridgeProc;
    }

    public void setCommunicationBridgeProc(DualListModel<CommunicationBridge> communicationBridgeProc) {
        this.communicationBridgeProc = communicationBridgeProc;
    }

    public List<Property> getSourcePROPWSList() {
        return sourcePROPWSList;
    }

    public void setSourcePROPWSList(List<Property> sourcePROPWSList) {
        this.sourcePROPWSList = sourcePROPWSList;
    }

    public List<Property> getTargetPROPWSList() {
        return targetPROPWSList;
    }

    public void setTargetPROPWSList(List<Property> targetPROPWSList) {
        this.targetPROPWSList = targetPROPWSList;
    }

    public List<Property> getSourcePROPRList() {
        return sourcePROPRList;
    }

    public void setSourcePROPRList(List<Property> sourcePROPRList) {
        this.sourcePROPRList = sourcePROPRList;
    }

    public List<Property> getTargetPROPRList() {
        return targetPROPRList;
    }

    public void setTargetPROPRList(List<Property> targetPROPRList) {
        this.targetPROPRList = targetPROPRList;
    }

    public DualListModel<Property> getPropertiesWs() {
        return propertiesWs;
    }

    public void setPropertiesWs(DualListModel<Property> propertiesWs) {
        this.propertiesWs = propertiesWs;
    }

    public DualListModel<Property> getPropertiesProc() {
        return propertiesProc;
    }

    public void setPropertiesProc(DualListModel<Property> propertiesProc) {
        this.propertiesProc = propertiesProc;
    }

    public PlanConfigBehavior getPlanConfigBehavior() {
        return planConfigBehavior;
    }

    public void setPlanConfigBehavior(PlanConfigBehavior planConfigBehavior) {
        this.planConfigBehavior = planConfigBehavior;
    }

    public String getIdProdPlan() {
        return idProdPlan;
    }

    public void setIdProdPlan(String idProdPlan) {
        this.idProdPlan = idProdPlan;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public TemplatePlanLevel getTemplatePlanLevel() {
        return templatePlanLevel;
    }

    public void setTemplatePlanLevel(TemplatePlanLevel templatePlanLevel) {
        this.templatePlanLevel = templatePlanLevel;
    }

    public ProductToolOperation getProductToolOperation() {
        return productToolOperation;
    }

    public void setProductToolOperation(ProductToolOperation productToolOperation) {
        this.productToolOperation = productToolOperation;
    }
}
