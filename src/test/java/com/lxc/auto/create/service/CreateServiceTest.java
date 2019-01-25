package com.lxc.auto.create.service;

import com.lxc.auto.create.model.ReplaceModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateServiceTest {

    @Autowired
    private CreateService createService;

    @Test
    public void readModel(){

        List<ReplaceModel> strRms = new ArrayList<>();

        strRms.add(new ReplaceModel("title","产品展示")); //车辆展示标题（默认产品展示）
        strRms.add(new ReplaceModel("companyName","测试公司名称")); //公司名称
        strRms.add(new ReplaceModel("addr","测试使用公司地址")); //公司地址
        strRms.add(new ReplaceModel("name","李三")); //联系人
        strRms.add(new ReplaceModel("telePhone","00-000898989")); //座机号码
        strRms.add(new ReplaceModel("phone","18897667887667")); //电话号码
        strRms.add(new ReplaceModel("email","329099357@qq.com")); //邮箱
        strRms.add(new ReplaceModel("bah","测试备案号码")); //备案号
        strRms.add(new ReplaceModel("bqsy","Copyright(C)2009-20288")); //版权所有（默认Copyright(C)2009-2020）


        List<ReplaceModel> photoRms = new ArrayList<>();
        photoRms.add(new ReplaceModel("logo","D:\\webModel\\test\\logo.jpg")); //logo图
        photoRms.add(new ReplaceModel("show1","D:\\webModel\\test\\show1.jpg")); //产品展示图1
        photoRms.add(new ReplaceModel("show2","D:\\webModel\\test\\show2.jpg")); //产品展示图2
        photoRms.add(new ReplaceModel("show3","D:\\webModel\\test\\show3.jpg")); //产品展示图3
        photoRms.add(new ReplaceModel("show4","D:\\webModel\\test\\show4.jpg")); //产品展示图4
        photoRms.add(new ReplaceModel("show5","D:\\webModel\\test\\show5.jpg")); //产品展示图5
        photoRms.add(new ReplaceModel("show6","D:\\webModel\\test\\show6.jpg")); //产品展示图6
        photoRms.add(new ReplaceModel("show7","D:\\webModel\\test\\show7.jpg")); //产品展示图7
        photoRms.add(new ReplaceModel("show8","D:\\webModel\\test\\show8.jpg")); //产品展示图8
        photoRms.add(new ReplaceModel("index","D:\\webModel\\test\\index.jpg")); //首页大图


        createService.doIt(strRms,photoRms);

    }

}
