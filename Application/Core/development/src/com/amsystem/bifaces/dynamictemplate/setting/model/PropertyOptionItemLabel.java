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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOCLE")
    private String locale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPOI", nullable = false)
    private PropertyOptionItem propertyOptionItem;

    public PropertyOptionItemLabel() {
    }

    public PropertyOptionItemLabel(String description, String locale, PropertyOptionItem propertyOptionItem) {
        this.description = description;
        this.locale = locale;
        this.propertyOptionItem = propertyOptionItem;
    }

    public Integer getPoilId() {
        return poilId;
    }

    public void setPoilId(Integer poilId) {
        this.poilId = poilId;
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

    public PropertyOptionItem getPropertyOptionItem() {
        return propertyOptionItem;
    }

    public void setPropertyOptionItem(PropertyOptionItem propertyOptionItem) {
        this.propertyOptionItem = propertyOptionItem;
    }
}
