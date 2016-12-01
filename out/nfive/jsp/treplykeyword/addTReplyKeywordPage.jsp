<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTReplyKeywordFrom" name="addTReplyKeywordFrom" method="post" action="addTReplyKeywordAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>关键字</label><span class="stRed">*</span></th>
							<td>
								<input type="text" id="tReplyKeywordDto.keyword" name="in_tReplyKeywordDto.keyword" class="easyui-validatebox" maxlength="50" data-options="validType:'length[0,50]',required:true,invalidMessage:'长度不能大于50',missingMessage:'关键字不能为空'" style="width:200px;"/>
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
								<ldui:select items="${reply_SelList}" id="search_reply_type" name="in_tReplyKeywordDto.reply_type" class="easyui-combobox" style="width:200px;" required="true" validType="select['#search_reply_type']" invalidMessage="回复类型不能为空" missingMessage="请填写回复类型" />
							</td>
							<th class="wd-10"><label>启用标志</label><span class="stRed">*</span></th>
								<td>
									<ldui:select items="${effect_flag_SelList}" id="search_effect_flag_select" name="in_tReplyKeywordDto.effect_flag" class="easyui-combobox" style="width:200px;" required="true" validType="select['#search_effect_flag_select']" invalidMessage="启用标志不能为空" missingMessage="请填写启用标志"/>
								</td>
						  </tr>
						  <tr>
				  				<th class="wd-20"><label>发布期间<span class="stRed">*</span></label></th>
							<td>
								<input type="text"  class="easyui-datebox" id="add_pub_startdate" name="in_tReplyKeywordDto.pub_startdate" value="<fmt:formatDate value='${out_tReplyKeywordDto.pub_startdate}' type='date'  pattern='yyyy-MM-dd'/>"  data-options="required:true"  style="width:120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
								<input type="text"  class="easyui-datebox" id="add_pub_enddate" name="in_tReplyKeywordDto.pub_enddate" validType="minFirstDate['#add_pub_startdate']" value="<fmt:formatDate value='${out_tReplyKeywordDto.pub_enddate}' type='date'  pattern='yyyy-MM-dd'/>" data-options="required:true"  style="width:120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
							</td>
							<th class="wd-10"><label>启用客服模式</label><span class="stRed">*</span></th>
								<td>
									<ldui:select items="${cust_srv_flag_SelList}" id="search_cust_srv_flag_select" name="in_tReplyKeywordDto.cust_srv_flag" class="easyui-combobox" style="width:200px;" required="true" validType="select['#search_cust_srv_flag_select']" invalidMessage="启用客服模式标志不能为空" missingMessage="请填写启用客服模式标志"/>
								</td>
						  </tr>
				  </table>
				 			 <%@ include file="../commonselect/commonTMaterialSelect.jsp"%> 
				 </div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">

	//页面加载初始化事件
	$(document).ready(function(){
		
		//定义清空日期
		$(".icon-clear-date").mousedown(function(){
			var id=$(this).prev().prev().attr("id");
			$("#"+id).datebox("setValue","");
	    	$("#"+id).datebox("reset");
		});
		
		//设置隐藏标签的name
	  	$("#text_msg_hid").attr("name","in_tReplyKeywordDto.text_msg")//设置隐藏文本NAME
	  	$("#material_id_hid").attr("name","in_tReplyKeywordDto.material_id");//素材ID
	  	$("#templet_flag_hid").attr("name","in_tReplyKeywordDto.templet_flag");//是否动态模板
	  	$("#preview").css("width","910");//设置面板大小
	  	
		//绑定切换效果事件
		$("#search_reply_type").combobox({
			onSelect:keySelect
		})
		changePage('${out_tReplyKeywordDto.reply_type}');
	});
</script>
