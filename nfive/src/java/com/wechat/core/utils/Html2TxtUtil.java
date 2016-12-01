package com.wechat.core.utils;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class Html2TxtUtil {
	
	
	 private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	 private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	 private static final String regEx_html = "<[^>^a]+>"; // 定义HTML标签的正则表达式  (a标签作为页面链接是不能去除的)
	 private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符 
	
	 /**
	  * 去除HTML标签,保留A标签
	  * @param htmlStr
	  * @return
	  */
	 public static String removeHTMLTag(String htmlStr) {
		 	 
	    	 //1   将两个换行符转换成一个
	    	 Pattern p_html_n = Pattern.compile("\n\n", Pattern.CASE_INSENSITIVE);  
		     Matcher m_html_n = p_html_n.matcher(htmlStr); 
		     htmlStr = m_html_n.replaceAll("\n");
		     //1.2   将<p/>标签更换成换行
	    	 Pattern p_html_p = Pattern.compile("</p>", Pattern.CASE_INSENSITIVE);  
		     Matcher m_html_p = p_html_p.matcher(htmlStr); 
		     htmlStr = m_html_p.replaceAll("\n");
	    	 //2.去除html标签(A标签去除不可用 )
		     Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
		     Matcher m_html = p_html.matcher(htmlStr); 
		     htmlStr = m_html.replaceAll("");
	    	 //3. 还原XML实体  
		     htmlStr = htmlStr.replaceAll("&ldquo;","\"");//全角双引号前
		     htmlStr = htmlStr.replaceAll("&rdquo;","\"");//全角双引号后
		     
		     htmlStr = htmlStr.replaceAll("&rsquo;","'");//全角双引号前
		     htmlStr = htmlStr.replaceAll("&lsquo;","'");//全角双引号后
		     
		     htmlStr = htmlStr.replaceAll("&quot;","\"");//半角双引号
		     htmlStr = htmlStr.replaceAll("&#39;","\"");//半角单引号
		     
		     htmlStr = htmlStr.replaceAll("&hellip;&hellip;","……");//省略号
		     htmlStr = htmlStr.replaceAll("&mdash;&mdash;","——");//破折号
		     
		     htmlStr = htmlStr.replaceAll("&amp;","&");//和号
		     htmlStr = htmlStr.replaceAll("&gt;", ">");//替换大于号实体,
		     htmlStr = htmlStr.replaceAll("&lt;", "<");//替换大于号实体,
		     htmlStr = htmlStr.replaceAll("&nbsp;"," ");//空格
		 
	         return htmlStr.trim(); // 返回文本字符串  
	 } 
	 
	 /**
	  * 去除所有空字符,回车,换行
	  * @param htmlStr
	  * @return
	  */
	 public static String removeSPACETag(String htmlStr) {  
	        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
	        Matcher m_space = p_space.matcher(htmlStr);  
	        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签 
	        htmlStr = htmlStr.replaceAll("&nbsp;", ""); //去除空字符
	        return htmlStr.trim(); // 返回文本字符串  
	  } 
	 
	 /**
	  * 去除所有样式
	  * @param htmlStr
	  * @return
	  */
	 public static String removeSTYLETag(String htmlStr) {  
	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
	        Matcher m_style = p_style.matcher(htmlStr);  
	        htmlStr = m_style.replaceAll(""); // 过滤CSS样式标签  
	        return htmlStr.trim(); // 返回文本字符串  
	 } 
	 
	 /**
	  * 去除所有JS代码
	  * @param htmlStr
	  * @return
	  */
	 public static String removeSCRIPTTag(String htmlStr) {  
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
	        Matcher m_script = p_script.matcher(htmlStr);  
	        htmlStr = m_script.replaceAll(""); // 过滤JS代码标签  
	        return htmlStr.trim(); // 返回文本字符串  
	 } 
	  
	 
	 /**
	  * 将文件的相对路径逆转成URL的相对路径
	  * @param filePath
	  * @return
	  */
	 public static String filePath2UrlPath(String filePath){
		 String tmpPath ="";
		 if(StringUtils.isNotEmpty(filePath)){
			 //linux环境下
			 if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
				 tmpPath =filePath.replace("\\", "/");
			 }else{
			     tmpPath =filePath.replace( File.separator, "/");
			 }
		 }
		 return tmpPath;
	 }
	 
     /**
      * 测试类
      * @param args
      */
     public static void main(String[] args) { 
    	 
    	 

	        String str = "<p></p><style>////CSS样式#a{text-algin:center;}</style>---------&gt;&lt;&quot;&apos;&amp;--<p></p>---------------<script>function text(){alert('JS代码')}</script><div style='text-align:center;'>&nbsp; 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";  
	        System.out.println(removeHTMLTag("<p>哈哈哈</p><p>呵呵呵</p><p>HOHOHO</p>"));//测试去除所有HTML标签
	        //System.out.println(removeSTYLETag(str));//去除所有CSS样式
	       // System.out.println(removeSPACETag(str));//测试去除所有空字符,回车,换行等
	        //System.out.println(removeSCRIPTTag(str));//测试去除所有JS代码
//        String aa ="D:\\tomcat6.0\\webapps\\wechat\\201408";
//        System.out.println(filePath2UrlPath(aa));
     }  
}
