package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

import javax.persistence.*;
import java.io.Serializable;

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
public class ProductTemplateLevel implements Serializable {

    @EmbeddedId
    ProductTemplateLevelPK pk = new ProductTemplateLevelPK();

    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    public ProductTemplateLevel() {
    }

    public ProductTemplateLevel(ProductConfigBehavior pcb, Template t, Integer level) {
        this.pk.setProductConfigBehavior(pcb);
        this.pk.setTemplate(t);
        this.level = level;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTemplateLevel)) return false;

        ProductTemplateLevel that = (ProductTemplateLevel) o;

        if (!level.equals(that.level)) return false;
        if (!pk.equals(that.pk)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        result = 31 * result + level.hashCode();
        return result;
    }
}
