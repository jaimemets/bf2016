package com.amsystem.bifaces.util;

/**
 * Title: LevelProduct.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 07/06/2017.
 */


public enum LevelProduct {
    PRODUCT(1, "lpPR"),
    POLICY(2, "lpPOL"),
    RISK_UNIT(3, "lpRU"),
    INSURANCE_OBJECT(4, "lpIO"),
    COVERAGE(5, "lpCOV"),
    PARTICIPATION(6, "lpCL"),
    SUMMARY(7, "lpSMR");

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
