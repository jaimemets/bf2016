package com.amsystem.bifaces.product.setting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Title: Plan.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 26/05/2017.
 */

@Entity
@Table(name = "PLAN")
public class Plan {

    @EmbeddedId
    private ProductPlanPK productPlanPK;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @OneToOne
    @JoinColumn(name = "IDPCB")
    ProductConfigBehavior pcBehavior;

    public ProductPlanPK getProductPlanPK() {
        return productPlanPK;
    }

    public void setProductPlanPK(ProductPlanPK productPlanPK) {
        this.productPlanPK = productPlanPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProductConfigBehavior getPcBehavior() {
        return pcBehavior;
    }

    public void setPcBehavior(ProductConfigBehavior pcBehavior) {
        this.pcBehavior = pcBehavior;
    }

}
