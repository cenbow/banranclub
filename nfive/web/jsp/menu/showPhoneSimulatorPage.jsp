<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<%
	String tempPath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();	
	String tempCssPath = tempPath + "/css";
%>
<link rel="stylesheet" type="text/css" href="<%=tempCssPath%>/preview-article-group.css">
<div class="content-body" id="preview">
	<div class="toggle-panel" style="margin:25px 20px 20px 20px;">
		<c:if test="${action_type == '501100000003'}">
			<!-- 文本内容 -->
			<div class="search-panel" style="padding:5px 5px 5px 5px;width: 200px;word-wrap: break-word; word-break: normal;">
				${text_msg}
			</div>
		</c:if>
		<c:if test="${action_type == '501100000004'}">
			<!-- 图片信息 -->
			<div class="search-panel search-panel-content" style="padding:1px 1px 1px 1px;width:150px;" align="center">
				<img alt="图片无法显示" width="100%" src="${picture_url}"></img>
			</div>
		</c:if>
		<c:if test="${action_type == '501100000005'}">
			<!-- 音频信息 -->
			<div class="search-panel-content" align="center">
				<audio controls="controls" src="${media_url}" >您的浏览器不支持音频播放</audio>
			</div>
		</c:if>
		<c:if test="${action_type == '501100000006'}">
			<!-- 视频信息 -->
			<div class="search-panel-content" align="center">
				<video controls>
					<source src="${media_url}" type="video/ogg"/>
					<source src="${media_url}" type="video/mp4"/>您的浏览器不支持视频播放
				</video>
			</div>
		</c:if>
	</div>
</div>
<script type="text/javascript">
  <c:if test="${action_type == '501100000002'}">
	// 图文素材显示
	//页面加载绑定事件
	$(document).ready(function(){
		//设置图文组预览内容
		$.ajax({
			url : "showArtcleGroupAction.action",
			post: 'post',
			data: {"tArticleRelaQueryBean.article_group_id" : '${material_id}',
				   "articleGroupVo.article_type" : '${article_type}'},
			success: function(data){
				$("#preview").html("");	//清空info内容
				data = JSON.parse(data);	
				$("#preview").html(data.html);
			}
		});
	});
  </c:if>
</script>