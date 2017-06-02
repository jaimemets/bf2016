package com.amsystem.bifaces.dynamictemplate.setting.model;

import com.amsystem.bifaces.product.setting.model.ProductTemplateLevel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDTR")
    private Integer templateId;

    @Column(name = "IDCT", nullable = false)
    private Integer categoryId;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS")
    private Integer status;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PROPERTYTEMPLATE",
            joinColumns = {@JoinColumn(name = "IDTR")},
            inverseJoinColumns = {@JoinColumn(name = "IDPROPERTY")})
    private List<Property> propertyList;

    @OneToMany(mappedBy = "id.template",
            cascade = CascadeType.ALL)
    private Set<ProductTemplateLevel> productTemplateLevelSet = new HashSet<>();

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

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public Set<ProductTemplateLevel> getProductTemplateLevelSet() {
        return productTemplateLevelSet;
    }

    public void setProductTemplateLevelSet(Set<ProductTemplateLevel> productTemplateLevelSet) {
        this.productTemplateLevelSet = productTemplateLevelSet;
    }
}
