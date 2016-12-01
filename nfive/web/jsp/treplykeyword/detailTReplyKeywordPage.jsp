<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ include file="../commonselect/commonTMaterialSelect.jsp"%> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTReplyKeywordFrom" name="editTReplyKeywordFrom" method="post" action="editTReplyKeywordAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-10"><label>关键字</label></th>
								<td class="wd-30">
									${out_tReplyKeywordDto.keyword}
								</td>
								
								<th class="wd-10"><label>匹配类型</label></th>
								<td class="wd-30">
									${out_tReplyKeywordDto.match_type}
								</td>
							  </tr>
							 <tr>
								<th class="wd-10"><label>回复类型</label></th>
								<td>
									${reply_type_text}
								</td>
								<th class="wd-10"><label>启用标志:</label></th>
								<td>
									${out_tReplyKeywordDto.effect_flag}
								</td>
							  </tr>
							  <tr>
								<th class="wd-10"><label>发布期间:</label></th>
								<td>
								<fmt:formatDate value='${out_tReplyKeywordDto.pub_startdate}' type='date'  pattern='yyyy-MM-dd'/>&nbsp;~&nbsp;
                                <fmt:formatDate value='${out_tReplyKeywordDto.pub_enddate}' type='date'  pattern='yyyy-MM-dd'/>
								</td>
								<th class="wd-10"><label>启用客服模式:</label></th>
								<td>
									${out_tReplyKeywordDto.effect_flag}
								</td>
							  </tr>
							 <tr>
								<th class="wd-10"><label>回复内容</label></th>
								<td colspan="3">
									<div id="reply_div" style="border: 1px solid gray;">
									</div>
								</td>
							  </tr>
							
							 <tr>
								<th class="wd-10"><label>创建人</label></th>
								<td>
									${out_tReplyKeywordDto.created_user_cd}
								</td>
								<th class="wd-10"><label>创建时间</label></th>
								<td>
									<fmt:formatDate value='${out_tReplyKeywordDto.created_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
								</td>
							  </tr>
							  <tr>
							 	 <th class="wd-10"><label>更新人</label></th>
								<td>
									${out_tReplyKeywordDto.updated_user_cd}
								</td>
								<th class="wd-10"><label>最后更新时间</label></th>
								<td>
									<fmt:formatDate value='${out_tReplyKeywordDto.updated_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
								</td>
							 </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
	
		var  tag_id = 'reply_div';//DIV名称
		var  combox_value = '${out_tReplyKeywordDto.reply_type}';//回复类型KEY
		var  material_id = '${out_tReplyKeywordDto.material_id}'//素材ID
		var  material_name = '${material_name}';//素材名称
		var  material_url = '${material_url}';//素材路径
		var  material_tmpFlag = '${out_tReplyKeywordDto.templet_flag}';//是否动态模板
		var  text_msg  = decodeURIComponent('${out_tReplyKeywordDto.text_msg}','UTF-8');//文本内容
		
		$("#preview").css("width","800");//设置面板大小
		
		CommonSelect.initDetail(tag_id,combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg);
		
		
	})
</script>