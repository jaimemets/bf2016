package com.amsystem.bifaces.dynamictemplate.setting.model;


/**
 * Title: PropertyTemplate.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
public class PropertyTemplate {
    private Integer propertyId;
    private Integer templateId;


    public PropertyTemplate(Integer propertyId, Integer templateId) {
        this.propertyId = propertyId;
        this.templateId = templateId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

}
