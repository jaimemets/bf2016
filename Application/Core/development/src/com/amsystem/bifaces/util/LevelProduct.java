package com.amsystem.bifaces.util;

/**
 * Title: LevelProduct.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/06/2017.
 */
public enum LevelProduct {
    PRODUCT(1, "label_PR_GRL"),
    POLICY(2, "label_POL_GRL"),
    RISK_UNIT(3, "label_RU_GRL"),
    INSURANCE_OBJECT(4, "label_IO_GRL"),
    COVERAGE(5, "label_COV_GRL"),
    PARTICIPATION(6, "label_CL_GRL"),
    SUMMARY(7, "label_SMR_GRL");

    private int value;
    private String label;

    LevelProduct(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public static LevelProduct valueOf(int value) {
        for (LevelProduct lp : values()) {
            if (lp.getValue() == value) {
                return lp;
            }
        }
        return null;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
