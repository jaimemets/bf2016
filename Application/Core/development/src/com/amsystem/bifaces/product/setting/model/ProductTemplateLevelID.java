package com.amsystem.bifaces.product.setting.model;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

import javax.persistence.CascadeType;
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
public class ProductTemplateLevelID implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductConfigBehavior productConfigBehavior;

    @ManyToOne(cascade = CascadeType.ALL)
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
}
