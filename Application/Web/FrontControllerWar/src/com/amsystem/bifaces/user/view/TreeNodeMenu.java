package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.util.NodeTypeMenu;

import java.io.Serializable;

/**
 * Title: TreeNodeMenu.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/05/2017.
 */


public class TreeNodeMenu implements Serializable, Comparable<TreeNodeMenu> {

    private Integer id;

    private String name;

    private NodeTypeMenu nodeTypeMenu;

    public TreeNodeMenu(Integer id, String name, NodeTypeMenu nodeTypeMenu) {
        this.id = id;
        this.name = name;
        this.nodeTypeMenu = nodeTypeMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeTypeMenu getNodeTypeMenu() {
        return nodeTypeMenu;
    }

    public void setNodeTypeMenu(NodeTypeMenu nodeTypeMenu) {
        this.nodeTypeMenu = nodeTypeMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNodeMenu)) return false;

        TreeNodeMenu that = (TreeNodeMenu) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (nodeTypeMenu != that.nodeTypeMenu) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + nodeTypeMenu.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(TreeNodeMenu o) {
        return getId().compareTo(o.getId());
    }
}

