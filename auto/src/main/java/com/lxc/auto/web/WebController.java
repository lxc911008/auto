package com.lxc.auto.web;

import com.lxc.auto.model.FrontObj;
import com.lxc.auto.service.CjwjService;
import com.lxc.auto.service.CjwzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class WebController {

    @Autowired
    private CjwjService cjwjService;

    @Autowired
    private CjwzService cjwzService;

    /**
     * 创建文件请求
     * @param cjlj 创建路径
     * @param wjjm 文件夹名称
     * @param zjtm 主机头名称，多个逗号分隔
     * @param zjhm  主机后面部分名称，多个逗号分隔
     * @param ysjlj 源数据路径
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cjwj")
    public FrontObj cjwj(String cjlj,String wjjm,String zjtm,String zjhm,String ysjlj){

        List<String> revs = cjwjService.doIt(cjlj,wjjm,zjtm,zjhm,ysjlj);

        FrontObj<List> foj = new FrontObj<>();
        foj.setData(revs);

        return foj;

    }

    /**
     * 建站请求
     * @param cjlj 创建路径
     * @param wjjm 文件夹名称
     * @param zjtm 主机头名称，多个逗号分隔
     * @param zjhm  主机后面部分名称，多个逗号分隔
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/jz")
    public FrontObj jz(String cjlj,String wjjm,String zjtm,String zjhm){

        String rev = cjwzService.doIt(cjlj,wjjm,zjtm,zjhm);

        FrontObj<String> foj = new FrontObj<>();
        foj.setData(rev);

        return foj;

    }

    /**
     * 创建文件并建站
     * @param cjlj 创建路径
     * @param wjjm 文件夹名称
     * @param zjtm 主机头名称，多个逗号分隔
     * @param zjhm  主机后面部分名称，多个逗号分隔
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cjwjbjz")
    public FrontObj cjwjbjz(String cjlj,String wjjm,String zjtm,String zjhm,String ysjlj){

        //创建文件
        cjwjService.doIt(cjlj,wjjm,zjtm,zjhm,ysjlj);

        //建站
        String rev = cjwzService.doIt(cjlj,wjjm,zjtm,zjhm);

        FrontObj<String> foj = new FrontObj<>();
        foj.setData(rev);

        return foj;

    }


}
