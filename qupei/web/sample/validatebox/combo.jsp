<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.leadbank.com.cn/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>模板内容页</title>
</head>


<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
				<div class="search-panel-bd">
				 <table class="search-table">
					<tr>
						<th class="wd-20"><label>Html-属性模式</label><span class="stRed">*</span></th>
						<td>
									 <select id="combo1" class="easyui-combobox" required="true" validType="select['#combo1']" invalidMessage="该值不能为空" missingMessage="请填写此值">
										<option value="">未输入</option>
										<option value="test1">test1</option>
										<option value="test2">test2</option>
									</select>
						</td>			
						<td class="wd-20"><label>可用</label></td>				  
				   </tr>	
				   <tr>
						<th class="wd-20"><label>JSTL-属性模式</label><span class="stRed">*</span></th>
						<td>
								<select id="combo2" class="easyui-combobox" required="true" validType="select['#combo2']" invalidMessage="该值不能为空" missingMessage="请填写此值">
									<option value="">未输入</option>
									<c:forEach var="item" items="${reply_SelList}" varStatus="status"> 	 					
									 <c:if  test="${status.index!=0}">
											<option value="${item.key}" <c:if  test="${item.selected==true}">selected</c:if> >${item.value}</option>
									 </c:if>
									</c:forEach>
								</select>
						</td>
						<td class="wd-20"><label>技巧可用</label></td>
				  </tr>					
				  <tr>
						<th class="wd-20"><label>标签-属性模式</label><span class="stRed">*</span></th>
						<td>
								<ldui:select items="${reply_SelList}" id="combo3" name="combo3" class="easyui-combobox" style="width:200px;" required="true" validType="select['#combo3']" invalidMessage="该值不能为空" missingMessage="请填写此值"/>	
						</td>
						<td class="wd-20"><label>可用</label></td>
				  </tr>
				  
				  
				 <tr>
						<th class="wd-20"><label>html-option模式</label><span class="stRed">*</span></th>
						<td>
								 <select id="combo4" class="easyui-combobox" data-options="required:true,validType:'select[\'#combo4\']', invalidMessage:'该值不能为空',missingMessage:'请填写此值'">
										<option value="">未输入</option>
										<option value="test1">test1</option>
										<option value="test2">test2</option>
								 </select>
						</td>
						<td class="wd-20"><label>可用</label></td>
				  </tr>		
				  
			
				    	  
				  <tr>
						<th class="wd-20"><label>标签-option模式</label><span class="stRed">*</span></th>
						<td>
								<ldui:select items="${reply_SelList}" id="combo5" name="combo5" class="easyui-combobox" style="width:200px;" data-options="required:true,validType:'select[\'#combo5\']', invalidMessage:'该值不能为空',missingMessage:'请填写此值'"/>	
						</td>
						<td class="wd-20"><label>不可用</label></td>
				  </tr>
				  </table>
				 </div>
		</div>
	</div>


<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>