package com.amsystem.bifaces.dynamictemplate.setting.model;

import com.amsystem.bifaces.product.setting.model.TemplatePlanLevel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: DynamicObject.java
 * @author jaguilar (JAR)
 * File Creation on 24/04/2016
 */
@Entity
@Table(name = "TEMPLATEREPOSITORY")
public class Template implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDTR")
    private Integer templateId;

    @Column(name = "IDCT", nullable = false)
    private Integer categoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PROPERTYTEMPLATE",
            joinColumns = {@JoinColumn(name = "IDTR")},
            inverseJoinColumns = {@JoinColumn(name = "IDPROPERTY", nullable = false, unique = false)})
    private Set<Property> propertySet = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.template", cascade = CascadeType.ALL)
    private Set<TemplatePlanLevel> templatePlanLevelSet = new HashSet<>(0);

    public Template() {
    }

    public Template(Integer idDynamicObject, String name) {
        this.templateId = idDynamicObject;
        this.name = name;
    }

    public Template(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateId=" + templateId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Property> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<Property> propertySet) {
        this.propertySet = propertySet;
    }

    public Set<TemplatePlanLevel> getTemplatePlanLevelSet() {
        return templatePlanLevelSet;
    }

    public void setTemplatePlanLevelSet(Set<TemplatePlanLevel> templatePlanLevelSet) {
        this.templatePlanLevelSet = templatePlanLevelSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;

        Template template = (Template) o;

        if (categoryId != null ? !categoryId.equals(template.categoryId) : template.categoryId != null) return false;
        if (name != null ? !name.equals(template.name) : template.name != null) return false;
        if (status != null ? !status.equals(template.status) : template.status != null) return false;
        if (templateId != null ? !templateId.equals(template.templateId) : template.templateId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = templateId != null ? templateId.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
