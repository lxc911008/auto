package com.lxc.auto.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.util.StringUtils;

/**
 * 主机名称对象
 */
public class ZjModel {

    private static final String fgf = ".";

    private String zjtm; //主机头
    private String zjhm; //主机后面名称

    public ZjModel(){

    }

    public ZjModel(String zjtm, String zjhm) {
        this.zjtm = zjtm;
        this.zjhm = zjhm;
    }

    public String getZjtm() {
        return zjtm;
    }

    public void setZjtm(String zjtm) {
        this.zjtm = zjtm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 根据主机头，主机后面部分数据，拼接
     * @return
     */
    public String getName(){

        if(StringUtils.isEmpty(zjtm)){
            return zjhm;
        }else{
            return zjtm + fgf + zjhm;
        }

    }

    @Override
    public String toString() {
        return "ZjModel{" +
                "zjtm='" + zjtm + '\'' +
                ", zjhm='" + zjhm + '\'' +
                '}';
    }
}
