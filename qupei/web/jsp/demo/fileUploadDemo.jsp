<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>文件上传Demo</title>
<body>
<!--菜单-->
<div id="dubboTest" class="container">
	<div class="search-panel-bd">
			<table class="search-table">
				<tr>
					<th class="wd-20"><label>文件上传Demo</label></th>
					<td>
					</td>
				</tr>
			</table>
			<form id="fileUploadFrom" name="fileUploadFrom" method="post" action="fileUploadSubmit.action">
				<input type="hidden" id="addFiles_addModeFileUpload" name="addFiles1"/>
				<input type="hidden" id="delFiles_addModeFileUpload" name="delFiles1"/>

				<input type="hidden" id="addFiles_editModeFileUpload" name="addFiles2"/>
				<input type="hidden" id="delFiles_editModeFileUpload" name="delFiles2"/>
				
				<br/>
				<div style="text-align:center;">
				<input type="submit" name="Submit" value="提交" /> </div>
			</form>
			<br/>
			<br/>
			<br/>
			<span>新增模式</span><br/><br/>
			<%
				java.util.Map<String, Object> parmMap = new java.util.HashMap<String, Object>();
				parmMap.put("namespace","addModeFileUpload");		// 必须，且唯一
				parmMap.put("mode","add");							// 必须，add,edit,view
			%>
			<%@ include file="/jsp/common/fileUpload.jsp"%>
			
			<br/>
			<br/>
			<span>编辑模式    设置为只能上传图片</span><br/><br/>
			<%
				parmMap = new java.util.HashMap<String, Object>();
				parmMap.put("namespace","editModeFileUpload");		// 必须，且唯一
				parmMap.put("mode","edit");							// 必须，add,edit,view
				parmMap.put("referenceCode","referenceCode002");	// edit,view 模式时必须
				parmMap.put("functionCode","functionCode002");		// edit,view 模式时必须
				parmMap.put("acceptFileTypes",new StringBuffer("/(\\.|\\/)(gif|jpe?g|png)$/i"));	// 正则表达式
				parmMap.put("maxFileSize",999120);
			
			%>
			<%@ include file="/jsp/common/fileUpload.jsp"%>
			
			<br/>
			<br/>
			<span>显示模式</span><br/><br/>
			<%
				parmMap = new java.util.HashMap<String, Object>();
				parmMap.put("namespace","viewModeFileUpload");		// 必须，且唯一
				parmMap.put("mode","view");							// 必须，add,edit,view
				parmMap.put("referenceCode","referenceCode002");	// edit,view 模式时必须
				parmMap.put("functionCode","functionCode002");		// edit,view 模式时必须
			
			%>
			<%@ include file="/jsp/common/fileUpload.jsp"%>
    </div>
</div>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>