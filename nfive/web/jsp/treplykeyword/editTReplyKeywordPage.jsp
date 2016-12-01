<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTReplyKeywordFrom" name="editTReplyKeywordFrom" method="post" action="editTReplyKeywordAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tReplyKeywordDto.kreply_id" name="in_tReplyKeywordDto.kreply_id" value="${out_tReplyKeywordDto.kreply_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>关键字</label><span class="stRed">*</span></th>
									<td>
										<input type="text" maxlength="50" id="tReplyKeywordDto.keyword" name="in_tReplyKeywordDto.keyword" value="${out_tReplyKeywordDto.keyword}" class="easyui-validatebox" data-options="validType:'length[0,50]',required:true,invalidMessage:'长度不能大于50',missingMessage:'关键字不能为空'" style="width:200px;"/>
									</td>
									<th class="wd-20"><label>匹配类型</label><span class="stRed">*</span></th>
									<td>
										<ldui:select items="${match_SelList}" id="search_match_type" name="in_tReplyKeywordDto.match_type" class="easyui-combobox" style="width:200px;" 
										required="true" validType="select['#search_match_type']" invalidMessage="匹配类型不能为空" missingMessage="请填写匹配类型" />
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>回复类型</label><span class="stRed">*</span></th>
									<td>
										<ldui:select items="${reply_SelList}" id="search_reply_type" name="in_tReplyKeywordDto.reply_type" class="easyui-combobox" style="width:200px;" 
										required="true" validType="select['#search_reply_type']" invalidMessage="回复类型不能为空" missingMessage="请填写回复类型" />
									</td>
									<th class="wd-10"><label>启用标志</label><span class="stRed">*</span></th>
										<td>
											<ldui:select items="${effect_flag_SelList}" id="search_effect_flag_select" name="in_tReplyKeywordDto.effect_flag" class="easyui-combobox" style="width:200px;" 
											required="true" validType="select['#search_effect_flag_select']" invalidMessage="启用标志不能为空" missingMessage="请填写启用标志" />
									</td>
								  </tr>
								   <tr>
						  			<th class="wd-20"><label>发布期间<span class="stRed">*</span></label></th>
									<td >
										<input type="text"  class="easyui-datebox" id="edit_pub_startdate" name="in_tReplyKeywordDto.pub_startdate" value="<fmt:formatDate value='${out_tReplyKeywordDto.pub_startdate}' type='date'  pattern='yyyy-MM-dd'/>"  required="true"  style="width:120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
										<input type="text"  class="easyui-datebox" id="edit_pub_enddate" name="in_tReplyKeywordDto.pub_enddate" validType="minFirstDate['#edit_pub_startdate']" value="<fmt:formatDate value='${out_tReplyKeywordDto.pub_enddate}' type='date'  pattern='yyyy-MM-dd'/>" required="true"  style="width:120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
									</td>
									<th class="wd-10"><label>启用客服模式</label><span class="stRed">*</span></th>
										<td>
											<ldui:select items="${cust_srv_flag_SelList}" id="search_cust_srv_flag_select" name="in_tReplyKeywordDto.cust_srv_flag" class="easyui-combobox" style="width:200px;" 
											required="true" validType="select['#search_cust_srv_flag_select']" invalidMessage="启用客服模式标志不能为空" missingMessage="请填写启用客服模式标志" />
									</td>
						  			</tr>
						</table>
				 			 <%@ include file="../commonselect/commonTMaterialSelect.jsp"%> 
				</div>
			</form>
			<form id="delTReplyKeywordFrom" name="delTReplyKeywordFrom" method="post" action="delTReplyKeywordAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${out_tReplyKeywordDto.kreply_id}" />
			</form>
		</div>
	</div>
</div>
	<script  type="text/javascript">
		//页面加载绑定事件
		$(document).ready(function(){
		
		//定义清空日期
		$(".icon-clear-date").mousedown(function(){
			var id=$(this).prev().prev().attr("id");
			$("#"+id).datebox("setValue","");
	    	$("#"+id).datebox("reset");
		});
		
		var  material_id = '${out_tReplyKeywordDto.material_id}';//素材ID
		var  combox_value = '${out_tReplyKeywordDto.reply_type}';//回复类型KEY
		var  material_name = '${material_name}';//素材名称
		var  material_url = '${material_url}';//素材路径
		var  material_tmpFlag = '${out_tReplyKeywordDto.templet_flag}';//是否动态模板
		var  text_msg  = decodeURIComponent('${out_tReplyKeywordDto.text_msg}','UTF-8');//文本内容
		
		CommonSelect.initEdit(combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg);
		
		//设置隐藏标签的name
	  	$("#material_id_hid").attr("name","in_tReplyKeywordDto.material_id");//素材ID
	  	$("#templet_flag_hid").attr("name","in_tReplyKeywordDto.templet_flag");//是否动态模板
	  	$("#text_msg_hid").attr("name","in_tReplyKeywordDto.text_msg")//设置隐藏文本NAME
	  	$("#preview").css("width","910");//设置面板大小
	  	
	  	$("#material_id_hid").val('${out_tReplyKeywordDto.material_id}');//设置素材ID值
	  	
		//绑定切换效果事件
		$("#search_reply_type").combobox({
			onSelect:keySelect
		})
		
		
		});
	</script>