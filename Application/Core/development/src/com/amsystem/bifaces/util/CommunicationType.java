package com.amsystem.bifaces.util;

/**
 * Title: CoomunicationType.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */


public enum CommunicationType {
    NOT_ANY(0, "NA"),
    WEB_SERVICE(1, "WS"),
    PROCEDURE(2, "PRC");

    private int value;
    private String label;

    private CommunicationType(int value, String label) {
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

    public void setLabel(String label) {
        this.label = label;
    }


    public static CommunicationType valueOf(int value) {
        for (CommunicationType ct : values()) {
            if (ct.getValue() == value) {
                return ct;
            }
        }
        return null;
    }

    public static CommunicationType stringValueOf(String value) {
        for (CommunicationType ct : values()) {
            if (ct.getLabel().equalsIgnoreCase(value)) {
                return ct;
            }
        }
        return null;
    }
}
