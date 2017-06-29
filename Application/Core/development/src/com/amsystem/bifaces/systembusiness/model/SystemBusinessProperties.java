package com.amsystem.bifaces.systembusiness.model;

import com.amsystem.bifaces.translation.SystemBusinessTranslation;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Title: SystemBusinessProperties.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@Entity
@Table(name="SYSTEMBUSINESSPROPERTY")
public class SystemBusinessProperties {

    @Id
    @Column(name = "COD_SBP")
    private String codSbp;

    @NotEmpty
    @Column(name = "COD_SM")
    private String codSm;

    @NotEmpty
    @Column(name = "DESCRIPTION")
    private String description;


    @Column(name = "VALUE", nullable = true)
    private String value;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="COD_SBP")
    private Set<SystemBusinessTranslation> language;

    public String getCodSbp() {
        return codSbp;
    }

    public void setCodSbp(String codSbp) {
        this.codSbp = codSbp;
    }

    public String getCodSm() {
        return codSm;
    }

    public void setCodSm(String codSm) {
        this.codSm = codSm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<SystemBusinessTranslation> getLanguage() {
        return language;
    }

    public void setLanguage(Set<SystemBusinessTranslation> language) {
        this.language = language;
    }
}
