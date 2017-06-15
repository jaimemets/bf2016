package com.amsystem.bifaces.product.setting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: ProductConfigBehavior.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 26/05/2017.
 */

@Entity
@Table(name = "PRODUCTCONFIGBEHAVIOR")
public class ProductConfigBehavior implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPCB")
    private Integer pcbID;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Plan plan;

    @Column(name = "STATUS", nullable = false)
    private int status;

    @Column(name = "NUM_COLUMN", nullable = false)
    private int numColumn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.productConfigBehavior", cascade = CascadeType.ALL)
    private Set<ProductTemplateLevel> productTemplateLevelSet = new HashSet<>();

    public Integer getPcbID() {
        return pcbID;
    }

    public void setPcbID(Integer pcbID) {
        this.pcbID = pcbID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumColumn() {
        return numColumn;
    }

    public void setNumColumn(int numColumn) {
        this.numColumn = numColumn;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Set<ProductTemplateLevel> getProductTemplateLevelSet() {
        return productTemplateLevelSet;
    }

    public void setProductTemplateLevelSet(Set<ProductTemplateLevel> productTemplateLevelSet) {
        this.productTemplateLevelSet = productTemplateLevelSet;
    }
}
