package com.amsystem.bifaces.dynamictemplate.setting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: Property.java
 *
 * @author Jaime Aguilar (JAR)
 * File Creation on 03/04/2016
 */

@Entity
@Table(name = "PROPERTY")
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROPERTY")
    private Integer propertyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "MASK")
    private String mask;

    @Column(name = "FORMULA")
    private String formula;

    @Column(name = "DEFAULTVALUE")
    private String defaultValue;

    @Column(name = "EXPRESSIONVALIDATOR")
    private String expressionValidator;

    @Column(name = "PROPERTYTYPE")
    private Integer type;

    @Column(name = "RENDERINGTYPE")
    private Integer renderingType;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "EDITABLE")
    private boolean editable;

    @Column(name = "REQUIRED")
    private boolean required;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PARENT")
    private Property parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "property")
    private Set<PropertyOptionItem> propertyOptionItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Property> propertySet = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "propertySet")
    private Set<Template> templateSet = new HashSet<>();


    public Property() { }

    public Property(Integer propertyId, String name){
        this.propertyId = propertyId;
        this.name = name;
    }

    public Property(String name, String label, String mask, String formula, String defaultValue,
                    String expressionValidator, Integer type, Integer renderingType, boolean visible,
                    boolean editable, boolean required) {
        this.name = name;
        this.label = label;
        this.mask = mask;
        this.formula = formula;
        this.defaultValue = defaultValue;
        this.expressionValidator = expressionValidator;
        this.type = type;
        this.renderingType = renderingType;
        this.visible = visible;
        this.editable = editable;
        this.required = required;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getExpressionValidator() {
        return expressionValidator;
    }

    public void setExpressionValidator(String expressionValidator) {
        this.expressionValidator = expressionValidator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRenderingType() {
        return renderingType;
    }

    public void setRenderingType(Integer renderingType) {
        this.renderingType = renderingType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Property getParent() {
        return parent;
    }

    public void setParent(Property parent) {
        this.parent = parent;
    }

    public Set<PropertyOptionItem> getPropertyOptionItems() {
        return propertyOptionItems;
    }

    public void setPropertyOptionItems(Set<PropertyOptionItem> propertyOptionItems) {
        this.propertyOptionItems = propertyOptionItems;
    }

    public Set<Property> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<Property> propertySet) {
        this.propertySet = propertySet;
    }

    public Set<Template> getTemplateSet() {
        return templateSet;
    }

    public void setTemplateSet(Set<Template> templateSet) {
        this.templateSet = templateSet;
    }
}
