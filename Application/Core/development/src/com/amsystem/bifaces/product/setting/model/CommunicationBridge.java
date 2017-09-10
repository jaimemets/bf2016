package com.amsystem.bifaces.product.setting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: CommunicationService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */

@Entity
@Table(name = "COMMUNICATIONBRIDGE")
public class CommunicationBridge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCB")
    private Integer cbId;

    @Column(name = "CATEGORY")
    private Integer category;

    @Column(name = "NUM_PARAMETER")
    private Integer numParameter;

    @Column(name = "TYPE")
    private Integer type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.communicationBridge", cascade = CascadeType.ALL)
    private Set<PropertyCommunicationLevel> propertyCommunicationLevelSet = new HashSet<>();

    public CommunicationBridge() {
    }

    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getNumParameter() {
        return numParameter;
    }

    public void setNumParameter(Integer numParameter) {
        this.numParameter = numParameter;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PropertyCommunicationLevel> getPropertyCommunicationLevelSet() {
        return propertyCommunicationLevelSet;
    }

    public void setPropertyCommunicationLevelSet(Set<PropertyCommunicationLevel> propertyCommunicationLevelSet) {
        this.propertyCommunicationLevelSet = propertyCommunicationLevelSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommunicationBridge)) return false;

        CommunicationBridge that = (CommunicationBridge) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (cbId != null ? !cbId.equals(that.cbId) : that.cbId != null) return false;
        if (numParameter != null ? !numParameter.equals(that.numParameter) : that.numParameter != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cbId != null ? cbId.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (numParameter != null ? numParameter.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
