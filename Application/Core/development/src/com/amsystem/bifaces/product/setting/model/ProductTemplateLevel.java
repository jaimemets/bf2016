package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

import javax.persistence.*;

/**
 * Title: TemplateLevelProduct.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 28/05/2017.
 */

@Entity
@Table(name = "TEMPLATELEVELPRODUCT")
@AssociationOverrides({
        @AssociationOverride(name = "ptlPK.productConfigBehavior",
                joinColumns = @JoinColumn(name = "IDPCB")),
        @AssociationOverride(name = "ptlPK.template",
                joinColumns = @JoinColumn(name = "IDTR"))})
public class ProductTemplateLevel {

    @EmbeddedId
    private ProductTemplateLevelPK ptlPK;

    private Integer level;

    private String levelDescription;

    public ProductTemplateLevelPK getPtlPK() {
        return ptlPK;
    }

    public void setPtlPK(ProductTemplateLevelPK ptlPK) {
        this.ptlPK = ptlPK;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    @Transient
    public ProductConfigBehavior getProductConfigBehavior() {
        return getPtlPK().getProductConfigBehavior();
    }

    public void setProductConfigBehavior(ProductConfigBehavior pcb) {
        getPtlPK().setProductConfigBehavior(pcb);
    }

    @Transient
    public Template getTemplate() {
        return getPtlPK().getTemplate();
    }

    public void setTemplate(Template template) {
        getPtlPK().setTemplate(template);
    }


}
