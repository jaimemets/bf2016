package com.amsystem.bifaces.product.setting.model;

import javax.persistence.*;
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
public class ProductConfigBehavior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPCB")
    private Integer pcbID;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "NUM_COLUMN")
    private Integer numColumn;

    @OneToMany(mappedBy = "ptlPK.productConfigBehavior",
            cascade = CascadeType.ALL)
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

    public Integer getNumColumn() {
        return numColumn;
    }

    public void setNumColumn(Integer numColumn) {
        this.numColumn = numColumn;
    }

    public Set<ProductTemplateLevel> getProductTemplateLevelSet() {
        return productTemplateLevelSet;
    }

    public void setProductTemplateLevelSet(Set<ProductTemplateLevel> productTemplateLevelSet) {
        this.productTemplateLevelSet = productTemplateLevelSet;
    }
}
