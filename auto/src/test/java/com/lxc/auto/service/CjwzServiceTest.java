package com.lxc.auto.service;

import com.lxc.auto.model.CjwzModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CjwzServiceTest {

    @Autowired
    private CjwzService cjwzService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getTagetPaths(){

        String cjlj = "D:" + CjwjService.pathFgf + "测试"+ CjwjService.pathFgf +"新建文件在这里";
        String wjjm = "a龙飞有限公司";
        String zjtm = "dao,wang";
        String zjhm = "123125.cn,123126.cn,123127.cn,123128.cn";
        String ywjlj = "D:"+ CjwjService.pathFgf +"测试"+ CjwjService.pathFgf +"源文件";

        List<CjwzModel> revs = cjwzService.getTagetPaths(cjlj,wjjm,zjtm,zjhm);

        for(CjwzModel cz : revs){

            logger.info(cz.toString());

        }
    }

    @Test
    public void doIt(){

        String cjlj = "D:" + CjwjService.pathFgf + "测试"+ CjwjService.pathFgf +"新建文件在这里";
        String wjjm = "a龙飞有限公司";
        String zjtm = "dao,wang";
        String zjhm = "123125.cn,123126.cn,123127.cn,123128.cn";
        String ywjlj = "D:"+ CjwjService.pathFgf +"测试"+ CjwjService.pathFgf +"源文件";

        String revs = cjwzService.doIt(cjlj,wjjm,zjtm,zjhm);

    }
}
