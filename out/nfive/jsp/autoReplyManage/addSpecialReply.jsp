<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<style>
.stRed{color: red;}
.keyDiv{display: none;}
.borDiv{border: 1px solid blue;height: 300px;margin-top: 5px;}
</style>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTReplySpecialFrom" name="addTReplySpecialFrom" method="post" action="addTReplySpecialAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>回复规则<span class="stRed">*</span>：</label></th>
							<td>
							<ldui:select items="${rule_typeList}" class="easyui-combobox" id="add_rule_type" name="tReplySpecialDto.rule_type" required="true" validType="select['#add_rule_type']" invalidMessage="该值不能为空" missingMessage="请填写此值" style="width:150px;"/>
							</td>
							<th class="wd-20"><label>启用标志<span class="stRed">*</span>:</label></th>
							<td>
							<ldui:select items="${effect_flagList}" class="easyui-combobox"  id="add_effect_flag" name="tReplySpecialDto.effect_flag" required="true" validType="select['#add_effect_flag']" invalidMessage="该值不能为空" missingMessage="请填写此值" style="width:150px;"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>回复类型<span class="stRed">*</span>:</label></th>
							<td colspan="3">
								<ldui:select items="${reply_typeList}" class="easyui-combobox" id="add_reply_type" name="tReplySpecialDto.reply_type" style="width:150px;" required="true" validType="select['#add_reply_type']" invalidMessage="该值不能为空" missingMessage="请填写此值"  data-options="onSelect:keySelect"/>
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
	//页面加载绑定事件
	$(document).ready(function(){
		$("#tReplySpecialDto.reply_type").combobox({
			onSelect:keySelect
		});
		$('#material_id_hid').attr("name","tReplySpecialDto.material_id");
		$('#text_msg_hid').attr("name","tReplySpecialDto.text_msg");
		$('#templet_flag_hid').attr("name","tReplySpecialDto.templet_flag");
		changePage('505200000002');
	});
</script>