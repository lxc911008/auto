package com.lxc.auto.create.service;

import com.lxc.auto.create.model.ReplaceModel;
import com.lxc.auto.create.util.ReplaceConfig;
import com.lxc.auto.create.util.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class CreateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${config.create.sourPath}")
    private String sourPath;

    private String base = "D:/create";
    private String fileTempPath = base + "/temp";
    public String zipPath = base + "/tempZip.zip";

    /**
     * 文字替换
     * @param allStr 全部文字
     * @param mateStr 匹配文字
     * @param repStr 替换后文字
     */
    public void charReplace(String allStr,String mateStr,String repStr){

        allStr.replaceAll(mateStr,repStr);

    }

    /**
     * 获取对应路径文件夹，若不存在，则新建
     * @param path
     * @return
     */
    public File getDirFile(String path){

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        return file;

    }

    /**
     *
     * @param strRms 需要替换的文字部分
     * @param photoRms 需要替换的图片部分
     */
    public void doIt(List<ReplaceModel> strRms,List<ReplaceModel> photoRms){

        //将base文件夹下的东西清空
        try {
            FileUtils.deleteDirectory(new File(base));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取临时文件存放路径
        File tempFile = getDirFile(fileTempPath);

        File soruFileDir = new File(sourPath);

        //递归读取所有文件
        List<File> allFiles = (List<File>)FileUtils.listFiles(soruFileDir,null,true);

        for(File f : allFiles){

            try {
                byte[] bt = FileUtils.readFileToByteArray(f);

                String fileName = f.getName();

                if(fileName.endsWith("htm") || fileName.endsWith("js") || fileName.endsWith("css")){
                    //进行文字处理
                    bt = strDetail(f,strRms);

                }else if(fileName.endsWith("jpg")){
                    //进行图片处理
                    byte[] rev = photoDetail(f,photoRms);
                    if(rev != null){
                        bt = rev;
                    }

                }

                //获取对应输出路径，文件夹不存在的则对应新建
                File osFile = getOsFile(f);
                //写入
                FileUtils.writeByteArrayToFile(osFile,bt);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //将最后文件压缩打包
        try {
            //压缩包路径
            FileOutputStream fios = new FileOutputStream(new File(zipPath));
            ZipUtils.toZip(fileTempPath, fios,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 对应图片替换
     * @param f
     * @param photoRms
     * @return
     */
    private byte[] photoDetail(File f, List<ReplaceModel> photoRms) throws IOException {

        byte[] rev = null;

        String path = f.getAbsolutePath().replaceAll("\\\\","\\/");

        for(ReplaceModel rm : photoRms){

            String id = rm.getId();
            String name = ReplaceConfig.photoConfigs.get(id);

            if(path.endsWith(name)){

                String newPath = rm.getValue();
                rev = FileUtils.readFileToByteArray(new File(newPath));

                break;
            }

        }

        return rev;

    }

    /**
     * 获取对应输出文件路径
     * @return
     */
    private File getOsFile(File f) {

        //将路径斜杠转换，并将前面sourPath部分置空
        String pjName = f.getAbsolutePath().replaceAll("\\\\","\\/").replaceAll(sourPath,"");

        //分割数组
        String[] pjs = pjName.split("\\/");

        String basePath = fileTempPath;

        //除了最后一位，其他拼接，文件夹路径如果不存在，需要新建
        for(int i = 0; i < pjs.length - 1; i++){
            basePath += "/" + pjs[i];
        }

        File baseDir = getDirFile(basePath);

        //基本路径文件夹新建好，则拼接最后文件明，作为最后输出文件
        File osFile = new File(basePath + "/" + pjs[pjs.length - 1]);

        return osFile;

    }

    /**
     * 删除缓存文件夹
     */
    public void deleteTemp(){

        try {
            FileUtils.deleteDirectory(new File(base));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据文字替换对象数组进行文字处理
     * @param f
     * @param strRms
     */
    private byte[] strDetail(File f, List<ReplaceModel> strRms) throws IOException {

        String str = FileUtils.readFileToString(f,"gbk");

        for(ReplaceModel strRm : strRms){

            String id = strRm.getId();
            String sourStr = ReplaceConfig.strConfigs.get(id);
            String tagetStr = strRm.getValue();
            str = str.replaceAll(sourStr,tagetStr);

        }

        byte[] bt = str.getBytes("gbk");

        return bt;

    }

}
