package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Property;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title: PropertyCommunicationLevel.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 09/07/2017.
 */

@Entity
@Table(name = "PROPERTY_COMMUNICATION_LEVEL")
@AssociationOverrides({
        @AssociationOverride(name = "pk.communicationBridge",
                joinColumns = @JoinColumn(name = "IDCB")),
        @AssociationOverride(name = "pk.property",
                joinColumns = @JoinColumn(name = "IDPROPERTY")),
        @AssociationOverride(name = "pk.planTemplateLevelPK.planConfigBehavior",
                joinColumns = @JoinColumn(name = "IDPCB")),
        @AssociationOverride(name = "pk.planTemplateLevelPK.template",
                joinColumns = @JoinColumn(name = "IDTR")),
        @AssociationOverride(name = "pk.planTemplateLevelPK.level",
                joinColumns = @JoinColumn(name = "LEVEL"))})
public class PropertyCommunicationLevel implements Serializable, Comparable<PropertyCommunicationLevel> {
    @EmbeddedId
    PropertyCommunicationLevelPK pk = new PropertyCommunicationLevelPK();


    public PropertyCommunicationLevel() {
    }

    public PropertyCommunicationLevel(PropertyCommunicationLevelPK pk) {
        this.pk = pk;
    }

    @Override
    public int compareTo(PropertyCommunicationLevel o) {
        return (this.pk.getCommunicationBridge().getCbId().intValue() == o.pk.getCommunicationBridge().getCbId().intValue()
                && this.pk.getProperty().getPropertyId().intValue() == o.pk.getProperty().getPropertyId().intValue()
                && this.pk.getPlanTemplateLevelPK().getPlanConfigBehavior().getPcbID().intValue() == o.pk.getPlanTemplateLevelPK().getPlanConfigBehavior().getPcbID().intValue()
                && this.pk.getPlanTemplateLevelPK().getTemplate().getTemplateId().intValue() == o.pk.getPlanTemplateLevelPK().getTemplate().getTemplateId().intValue()
                && this.pk.getPlanTemplateLevelPK().getLevel().intValue() == o.pk.getPlanTemplateLevelPK().getLevel().intValue()) ? 1 : -1;
    }

    public PropertyCommunicationLevelPK getPk() {
        return pk;
    }

    public void setPk(PropertyCommunicationLevelPK pk) {
        this.pk = pk;
    }

    @Transient
    public Property getProperty() {
        return getPk().getProperty();
    }

    public void setProperty(Property property) {
        getPk().setProperty(property);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyCommunicationLevel)) return false;

        PropertyCommunicationLevel that = (PropertyCommunicationLevel) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
