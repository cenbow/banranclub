/**
 * Analyse
 * analyse
 * Analyse.java
 * 2014-9-26
 */
package com.wechat.useraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.hercules.common.encryption.Encrypter;

/**
 * @author zhaoss 2014-9-26
 */
public class UserAnalyse {

	/**
	 * 读取用户文件
	 * 
	 * @return
	 * 
	 */
	public void readFlies() {
	    String filePath=this.getClass().getResource("/").getPath();//得到d:/tomcat/webapps/工程名WEB-INF/classes/路径
	    filePath=filePath.substring(1, filePath.length())+"config/properties/";//获得文件夹file.properties文件夹路径
		try {
			Properties property = new Properties();
			property.load(new FileInputStream(filePath+"file.properties"));
			// 文件路径
			 String oldFilePath = property.getProperty("file.oldurl");
			 String newFilePath = property.getProperty("file.newurl");  
			File oldfile = new File(oldFilePath);
			File newfile = new File(newFilePath);
			// 新文件夹地址不存在，创建新文件夹地址
			if (!newfile.exists() && !newfile.isDirectory()) {
				newfile.mkdir();
			}
			if (oldfile.isDirectory()) {
				File[] fileList = oldfile.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					if (!fileList[i].isDirectory()) {
						File nfile = new File(newFilePath + fileList[i].getName());
						try {
							// 如果文件已存在，删除原来的文件，创建新文件
							if (nfile.exists()) {
								nfile.delete();
							}
							nfile.createNewFile();
						} catch (IOException e) {
							System.out.println("创建文件操作出错 ");
							e.printStackTrace();
						}
						readFileByLines(fileList[i].getPath(), nfile.getPath(),filePath);
					}
					try {
						fileList[i].delete();
					} catch (Exception e) {
						System.out.println("删除文件操作出错 ");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件 ，编码UTF-8
	 */
	public static void readFileByLines(String oldFilePath, String newFilePath,String filePath) {
		File oldfile = new File(oldFilePath);
		File newfile = new File(newFilePath);
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			// 以行为单位读取文件内容，一次读一整行
			reader = new BufferedReader(new FileReader(oldfile));
			// 以行为单位写去文件内容，一次写一整行
			writer = new BufferedWriter(new FileWriter(newfile));
			String tempString = null;
			int line = 1;
			List<String[]> user = new ArrayList<String[]>();
			// 1、 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				// 写文件内容
				writer.write(tempString);
				//换行
				writer.newLine();
				// 2、进行数据分割
				String[] userAction = tempString.substring(tempString.indexOf("]")+3,tempString.lastIndexOf("]")).split("\\$");
				user.add(userAction);
			    //100批次提交一次
				if (user.size() == 100) {
					saveUserAction(user, filePath);
					user.clear();
				} 
				line++;
			}
			//文件行数不为整百时，把最后不足一百行的数据提交	
			System.out.println("line " + line + ": " + tempString);
			saveUserAction(user, filePath);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("读取文件操作出错 ");
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("写入文件操作出错 ");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 向T_USER_ACTION表插入用户行为分析数据
	 * 
	 * @param userAct
	 */
	public static void saveUserAction(List<String[]> userAct,String filePath) {

		Connection connection = null;
		PreparedStatement prest = null;

		try {
			Properties property = new Properties();
			property.load(new FileInputStream(filePath+"jdbc.properties"));
			String className = property.getProperty("jdbc.driverClassName");// jdbcDriver
			String url = property.getProperty("jdbc.url");// jdbcUrl
			String user = Encrypter.decrypt(property.getProperty("jdbc.username")); // jdbcName
			String password = Encrypter.decrypt(property.getProperty("jdbc.password")); // jdbcPassword

			Class.forName(className);
			// 获取数据库连接
			connection = DriverManager.getConnection(url, user, password);
			// 取消自动提交;
			connection.setAutoCommit(false);
			String sql = "INSERT INTO T_USER_ACTION(ACTION_ID,PLATFORM_ID,ACTION_TYPE,OPEN_ID,ACTION_TIME,MENU_ID,URL,KEYWORD,CREATED_DATE,UPDATED_DATE,CREATED_USER_CD,UPDATED_USER_CD,END_DATE) VALUES((SELECT PKG_PUB_UTIL.ld_idgenerator FROM dual),?,?,?,?,?,?,?,sysdate,sysdate,'admin','admin',TO_DATE('9999-12-31','yyyy-MM-dd'))";
			prest = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// 一百条数据处理一次
			for (int x = 0; x < userAct.size(); x++) {
				String[] userAction = userAct.get(x);
				// 添加参数
				//set userAction顺序为PLATFORM_ID,ACTION_TYPE,OPEN_ID,ACTION_TIME,MENU_ID,URL,KEYWORD
				prest.setString(1, userAction[0]);
				prest.setString(2, userAction[1]);
				prest.setString(3, userAction[2]);
				// 将String类型转换Date类型
				DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date action_time = fmt.parse(userAction[3]);
				prest.setDate(4, new java.sql.Date(action_time.getTime()));
				if (userAction[4].length() == 0) {
					prest.setString(5, null);
				} else {
					prest.setString(5, userAction[4]);
				}
				if (userAction.length < 6 || userAction[5].length() == 0) {
					prest.setString(6, null);
				} else {
					prest.setString(6, userAction[5]);
				}
				if(userAction.length < 7 || userAction[6].length() == 0){
					prest.setString(7, null);
				} else {
					prest.setString(7, userAction[6]);
				}
				prest.addBatch();
			}
			// 100批次提交
			prest.executeBatch();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prest != null) {
					prest.close();
					prest = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		UserAnalyse a = new UserAnalyse();
		a.readFlies();
	}
}
