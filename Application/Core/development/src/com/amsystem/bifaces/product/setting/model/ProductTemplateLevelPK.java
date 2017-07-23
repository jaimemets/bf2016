package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Title: ProductTemplateLevelID.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 28/05/2017.
 */

@Embeddable
public class ProductTemplateLevelPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductConfigBehavior productConfigBehavior;

    @ManyToOne(cascade = CascadeType.ALL)
    private Template template;

    @Column(name = "LEVEL")
    private Integer level;


    //getter and setter
    public ProductConfigBehavior getProductConfigBehavior() {
        return productConfigBehavior;
    }

    public void setProductConfigBehavior(ProductConfigBehavior productConfigBehavior) {
        this.productConfigBehavior = productConfigBehavior;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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
        if (!(o instanceof ProductTemplateLevelPK)) return false;

        ProductTemplateLevelPK that = (ProductTemplateLevelPK) o;

        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (productConfigBehavior != null ? !productConfigBehavior.equals(that.productConfigBehavior) : that.productConfigBehavior != null)
            return false;
        if (template != null ? !template.equals(that.template) : that.template != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productConfigBehavior != null ? productConfigBehavior.hashCode() : 0;
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
