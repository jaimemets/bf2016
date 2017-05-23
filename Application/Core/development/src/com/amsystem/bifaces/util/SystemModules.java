package com.amsystem.bifaces.util;

/**
 * Title: SystemModules.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 23/05/2017.
 */


public enum SystemModules {

    PRODUCT_TOOL(1, "PT"),
    TEMPLATE_TOOL(2, "TT"),
    USER_TOOL(3, "UST"),
    SBP_TOOL(4, "SBPT");

    private int value;
    private String cod;

    SystemModules(int value, String cod) {
        this.value = value;
        this.cod = cod;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public static SystemModules valueOf(int value) {
        for (SystemModules sm : values()) {
            if (sm.getValue() == value) {
                return sm;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SystemModules{" +
                "value=" + value +
                ", cod='" + cod + '\'' +
                '}';
    }
}
