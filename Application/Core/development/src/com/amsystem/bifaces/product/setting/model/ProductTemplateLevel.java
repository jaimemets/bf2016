package com.amsystem.bifaces.product.setting.model;

import javax.persistence.*;

/**
 * Title: TemplateLevelProduct.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 28/05/2017.
 */

@Entity
@Table(name = "TEMPLATELEVELPRODUCT")
@AssociationOverrides({
        @AssociationOverride(name = "id.productConfigBehavior",
                joinColumns = @JoinColumn(name = "IDPCB")),
        @AssociationOverride(name = "id.template",
                joinColumns = @JoinColumn(name = "IDTR"))})
public class ProductTemplateLevel {

    @EmbeddedId
    private ProductTemplateLevelID id;

    private Integer level;

    private String levelDescription;

    public ProductTemplateLevelID getId() {
        return id;
    }

    public void setId(ProductTemplateLevelID id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }
}
