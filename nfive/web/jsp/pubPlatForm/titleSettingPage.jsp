<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String basePath = path + "/";
	String jsPath = path + "/js";
%>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="titleSettingForm" name="titleSettingForm" method="post" action="titleSettingAction.action">
			<input type="hidden" id="tPubPlatformDto_platform_id" name="tPubPlatformDto.platform_id" value="${tPubPlatformDto.platform_id}" />
				<div class="search-panel-bd">
					<table class="search-table">
						 <tr>
							<th class="wd-20"><label>是否显示标题</label></th>
							<td>
								<input type="checkbox" name="is_display_title_check" />是
								<input type="hidden" id="tPubPlatformDto_is_display_title" name="tPubPlatformDto.is_display_title" />
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>显示时间</label></th>
							<td>
								<input type="radio" name="tPubPlatformDto.display_time" value="created_date"/>创建时间
								<input type="radio" name="tPubPlatformDto.display_time" value="updated_date"/>更新时间
								<input type="radio" name="tPubPlatformDto.display_time" value="system_date"/>当前时间
							</td>
						  </tr>
						  <tr>
						  		<td colspan="2">说明：以下四个项目仅允许修改样式。{title}、{author}、{time}、{platform_name}分别代表标题、作者、时间和公众号名称。</td>
						  </tr>
						  <tr>
					  		   <th class="wd-10"><label>样式设置</label></th>
                               <td>
                                	<script id="editor" type="text/plain" style="width:100%;height:180px;"></script>
                                	<input type="hidden" id="tPubPlatformDto_title_style" name="tPubPlatformDto.title_style" />
                               </td>
						  </tr>
					</table>
					<br/>
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>效果展示</label></th>
							<td width="20%"><input type="button" id="display" value="显示设置效果"/></td>
							<td>
								<div id="title_style"></div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
var ue = UE.getEditor('editor', {
 	zIndex:'10000' //初始化选项
});
 
ue.ready(function() {
	if("${tPubPlatformDto.title_style}" == ""){
		UE.getEditor('editor').setContent("<p>{title}</p><p>{time}&nbsp;&nbsp;&nbsp;{author}&nbsp;&nbsp;&nbsp;{platform_name}</p>");
	}else{
		UE.getEditor('editor').setContent(decodeURIComponent("${tPubPlatformDto.title_style}"));
	}
}); 
//是否显示标题
if("${tPubPlatformDto.is_display_title}"=="100000000001"){
	$('input[name="is_display_title_check"]').attr("checked","checked");
}
//显示时间
if("${tPubPlatformDto.display_time}"=="created_date"){
	$('input[name="tPubPlatformDto.display_time"]').eq(0).attr("checked","checked");
}
if("${tPubPlatformDto.display_time}"=="updated_date"){
	$('input[name="tPubPlatformDto.display_time"]').eq(1).attr("checked","checked");
}
if("${tPubPlatformDto.display_time}"=="system_date"){
	$('input[name="tPubPlatformDto.display_time"]').eq(2).attr("checked","checked");
}

//点击显示设置效果按钮
$("#display").click(function(){
	var style = UE.getEditor('editor').getContent();
	var content = style.replace("{title}","标题").replace("{time}","2014-11-01")
		.replace("{author}","作者").replace("{platform_name}","公众号");
	$("#title_style").html(content);
});

</script>
