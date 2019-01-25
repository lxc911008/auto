package com.lxc.auto.create.web;

import com.lxc.auto.create.model.ReplaceModel;
import com.lxc.auto.create.service.CreateService;
import com.lxc.auto.model.FrontObj;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("create")
public class CreateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreateService createService;

    @RequestMapping(method = RequestMethod.POST,value = "do")
    public void doIt(HttpServletRequest request, HttpServletResponse response,String name,String id) {

        logger.info("id:" + id);
        logger.info("name:" + name);

        List<ReplaceModel> strRms = new ArrayList<>();

        strRms.add(new ReplaceModel("title", "产品展示")); //车辆展示标题（默认产品展示）
        strRms.add(new ReplaceModel("companyName", "测试公司名称")); //公司名称
        strRms.add(new ReplaceModel("addr", "测试使用公司地址")); //公司地址
        strRms.add(new ReplaceModel("name", "李三")); //联系人
        strRms.add(new ReplaceModel("telePhone", "00-000898989")); //座机号码
        strRms.add(new ReplaceModel("phone", "18897667887667")); //电话号码
        strRms.add(new ReplaceModel("email", "329099357@qq.com")); //邮箱
        strRms.add(new ReplaceModel("bah", "测试备案号码")); //备案号
        strRms.add(new ReplaceModel("bqsy", "Copyright(C)2009-20288")); //版权所有（默认Copyright(C)2009-2020）


        List<ReplaceModel> photoRms = new ArrayList<>();
        photoRms.add(new ReplaceModel("logo", "D:\\webModel\\test\\logo.jpg")); //logo图
        photoRms.add(new ReplaceModel("show1", "D:\\webModel\\test\\show1.jpg")); //产品展示图1
        photoRms.add(new ReplaceModel("show2", "D:\\webModel\\test\\show2.jpg")); //产品展示图2
        photoRms.add(new ReplaceModel("show3", "D:\\webModel\\test\\show3.jpg")); //产品展示图3
        photoRms.add(new ReplaceModel("show4", "D:\\webModel\\test\\show4.jpg")); //产品展示图4
        photoRms.add(new ReplaceModel("show5", "D:\\webModel\\test\\show5.jpg")); //产品展示图5
        photoRms.add(new ReplaceModel("show6", "D:\\webModel\\test\\show6.jpg")); //产品展示图6
        photoRms.add(new ReplaceModel("show7", "D:\\webModel\\test\\show7.jpg")); //产品展示图7
        photoRms.add(new ReplaceModel("show8", "D:\\webModel\\test\\show8.jpg")); //产品展示图8
        photoRms.add(new ReplaceModel("index", "D:\\webModel\\test\\index.jpg")); //首页大图

        //执行
        createService.doIt(strRms, photoRms);

        //下载对应路径压缩包
        try (
                //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                InputStream inputStream = new FileInputStream(new File(createService.zipPath));
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            String fileName = "target.zip";
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);   // 设置文件名

            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //删除缓存的文件夹
        createService.deleteTemp();

    }

}
