package com.amsystem.bifaces.util;

/**
 * Title: NodeTypeMenu.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */


public enum NodeTypeMenu {
    ROOT(0, "ROOT"),
    MENU(1, "MEN"),
    SUB_MENU(2, "SB"),
    ITEM(2, "ITEM");

    private int value;
    private String label;

    private NodeTypeMenu(int value, String label) {
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


    public static NodeTypeMenu valueOf(int value) {
        for (NodeTypeMenu ntm : values()) {
            if (ntm.getValue() == value) {
                return ntm;
            }
        }
        return null;
    }
}
