package com.amsystem.bifaces.translation;

import javax.persistence.*;

/**
 * Title: SystemBusinessTranslation.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@Entity
@Table(name = "UNIVERSITY")
public class SystemBusinessTranslation {
    @Id
    @GeneratedValue
    @Column(name = "ID_BPT")
    private Integer systemBptId;

    @Column(name = "COD_SBP")
    private String codSbp;

    @Column(name = "LOCALE")
    private String locale;

    @Column(name = "VALUE")
    private String value;


    public Integer getSystemBptId() {
        return systemBptId;
    }

    public void setSystemBptId(Integer systemBptId) {
        this.systemBptId = systemBptId;
    }

    public String getCodSbp() {
        return codSbp;
    }

    public void setCodSbp(String codSbp) {
        this.codSbp = codSbp;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
