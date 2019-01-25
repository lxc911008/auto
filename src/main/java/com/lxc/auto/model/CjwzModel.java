package com.lxc.auto.model;

/**
 * 创建网站Model对象
 */
public class CjwzModel {

    private String path; //文件路径
    private String zjName; //主机名称
    private String wjjName; //文件夹名称

    public String getPath() {

        //斜杠转换
        if(path != null){
            path = path.replaceAll("\\/","\\\\");
        }

        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getZjName() {
        return zjName;
    }

    public void setZjName(String zjName) {
        this.zjName = zjName;
    }

    public String getWjjName() {
        return wjjName;
    }

    public void setWjjName(String wjjName) {
        this.wjjName = wjjName;
    }

    @Override
    public String toString() {
        return "CjwzModel{" +
                "path='" + path + '\'' +
                ", zjName='" + zjName + '\'' +
                ", wjjName='" + wjjName + '\'' +
                '}';
    }
}
