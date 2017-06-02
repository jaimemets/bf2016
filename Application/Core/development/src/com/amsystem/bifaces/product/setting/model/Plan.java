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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPLAN")
    private Integer planId;

    @NotEmpty
    @Column(name = "IDPRS")
    private Integer productId;


    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "IDPCB")
    ProductConfigBehavior pcBehavior;

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

    public ProductConfigBehavior getPcBehavior() {
        return pcBehavior;
    }

    public void setPcBehavior(ProductConfigBehavior pcBehavior) {
        this.pcBehavior = pcBehavior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
