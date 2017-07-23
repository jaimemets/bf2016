package com.amsystem.bifaces.product.setting.model;

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
        @AssociationOverride(name = "pk.productTemplateLevelPK.productConfigBehavior",
                joinColumns = @JoinColumn(name = "IDPCB")),
        @AssociationOverride(name = "pk.productTemplateLevelPK.template",
                joinColumns = @JoinColumn(name = "IDTR")),
        @AssociationOverride(name = "pk.productTemplateLevelPK.level",
                joinColumns = @JoinColumn(name = "LEVEL"))})
public class PropertyCommunicationLevel implements Serializable {
    @EmbeddedId
    PropertyCommunicationLevelPK pk = new PropertyCommunicationLevelPK();

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
