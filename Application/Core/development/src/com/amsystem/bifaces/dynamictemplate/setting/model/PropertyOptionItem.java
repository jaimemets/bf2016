package com.amsystem.bifaces.dynamictemplate.setting.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Clase que representa un Item de una propiedad cuyo comportamiento este representado por un componente de tipo
 * <tt>List</tt>, <tt>Check Box List</tt> etc.
 *
 * Title: PropertyOptionItem.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 27/07/2016.
 */

@Entity
@Table(name = "PROPERTYOPTIONITEM")
public class PropertyOptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPOI")
    private Integer poiId;

    @Column(name = "VALUE")
    private float value;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPROPERTY")
    private Property property;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "propertyOptionItem")
    private Set<PropertyOptionItemLabel> itemLabels;

    public PropertyOptionItem() {
    }

    public PropertyOptionItem(float value, String description, Property property) {
        this.value = value;
        this.description = description;
        this.property = property;
    }

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Set<PropertyOptionItemLabel> getItemLabels() {
        return itemLabels;
    }

    public void setItemLabels(Set<PropertyOptionItemLabel> itemLabels) {
        this.itemLabels = itemLabels;
    }
}
