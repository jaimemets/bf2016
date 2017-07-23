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
public class PlanTemplateLevelPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private PlanConfigBehavior planConfigBehavior;

    @ManyToOne(cascade = CascadeType.ALL)
    private Template template;

    @Column(name = "LEVEL")
    private Integer level;


    //getter and setter
    public PlanConfigBehavior getPlanConfigBehavior() {
        return planConfigBehavior;
    }

    public void setPlanConfigBehavior(PlanConfigBehavior planConfigBehavior) {
        this.planConfigBehavior = planConfigBehavior;
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
        if (!(o instanceof PlanTemplateLevelPK)) return false;

        PlanTemplateLevelPK that = (PlanTemplateLevelPK) o;

        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (planConfigBehavior != null ? !planConfigBehavior.equals(that.planConfigBehavior) : that.planConfigBehavior != null)
            return false;
        if (template != null ? !template.equals(that.template) : that.template != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planConfigBehavior != null ? planConfigBehavior.hashCode() : 0;
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
