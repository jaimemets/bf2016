package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Property;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Title: PropertyCommunicationLevelPK.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/07/2017.
 */

@Embeddable
public class PropertyCommunicationLevelPK implements Serializable {

    @ManyToOne
    private CommunicationBridge communicationBridge;

    @ManyToOne
    private Property property;

    private PlanTemplateLevelPK planTemplateLevelPK;


    public PropertyCommunicationLevelPK() {
    }

    public PropertyCommunicationLevelPK(CommunicationBridge communicationBridge, Property property, PlanTemplateLevelPK planTemplateLevelPK) {
        this.communicationBridge = communicationBridge;
        this.property = property;
        this.planTemplateLevelPK = planTemplateLevelPK;
    }

    public CommunicationBridge getCommunicationBridge() {
        return communicationBridge;
    }

    public void setCommunicationBridge(CommunicationBridge communicationBridge) {
        this.communicationBridge = communicationBridge;
    }

    public PlanTemplateLevelPK getPlanTemplateLevelPK() {
        return planTemplateLevelPK;
    }

    public void setPlanTemplateLevelPK(PlanTemplateLevelPK planTemplateLevelPK) {
        this.planTemplateLevelPK = planTemplateLevelPK;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyCommunicationLevelPK)) return false;

        PropertyCommunicationLevelPK that = (PropertyCommunicationLevelPK) o;

        if (communicationBridge != null ? !communicationBridge.equals(that.communicationBridge) : that.communicationBridge != null)
            return false;
        if (planTemplateLevelPK != null ? !planTemplateLevelPK.equals(that.planTemplateLevelPK) : that.planTemplateLevelPK != null)
            return false;
        if (property != null ? !property.equals(that.property) : that.property != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = communicationBridge != null ? communicationBridge.hashCode() : 0;
        result = 31 * result + (planTemplateLevelPK != null ? planTemplateLevelPK.hashCode() : 0);
        result = 31 * result + (property != null ? property.hashCode() : 0);
        return result;
    }
}
