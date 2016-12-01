package com.pub.common.tools.opensymphony.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author CHENJIA
 * 
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	public String orgUrl = null;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/***
	 * 基础域名获取简便类的方式 add by chenjia 2009.8.18
	 * */
	public String getBasePath() {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + getPath() + "/";
	}

	public String getPath() {
		return request.getContextPath();
	}

	/**
	 * 输出JSOND数据 dd by chenjia 2009.8.18
	 * **/
	public void outJSOND(HttpServletResponse response, String content)
			throws IOException {
		PrintWriter _writer =null;
		try {
			response.setContentType("text/plain;charset=UTF-8");
			_writer = response.getWriter();
			_writer.print(content);
			_writer.flush();
		} catch (IOException ex) {
			throw ex;
		} finally {
			_writer.close();
		}
	}

	/**
	 * 输出JSOND数据 dd by zzq 2009.8.18 拼接前台分页数据
	 * **/
	public void outJSOND(HttpServletResponse response, String content,int maxRecord) throws IOException {
		PrintWriter _writer =null;
		try {
		response.setContentType("text/plain;charset=UTF-8");
		_writer = response.getWriter();
		// 添加分页信息
		content = "{\"total\":\"" + maxRecord + "\",\"rows\":" + content + "}";
		_writer.print(content);
		_writer.flush();}
		catch (IOException ex) {
			throw ex;
		} finally {
			_writer.close();
		}
	}

	/**
	 * 输出XML数据 dd by chenjia 2009.8.18
	 * **/
	public void outXml(HttpServletResponse response, Document document)
			throws IOException {
		{
			OutputStream outputStream = null;
			try {
				response.setHeader("Content-Type", "text/html;   charset=UTF-8");
				outputStream = response.getOutputStream();
				XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat()
						.setEncoding("UTF-8"));
				xmlOut.output(document, outputStream);
				outputStream.flush();
			}
			catch (IOException ex) {
				throw ex;
			} finally {
				outputStream.close();
			}
		}
	}

	/**
	 *<p>
	 * 输出字符串流数据
	 * </p>
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param str
	 *            输出的字符串流
	 * @throws IOException
	 * @Author DUAN.GAOHUI AT TIME : 2009-8-31 下午07:54:31
	 */
	public void outPrintStream(HttpServletResponse resp, String str)
			throws IOException {
		{
			
			PrintWriter outWriter = null;
			try{
			resp.setContentType("text/xml;charset=UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			outWriter = resp.getWriter();
			outWriter.print(str);
			outWriter.flush();
			}catch (IOException ex) {
				throw ex;
			} finally {
			outWriter.close();
			}
		}
	}

	/**
	 * 输出文件 dd by chenjia 2009.8.18
	 * **/
	public String outFile(String fileName, String contentType) {
		String p = this.getRequest().getRealPath("/template") + "\\" + fileName;
		File file = new File(p);
		if (file.exists()) {
			this.getResponse().resetBuffer();
			this.getResponse().reset();
			this.getResponse().setContentType(contentType);
			this.getResponse().addHeader("Content-Length",
					String.valueOf(file.length()));
			try {
				byte[] bytes = fileName.getBytes();
				String encoded = new String(bytes, "ISO-8859-1");
				this.getResponse().addHeader("Content-Disposition",
						"attachment; filename=" + encoded);
				FileInputStream fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, this.getResponse().getOutputStream());
				// this.getResponse().getOutputStream ().flush ();
				// this.getResponse().getOutputStream ().close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return SUCCESS;
	}

	// -----------------暴露本地 request 和 response 的方法----------------
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

}
