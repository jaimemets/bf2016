package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: TemplateLevelProduct.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 28/05/2017.
 */

@Entity
@Table(name = "PRODUCTTEMPLATELEVEL")
@AssociationOverrides({
        @AssociationOverride(name = "pk.productConfigBehavior",
                joinColumns = @JoinColumn(name = "IDPCB")),
        @AssociationOverride(name = "pk.template",
                joinColumns = @JoinColumn(name = "IDTR"))})
public class TemplatePlanLevel implements Serializable {

    @EmbeddedId
    ProductTemplateLevelPK pk = new ProductTemplateLevelPK();

    @Column(name = "NUM_COLUMN")
    private Integer numColumn;

    @Column(name = "COMMUNICATION")
    private Integer communicationType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COMMUNICATIONPRODUCTLEVEL",
            joinColumns = {@JoinColumn(name = "LEVEL"), @JoinColumn(name = "IDPCB"), @JoinColumn(name = "IDTR")},
            inverseJoinColumns = {@JoinColumn(name = "IDCB")})
    private Set<CommunicationBridge> communicationBridgeSet = new HashSet<>(0);


    public TemplatePlanLevel() {
    }

    public TemplatePlanLevel(ProductConfigBehavior pcb, Template t, Integer level, Integer numColumn, Integer communicationType) {
        this.pk.setProductConfigBehavior(pcb);
        this.pk.setTemplate(t);
        this.pk.setLevel(level);
        this.numColumn = numColumn;
        this.communicationType = communicationType;
    }

    public ProductTemplateLevelPK getPk() {
        return pk;
    }

    public void setPk(ProductTemplateLevelPK pk) {
        this.pk = pk;
    }

    @Transient
    public ProductConfigBehavior getProductConfigBehavior() {
        return getPk().getProductConfigBehavior();
    }

    public void setProductConfigBehavior(ProductConfigBehavior pcb) {
        getPk().setProductConfigBehavior(pcb);
    }

    @Transient
    public Template getTemplate() {
        return getPk().getTemplate();
    }

    public void setTemplate(Template t) {
        getPk().setTemplate(t);
    }

    @Transient
    public Integer getLevel() {
        return getPk().getLevel();
    }

    public void setLevel(Integer level) {
        getPk().setLevel(level);
    }

    public Integer getNumColumn() {
        return numColumn;
    }

    public void setNumColumn(Integer numColumn) {
        this.numColumn = numColumn;
    }

    public Integer getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(Integer communicationType) {
        this.communicationType = communicationType;
    }

    public Set<CommunicationBridge> getCommunicationBridgeSet() {
        return communicationBridgeSet;
    }

    public void setCommunicationBridgeSet(Set<CommunicationBridge> communicationBridgeSet) {
        this.communicationBridgeSet = communicationBridgeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemplatePlanLevel)) return false;

        TemplatePlanLevel that = (TemplatePlanLevel) o;

        if (communicationType != null ? !communicationType.equals(that.communicationType) : that.communicationType != null)
            return false;
        if (numColumn != null ? !numColumn.equals(that.numColumn) : that.numColumn != null) return false;
        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (numColumn != null ? numColumn.hashCode() : 0);
        result = 31 * result + (communicationType != null ? communicationType.hashCode() : 0);
        return result;
    }
}
