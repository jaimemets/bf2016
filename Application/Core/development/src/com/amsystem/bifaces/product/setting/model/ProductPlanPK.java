package com.amsystem.bifaces.product.setting.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Title: ProductPlanPK.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 02/06/2017.
 */

@Embeddable
public class ProductPlanPK implements Serializable {
    @Column(name = "IDPLAN")
    protected Integer planId;

    @Column(name = "IDPRS")
    protected Integer productId;

    public ProductPlanPK() {
    }

    public ProductPlanPK(Integer planId, Integer productId) {
        this.planId = planId;
        this.productId = productId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
