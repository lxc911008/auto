package com.lxc.auto.create.model;

/**
 * 替换对象
 */
public class ReplaceModel {

    public enum ReplaceType{
        str,photo
    }

    private String id;
    private String value;
    private ReplaceType type = ReplaceType.str; //替换类型

    public ReplaceModel() {

    }

    public ReplaceModel(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public ReplaceModel(String id, String value, ReplaceType type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public ReplaceType getType() {
        return type;
    }

    public void setType(ReplaceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
