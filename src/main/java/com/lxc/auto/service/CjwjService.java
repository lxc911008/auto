package com.lxc.auto.service;

import com.lxc.auto.model.ZjModel;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建文件服务
 */
@Service
public class CjwjService {

    //路径分隔符，区分linux和windows
    public static final String pathFgf = "/";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param cjlj 创建路径
     * @param wjjm 文件夹名
     * @param zjtm 主机头名，多个以逗号分隔
     * @param zjhm 主机后面名字，多个以逗号分隔
     * @param ywjlj 源文件路径
     */
    public List<String> doIt(String cjlj,String wjjm,String zjtm,String zjhm,String ywjlj){

        //根据主机头名，主机后面名字获取组合数组
        List<ZjModel> zjModels = getZjZhList(zjtm,zjhm);

        //根据文件夹名与主机model对象数组，组合对应的最后文件夹名数组
        List<String> wjjNames = getWjjNames(wjjm,zjModels);

        //在对应路径依次创建文件夹并返回对应路径
        List<String> tagetPahts = creatAndGetTagetPahts(cjlj,wjjNames);

        //将源文件路径下的所有数据拷贝到上面的路径中
        copyFilesToTagetPaht(tagetPahts,ywjlj);

        return tagetPahts;


    }

    /**
     * 将源文件路径下的所有数据拷贝到上面的路径中
     * @param tagetPahts
     * @param ywjlj
     */
    private void copyFilesToTagetPaht(List<String> tagetPahts, String ywjlj) {

        File ywjFile = new File(ywjlj); //源文件

        for(String tagetPath : tagetPahts){

            File tagetFile = new File(tagetPath); //目标文件路径

            try {
                //先全部删除然后再复制
                FileUtils.cleanDirectory(tagetFile);
                FileUtils.copyDirectory(ywjFile,tagetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 在对应路径依次创建文件夹并返回对应路径，如果已经存在，则不会新建
     * @param cjlj
     * @param wjjNames
     * @return
     */
    public List<String> creatAndGetTagetPahts(String cjlj, List<String> wjjNames) {

        List<String> paths = new ArrayList<>();

        for(String wjjName : wjjNames){

            String filePath = cjlj + CjwjService.pathFgf + wjjName;
            File fileDir = new File(filePath);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }

            paths.add(filePath);
        }

        return paths;

    }

    /**
     * 根据文件夹名与主机model对象数组，组合对应的最后文件夹名数组
     * @param wjjm 文件夹名
     * @param zjModels zjmodel对象数组
     * @return
     */
    public List<String> getWjjNames(String wjjm, List<ZjModel> zjModels) {

        List<String> revs = new ArrayList<>();

        for(ZjModel z : zjModels){

            revs.add(wjjm + z.getName());

        }

        return revs;

    }

    /**
     * 根据主机头名，主机后面名称组合对应的主机模型对象数组
     * @param zjtm
     * @param zjhm
     * @return
     */
    public List<ZjModel> getZjZhList(String zjtm, String zjhm) {

        List<ZjModel> zjModels = new ArrayList<>();

        String[] zjtms = zjtm.split(","); //主机头数组
        String[] zjhms = zjhm.split(","); //主机后面名字数组

        //遍历主机头，主机后面部分，组合
        for(String zt : zjtms){

            for(String zh : zjhms){

                ZjModel z = new ZjModel(zt,zh);
                zjModels.add(z);

            }

        }

        //主机头为空，只要主机后面部分，组合
        for(String zh : zjhms){
            ZjModel z = new ZjModel(null,zh);
            zjModels.add(z);
        }

        return zjModels;

    }
}
