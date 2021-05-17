package com.imooc.oa.entity;

public class Node {

    private Long nodeId;
    private int nodeType;
    private String nodeName;
    private String url;
    private int nodeCode;
    private Long parentId;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(int nodeCode) {
        this.nodeCode = nodeCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
