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
    @OrderBy("LEVEL")
    private Set<TemplatePlanLevel> templatePlanLevelSet = new HashSet<>();

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

    public Set<TemplatePlanLevel> getTemplatePlanLevelSet() {
        return templatePlanLevelSet;
    }

    public void setTemplatePlanLevelSet(Set<TemplatePlanLevel> templatePlanLevelSet) {
        this.templatePlanLevelSet = templatePlanLevelSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductConfigBehavior)) return false;

        ProductConfigBehavior that = (ProductConfigBehavior) o;

        if (numColumn != that.numColumn) return false;
        if (status != that.status) return false;
        if (!pcbID.equals(that.pcbID)) return false;
        if (!plan.equals(that.plan)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcbID.hashCode();
        result = 31 * result + plan.hashCode();
        result = 31 * result + status;
        result = 31 * result + numColumn;
        return result;
    }
}
