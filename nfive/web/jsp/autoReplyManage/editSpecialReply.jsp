<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<style>
.stRed{color: red;}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTReplySpecialFrom" name="editTReplySpecialFrom" method="post" action="editTReplySpecialAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tReplySpecialDto.freply_id" name="tReplySpecialDto.freply_id" value="${tReplySpecialDto.freply_id}" />
					<table class="search-table">
						 <tr>
							<th class="wd-20"><label>回复规则<span class="stRed">*</span>：</label></th>
							<td>
								<ldui:select items="${rule_typeList}" class="easyui-combobox" id="edit_rule_type" name="tReplySpecialDto.rule_type" required="true" validType="select['#edit_rule_type']" invalidMessage="该值不能为空" missingMessage="请填写此值" style="width:150px;"/>
								
							</td>
						 
							<th class="wd-20"><label>启用标志<span class="stRed">*</span>:</label></th>
							<td>
							<ldui:select items="${effect_flagList}" class="easyui-combobox"  id="edit_effect_flag" name="tReplySpecialDto.effect_flag"  required="true" validType="select['#edit_effect_flag']" invalidMessage="该值不能为空" missingMessage="请填写此值"  style="width:150px;"/>	
							</td>
						</tr>
							<tr>
						    <th class="wd-20"><label>回复类型<span class="stRed">*</span>:</label></th>
							<td colspan="3">
							<ldui:select items="${reply_typeList}"  class="easyui-combobox" id="edit_reply_type" name="tReplySpecialDto.reply_type"  required="true" validType="select['#edit_reply_type']" invalidMessage="该值不能为空" missingMessage="请填写此值"  data-options="onSelect:keySelect"  style="width:150px;"/>
								</td>
							 </tr>	

					</table>
         		<%@ include file="../commonselect/commonTMaterialSelect.jsp"%>
				</div>
			</form>
			<form id="delTReplySpecialFrom" name="delTReplySpecialFrom" method="post" action="delTReplySpecialAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tReplySpecialDto.freply_id}" />
						<input type="hidden" id="edit_rule_type" name="edit_rule_type" value="${tReplySpecialDto.rule_type}"/>
					   <input type="hidden" id="effect_flag" name="effect_flag" value="${tReplySpecialDto.effect_flag}" />
			</form>
		</div>
	</div>
</div>
	<script  type="text/javascript">
		//页面加载绑定事件
		$(document).ready(function(){
			$("#tReplySpecialDto.reply_type").combobox({
				onSelect:keySelect
			})	
		var  combox_value = '${tReplySpecialDto.reply_type}';//回复类型KEY
		var  material_id = '${tReplySpecialDto.material_id}';//素材id
		var  material_name = '${material_name}';//素材名称
		var  material_url = '${material_url}';//素材路径
		var  material_tmpFlag = '${tReplySpecialDto.templet_flag}';//是否动态模板

		var  text_msg  = decodeURIComponent("${tReplySpecialDto.text_msg}");
			$('#material_id_hid').attr("name","tReplySpecialDto.material_id");
			$('#text_msg_hid').attr("name","tReplySpecialDto.text_msg");
			$('#templet_flag_hid').attr("name","tReplySpecialDto.templet_flag");
		CommonSelect.initEdit(combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg);
		});
	</script>