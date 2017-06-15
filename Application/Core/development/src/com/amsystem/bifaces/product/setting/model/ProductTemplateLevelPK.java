package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

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

    @ManyToOne
    private ProductConfigBehavior productConfigBehavior;

    @ManyToOne
    private Template template;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTemplateLevelPK)) return false;

        ProductTemplateLevelPK that = (ProductTemplateLevelPK) o;

        if (!productConfigBehavior.equals(that.productConfigBehavior)) return false;
        if (!template.equals(that.template)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productConfigBehavior.hashCode();
        result = 31 * result + template.hashCode();
        return result;
    }
}
