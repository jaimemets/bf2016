package com.amsystem.bifaces.dynamictemplate.setting.model;

import javax.persistence.*;

/**
 * Title: PropertyItemLabel.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 18/04/2017.
 */

@Entity
@Table(name="PROPERTYOPTIONITEMLABEL")
public class PropertyOptionItemLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPOIL")
    private Integer poilId;

    private Integer poiId;

    private Integer propertyId;

    private String description;

    private String locale;

    public PropertyOptionItemLabel(Integer poilId, Integer poiId, Integer propertyId, String description, String locale) {
        this.poilId = poilId;
        this.poiId = poiId;
        this.propertyId = propertyId;
        this.description = description;
        this.locale = locale;
    }

    public PropertyOptionItemLabel(Integer poiId, Integer propertyId, String description, String locale) {
        this.poiId = poiId;
        this.propertyId = propertyId;
        this.description = description;
        this.locale = locale;
    }

    public Integer getPoilId() {
        return poilId;
    }

    public void setPoilId(Integer poilId) {
        this.poilId = poilId;
    }

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
