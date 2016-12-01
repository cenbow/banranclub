<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ include file="../commonselect/commonTMaterialSelect.jsp"%>
<style>
.stRed{color: red;}
.keyDiv{display: none;}
.borDiv{border: 1px solid blue;height: 300px;margin-top: 5px;}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="detailkeywordFrom" name="detailkeywordFrom" method="post" action="">
				<div class="search-panel-bd">
					<input type="hidden" id="tReplySpecialDto.freply_id" name="tReplySpecialDto.freply_id" value="${tReplySpecialDto.freply_id}" />
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>回复规则：</label></th>
							<td>
								${tReplySpecialDto.rule_type}
							</td>
							<th class="wd-20"><label>启用标志:</label></th>
							<td>
							${tReplySpecialDto.effect_flag} 
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>回复类型:</label></th>
							<td  colspan="3">
						      ${reply_type} 
							</td>
					</tr>
						<tr>					
							<th class="wd-10"><label>回复内容</label></th>
								<td colspan="3">
									<div id="reply_div">
									</div>
								</td>
						</tr>
						<tr>
							<th class="wd-20"><label>创建人:</label></th>
							<td>
							${tReplySpecialDto.created_user_cd}
							</td>	
							<th class="wd-20"><label>创建时间:</label></th>
							<td>
								<fmt:formatDate value='${tReplySpecialDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>
						    </td>
						    </tr>
						    <tr>
							<th class="wd-20"><label>更新人</label></th>
							<td>
							${tReplySpecialDto.updated_user_cd}
							</td>	
							<th class="wd-20"><label>最后更新时间:</label></th>
							<td>
						     <fmt:formatDate value='${tReplySpecialDto.updated_date}' type='date'  pattern='yyyy-MM-dd'/>
						     </td>
						</tr>
				  </table>
				</div>
			</form>
			 <form id="delTReplySpecialFrom" name="delTReplySpecialFrom" method="post" action="delTReplySpecialAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tReplySpecialDto.freply_id}" />
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var  tag_id = 'reply_div';//DIV名称
		var  combox_value = '${tReplySpecialDto.reply_type}';//回复类型KEY
		var  material_id = '${tReplySpecialDto.material_id}';//素材名称
		var  material_name = '${material_name}';//素材名称
		var  material_url = '${material_url}';//素材路径
		var  material_tmpFlag = '${tReplySpecialDto.templet_flag}';//是否动态模板
		var  text_msg  = decodeURIComponent("${tReplySpecialDto.text_msg}") ;//文本内容
		$("#preview").css("width","800");//设置面板大小
		CommonSelect.initDetail(tag_id,combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg);
	})
</script>