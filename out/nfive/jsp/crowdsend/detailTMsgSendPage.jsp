<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commonselect/commonTMaterialSelect.jsp"%> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMsgSendFrom" name="editTMsgSendFrom" method="post" action="editTMsgSendAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>批次号</label></th>
								<td class="wd-30">
									${tMsgSendDto.batch_no}
								</td>
								<th class="wd-20"><label>发送状态</label></th>
								<td class="wd-20">
									${tMsgSendDto.send_status}
								</td>
							  </tr>
							 
							 <tr>
								<th class="wd-20"><label>群发区分</label></th>
								<td>
									${tMsgSendDto.send_dist}
								</td>
							    <th class="wd-20"><label>群发对象</label></th>
								<td>
									${tMsgSendDto.send_target}
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>群发接口</label></th>
								<td>
									${tMsgSendDto.send_if}
								</td>
								<th class="wd-20"><label>消息类型</label></th>
								<td>
									${tMsgSendDto.fans_group_flg}
								</td>
							  </tr>
							  <tr>
								<th class="wd-20"><label>发送人</label></th>
								<td>
									${tMsgSendDto.created_user_cd}
								</td>
								<th class="wd-20"><label>发送时间</label></th>
								<td>
									<fmt:formatDate value='${tMsgSendDto.send_time}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
									
								</td>
							  </tr>
							  <tr>
								<th class="wd-20"><label>分组名称</label></th>
								<td colspan="3">
									${tMsgSendDto.activity_group_id}
								</td>
							  </tr>
							  <tr>
								<th class="wd-20"><label>消息备注</label></th>
								<td colspan="3">
									${tMsgSendDto.remark}
								</td>
							  </tr>
							 <tr>
							 	<td colspan="4">
							 		<div id="show" style="width: 816px;height: 300px;"></div>
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
		
		$("#templet_flag_TextArea").attr("disabled",true);		//设置文本编辑器只读模式
		
		$("#media_name").css("width","560px");
		$("#preview").css("width","810px");
		$("#other_msg").css("height","290px");
		$("#preview").css("height","258px");
		
		$("#text_msg").css("margin-bottom","0px");
		$("#text_msg").css("line-height","25px");
		CommonSelect.initDetail('show','${tMsgSendDto.msg_type}','${tMsgSendDto.material_id}','${material_name}','${material_url}','${tMsgSendDto.templet_flag}',decodeURIComponent('${tMsgSendDto.text_msg}','UTF-8'));
		
		$("video").css("height","100px");
		if('' == '${material_name}' || null == '${material_name}'){
			$('#show').css('text-align','center').html('<span style="line-height: 300px;">该资源已被删除无法提供预览</span>');
		}
	})
	
</script>