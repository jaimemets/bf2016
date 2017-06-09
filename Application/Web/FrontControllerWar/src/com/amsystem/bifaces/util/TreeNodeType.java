package com.amsystem.bifaces.util;

/**
 * Title: TreeNodeType.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 02/06/2017.
 */


public enum TreeNodeType {
    ROOT(0, "ROOT"),
    PARENT(1, "PA"),
    CHILD(2, "CH");

    private int value;
    private String label;

    private TreeNodeType(int value, String label) {
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


    public static TreeNodeType valueOf(int value) {
        for (TreeNodeType tnt : values()) {
            if (tnt.getValue() == value) {
                return tnt;
            }
        }
        return null;
    }
}
