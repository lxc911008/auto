package com.lxc.auto.service;

import com.lxc.auto.model.CjwzModel;
import com.lxc.auto.model.ZjModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建网站服务
 */
@Service
public class CjwzService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CjwjService cjwjService;

    @Value("${config.iisPath}")
    private String qdIIsPath; //启动iis服务器路径

    //第一次鼠标移动的坐标位置
    @Value("${config.coor.firstX}")
    private int firstX;
    @Value("${config.coor.firstY}")
    private int firstY;

    //第二次鼠标移动的坐标位置
    @Value("${config.coor.secondX}")
    private int secondX;
    @Value("${config.coor.secondY}")
    private int secondY;

    //第三次鼠标移动的坐标位置
    @Value("${config.coor.thirdX}")
    private int thirdX;
    @Value("${config.coor.thirdY}")
    private int thirdY;

    //第四次鼠标移动的坐标位置
    @Value("${config.coor.fourthX}")
    private int fourthX;
    @Value("${config.coor.fourthY}")
    private int fourthY;

    //主机名鼠标移动的坐标位置
    @Value("${config.coor.zjmX}")
    private int zjmX;
    @Value("${config.coor.zjmY}")
    private int zjmY;

    //勾选时选择3鼠标移动的坐标位置
    @Value("${config.coor.xz1X}")
    private int xz1X;
    @Value("${config.coor.xz1Y}")
    private int xz1Y;

    //勾选时选择5鼠标移动的坐标位置
    @Value("${config.coor.xz2X}")
    private int xz2X;
    @Value("${config.coor.xz2Y}")
    private int xz2Y;

    @Value("${config.coor.glNum}")
    private int glNum; //向上滚动位置


    /**
     * 获取对应的目标路径数组
     * @param cjlj 创建路径
     * @param wjjm 文件夹名称
     * @param zjtm 主机头名称
     * @param zjhm 主机后面部分名称
     * @return
     */
    public List<CjwzModel> getTagetPaths(String cjlj, String wjjm, String zjtm, String zjhm){

        List<CjwzModel> cms = new ArrayList<>();

        //根据主机头名，主机后面名字获取组合数组
        List<ZjModel> zjModels = cjwjService.getZjZhList(zjtm,zjhm);

        //根据文件夹名与主机model对象数组，组合对应的最后文件夹名数组
        List<String> wjjNames = cjwjService.getWjjNames(wjjm,zjModels);

        //在对应路径依次创建文件夹并返回对应路径
        List<String> tagetPahts = cjwjService.creatAndGetTagetPahts(cjlj,wjjNames);

        int len = zjModels.size();

        for(int i = 0; i < len; i ++){

            CjwzModel cjwzModel = new CjwzModel();
            cjwzModel.setPath(tagetPahts.get(i));
            cjwzModel.setWjjName(wjjNames.get(i));
            cjwzModel.setZjName(zjModels.get(i).getName());

            cms.add(cjwzModel);
        }

        return cms;

    }

    /**
     * 启动iis服务器
     */
    public void qdIIsFw(){


        //File qdFile = new File(qdIIsPath);
        String zx = "cmd.exe /k start " + qdIIsPath;

        try {
            Runtime.getRuntime().exec(zx);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建网站步骤
     * @param robot
     */
    public void createWz(Robot robot,CjwzModel cm){
        //移动到网站选项
        robot.mouseMove(secondX,secondY);

        robot.delay(1000);
        //右键单击展开功能栏
        pressMouse(robot,InputEvent.BUTTON3_MASK);
        robot.delay(1000);

        //鼠标移动到新建栏
        robot.mouseMove(thirdX,thirdY);
        robot.delay(1000);

        //鼠标移动到网站栏
        robot.mouseMove(fourthX,fourthY);
        robot.delay(1000);

        //鼠标左键单击
        pressMouse(robot,InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //按下回车键，进入下一步
        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(1000);

        //输入描述
        copyText(robot,cm.getWjjName());
        robot.delay(1000);

        //按下回车键，进入下一步
        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(1000);

        //移动到主机名输入位置，单击
        robot.mouseMove(zjmX,zjmY);
        pressMouse(robot,InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //输入主机名
        copyText(robot,cm.getZjName());
        robot.delay(1000);

        //回车下一步
        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(1000);

        //输入文件夹路径
        copyText(robot,cm.getPath());
        robot.delay(1000);

        //回车下一步
        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(1000);

        //选择选项，第一项默认选中，将鼠标移动到第三项，单击
        robot.mouseMove(xz1X,xz1Y);
        pressMouse(robot,InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //选择选项，第一项默认选中，将鼠标移动到第五项，单击
        robot.mouseMove(xz2X,xz2Y);
        pressMouse(robot,InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //两步回车
        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(1000);

        keyDown(robot,KeyEvent.VK_ENTER);
        robot.delay(2000);

    }

    /**
     * 创建网站执行主方法
     * @param cjlj 创建路径
     * @param wjjm 文件夹名称
     * @param zjtm 主机头名称
     * @param zjhm 主机后面部分名称
     * @return
     */
    public String doIt(String cjlj, String wjjm, String zjtm, String zjhm){

        String rev = "ok";

        //获取创建网站需要的对象数组
        List<CjwzModel> cms = getTagetPaths(cjlj,wjjm,zjtm,zjhm);

        qdIIsFw();

        //开始操作iis服务器进行新建网站操作
        try {
            Robot robot = new Robot();
            //设置Robot产生一个动作后的休眠时间,否则执行过快
            //robot.setAutoDelay(1000);

            //移动鼠标到第一层
            robot.mouseMove(firstX, firstY);
            robot.delay(1000);

            //鼠标左键双击展开
            pressMouse(robot,InputEvent.BUTTON1_MASK);
            robot.delay(100);
            pressMouse(robot,InputEvent.BUTTON1_MASK);

            robot.delay(1000);

            for(CjwzModel cm : cms){

                //循环创建网站
                createWz(robot,cm);
                //创建完毕一个，需要自动向上滚动到最顶层，然后重新开始创建
                robot.mouseWheel(glNum);
                robot.delay(1000);

            }


        } catch (AWTException e) {

            rev = e.getMessage();

            e.printStackTrace();
        }

        return rev;


    }

    /**
     * 复制数据并粘贴
     * @param str
     */
    public void copyText(Robot robot,String str){

        addTextToCopy(str);
        //按下ctrl+v粘贴数据
        keySerDown(robot,KeyEvent.VK_CONTROL,KeyEvent.VK_V);
    }

    /**
     * 添加数据到剪切板
     */
    public void addTextToCopy(String str){
        //获取系统剪切板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //构建String数据类型
        StringSelection selection = new StringSelection(str);
        //添加文本到系统剪切板
        clipboard.setContents(selection, null);
    }

    /**
     * 连续按下多个键
     * @param keys
     */
    public void keySerDown(Robot robot,int... keys){

        //全部按下
        for(int key : keys){
            robot.keyPress(key);
        }

        //全部释放
        for(int key : keys){
            robot.keyRelease(key);
        }

    }

    /**
     * 按下并释放键盘
     * @param key
     */
    public void keyDown(Robot robot,int key){

        robot.keyPress(key);
        robot.keyRelease(key);

    }

    /**
     * 按下鼠标
     * @param robot
     * @param key
     */
    public void pressMouse(Robot robot,int key){

        robot.mousePress(key);
        robot.mouseRelease(key);
    }

}
