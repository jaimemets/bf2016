package com.amsystem.bifaces.producttool.view;

import com.amsystem.bifaces.util.TreeNodeType;

import java.io.Serializable;

/**
 * Title: TreeNodeData.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 02/06/2017.
 */


public class TreeNodeData implements Serializable {

    private Integer id;

    private String name;

    private Integer status;

    private TreeNodeType treeNodeType;

    public TreeNodeData(Integer id, String name, Integer status, TreeNodeType treeNodeType) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.treeNodeType = treeNodeType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TreeNodeType getTreeNodeType() {
        return treeNodeType;
    }

    public void setTreeNodeType(TreeNodeType treeNodeType) {
        this.treeNodeType = treeNodeType;
    }
}
