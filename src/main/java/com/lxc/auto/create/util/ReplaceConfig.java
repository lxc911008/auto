package com.lxc.auto.create.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 文字替换配置表
 */
public class ReplaceConfig {

    public static Map<String,String> strConfigs;

    public static Map<String,String> photoConfigs;

    static {

        strConfigs = new HashMap<>();

        strConfigs.put("title","车辆展示");
        strConfigs.put("companyName","阳曲县龙飞运输有限公司");
        strConfigs.put("addr","太原市阳曲县东黄水镇故县村6号");
        strConfigs.put("name","李伟飞");
        strConfigs.put("telePhone","0351-80187013");
        strConfigs.put("phone","17051202504");
        strConfigs.put("email","193331600@qq.com");
        strConfigs.put("bah","晋ICP备19000785号-1"); //备案号
        strConfigs.put("bqsy","Copyright\\(C\\)2009-2020"); //版权所有

        photoConfigs = new HashMap<>();
        photoConfigs.put("logo","logo.jpg");
        photoConfigs.put("show1","2016031516300712712.jpg");
        photoConfigs.put("show2","20160315163018541854.jpg");
        photoConfigs.put("show3","20160315163022262226.jpg");
        photoConfigs.put("show4","20160315163023142314.jpg");
        photoConfigs.put("show5","20160315163028792879.jpg");
        photoConfigs.put("show6","20160315163047564756.jpg");
        photoConfigs.put("show7","20160315163072487248.jpg");
        photoConfigs.put("show8","20160315163077897789.jpg");
        photoConfigs.put("index","gw_swf1/1.jpg");


    }

}
