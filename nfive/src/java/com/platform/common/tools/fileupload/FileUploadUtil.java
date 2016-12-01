package com.platform.common.tools.fileupload;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.constant.FileConstant;
import com.platform.common.tools.properties.UtilConfig;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wechat.material.model.dto.TAttachMaterialDto;


/***
 * @author hercuels.chen
 */
@Service("fileUploadUtil")
public class FileUploadUtil {
	@Autowired
	private UtilConfig utilConfig;

    private static  Logger logger  = Logger.getLogger(FileUploadUtil.class);

    public static String  pub_file_path="/Users/jinzhihong/Documents/PROJECT/02.代码/out/nfive_image/"; //永久文件存储地址
	public static String  tmp_file_path="/Users/jinzhihong/Documents/PROJECT/02.代码/out/nfive_image/";   //临时文件存储地址

    public static String  domain_weburl="http://localhost:9090/image/";

    /****
     *spring中定义的初始化方法
     *用于初始化读取配置
     */
    @SuppressWarnings("unused")
    public void InitConfig(){
    }

    /***
     * 通过常量将文件类型分类 若无常量
     * @param filetype 使用常量  5012
     * @return String  文件路径
     */
    private static String getPubStoragePath(String material_type){
    	String filetype = FileConstant.FILE_TYPE_OTHER;//默认
    	if(!StringUtils.isEmpty(material_type))
    	{
	    	//判断material_type分类文件
	    	if(CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG.equalsIgnoreCase(material_type)){
	    		filetype =FileConstant.FILE_TYPE_IMG;
	    	}else if(CodeStringConstant.CS_5012_MATERIAL_TYPE_VOICE.equalsIgnoreCase(material_type)){
	    		filetype =FileConstant.FLIE_TYPE_VOICE;
	    	}else if(CodeStringConstant.CS_5012_MATERIAL_TYPE_VIDEO.equalsIgnoreCase(material_type)){
	    		filetype =FileConstant.FLIE_TYPE_VIDEO;
	    	}else if(CodeStringConstant.CS_5012_MATERIAL_TYPE_THUMB.equalsIgnoreCase(material_type)){
	    		filetype =FileConstant.FLIE_TYPE_THUMB;
	    	}
    	}
    	String subPath = new SimpleDateFormat("yyyy-MM").format(new Date()).replaceAll("-", "");
    	return subPath+File.separator+filetype+File.separator;
    }

    /**
     * 提供临时文件的规范保存,返回数据可保存的对象
     * @param  tmpFile        		   临时文件
     * @param  file_name      		   原文件名称
     * @param  material_type  		   文件类型
     * @return TAttachMaterialDto
     * @throws IOException           文件异常
     */
    public static TAttachMaterialDto bulidAttachMaterialDtoByTmpFile(File tmpFile,String file_name,String material_type) throws IOException{
		TAttachMaterialDto  tAttachMaterialDto= new TAttachMaterialDto();
		tAttachMaterialDto.setFile_name(file_name);//附件实际的名称
	  	int  perindex = file_name.lastIndexOf('.');
	  	if(perindex!=-1){
		  	String prefix_part = file_name.substring(0,perindex);//file_name.substring(0, file_name.length()-perindex+1);
		  	String postfix_part =file_name.substring(perindex+1);//file_name.substring(file_name.length()-perindex+2, file_name.length());
		  	tAttachMaterialDto.setFile_prefix(prefix_part);//文件前缀
			tAttachMaterialDto.setFile_postfix(postfix_part);//文件后缀
	  	}else{//无后缀文件
	  		tAttachMaterialDto.setFile_prefix(file_name);
	  	}
	  	tAttachMaterialDto.setContent_size(tmpFile.length());////内容大小
    	//1.检查创建文件需要存储的目录
    	String store_path =getPubStoragePath(material_type);
    	File fileDir = new File(store_path);
//		if(!fileDir.exists()){
//			fileDir.mkdirs();//若不存在则创建目录
//		}
		tAttachMaterialDto.setStore_path(store_path);//存储路径
		String destFileName = "file_"+UUID.randomUUID().toString()+"."+tAttachMaterialDto.getFile_postfix();	//归档自动生成的存储名称 用于避免同目录下文件名冲突
		tAttachMaterialDto.setStoret_name(destFileName);//归档自动生成的存储名称 用于避免同目录下文件名冲突

		try {
		   	//2.将临时文件COPY到实际存储的目录
			File destFile = new File(pub_file_path+File.separator+store_path+File.separator+destFileName);
			FileUtils.copyFile(tmpFile, destFile);
			System.out.println("have copy ["+file_name+"]file to ["+destFile.getAbsolutePath()+"]");
		} catch (IOException e) {
			logger.error("["+tmpFile.getAbsoluteFile()+"]save to ["+"] is failed", e);
			throw e;
		}
		//返回拼装后可供数据库保存的文件
    	return tAttachMaterialDto;
    }


    /**
     * 将图片文件压缩到指定大小
     * @param srcImage   图片文件
     * @param outWidth   输出图片宽
     * @param outHeight  输出图片高
     * @return
     * @throws IOException
     */
    public static File zipImageFile(File srcImageFile,int outWidth,int outHeight){

    	File zipImageFile = null;

		// 临时文件生成
		String tmp_savepath = FileUploadUtil.tmp_file_path + File.separator + "zip";
		String tmp_file_str = tmp_savepath + File.separator + "tmp_" + UUID.randomUUID().toString();

		// 创建图片文件
		Image srcImage = null;
		FileOutputStream tmpImageFile = null;
		try {
			// 将文件读进image中
			srcImage = ImageIO.read(srcImageFile);
			// 设置缓存方式
			BufferedImage tag = new BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_RGB);
			// 绘制缩小后的图
			tag.getGraphics().drawImage(srcImage.getScaledInstance(outWidth, outHeight, Image.SCALE_DEFAULT), 0, 0, null);
			// 输出路径
			File file = new File(tmp_savepath);
			// 如果输出路径不存在，则创建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 构建输出流
			zipImageFile = new File(tmp_file_str);
			tmpImageFile = new FileOutputStream(zipImageFile);
			// JPEGImageEncoder可适用于其他图片类型的转换
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tmpImageFile);
			// JPEG编码
//			encoder.encode(tag);

			ImageIO.write(tag, "JPEG", zipImageFile);
		} catch (IOException e) {
			logger.error("file upload Exception");
			return null;
			// 关闭输出流
		} finally {
			try {
				tmpImageFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return zipImageFile;
    }



    public static void main(String[] args) throws IOException {
    	String fullName = "hello.txt";
    	File tmpfile = new File("d:/"+fullName);
    	FileUploadUtil.bulidAttachMaterialDtoByTmpFile(tmpfile, "hello.txt", CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
    }
}
