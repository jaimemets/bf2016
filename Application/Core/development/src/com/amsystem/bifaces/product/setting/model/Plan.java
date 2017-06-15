package com.amsystem.bifaces.product.setting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title: Plan.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 26/05/2017.
 */

@Entity
@Table(name = "PLAN")
public class Plan implements Serializable {

    @Id
    @Column(name = "IDPLAN", unique = true, nullable = false)
    private Integer planId;

    @ManyToOne
    @JoinColumn(name = "IDPRS")
    private Product product;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private int status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "plan")
    ProductConfigBehavior pcBehavior;


    public Plan() {
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ProductConfigBehavior getPcBehavior() {
        return pcBehavior;
    }

    public void setPcBehavior(ProductConfigBehavior pcBehavior) {
        this.pcBehavior = pcBehavior;
    }
}
