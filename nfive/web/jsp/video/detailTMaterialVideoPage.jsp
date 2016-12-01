<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 

<!DOCTYPE html>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMaterialVideoFrom" name="editTMaterialVideoFrom" method="post" action="editTMaterialVideoAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>视频名称:</label></th>
							<td colspan="1">
								${out_tMaterialVideoDto.video_name}
							</td>
							<th class="wd-20"><label>是否缓存:</label></th>
							<td colspan="1">
								${out_tMaterialVideoDto.cache_flag}
							</td>
						</tr>
						<tr>
							<th class="wd-20" ><label>视频描述:</label></th>
							<td  colspan="3" style="width: 500px;height:37px;word-break: break-all;word-wrap: break-word;">
									${out_tMaterialVideoDto.video_desc}								
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>创建人:</label></th>
							<td class="wd-30">
								${out_tMaterialVideoDto.created_user_cd}
							</td>
							<th class="wd-20"><label>创建时间:</label></th>
							<td class="wd-30">
								<fmt:formatDate value='${out_tMaterialVideoDto.created_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>更新人:</label></th>
							<td>
								${out_tMaterialVideoDto.updated_user_cd}
							</td>
							<th class="wd-20"><label>最后更新时间:</label></th>
							<td>
								<fmt:formatDate value='${out_tMaterialVideoDto.updated_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>视频地址:</label></th>
							<td  colspan="3">
								${out_tMaterialVideoDto.video_url}
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>缩略图地址:</label></th>
							<td  colspan="3">
								${out_tMaterialVideoDto.pic_thumburl}
							</td>
						</tr>
						<tr>
							<td  colspan="3">
								<div class="easyui-panel" id="localVideo" class="easyui-panel" style="width:415px;height: 226px;text-align: center;" data-options="" >		 	
							 	</div>
							</td>
							<td  colspan="3">
								<div class="easyui-panel" class="easyui-panel" style="width:300px;height: 226px;text-align: center;" data-options="" >	
											<c:if test="${not empty out_tMaterialVideoDto.pic_thumburl}">
									 			<img id="img" alt="图片不能正常显示" src="${out_tMaterialVideoDto.pic_thumburl}" width="200" height="200">
									 		</c:if>
									 		<c:if test="${empty out_tMaterialVideoDto.pic_thumburl}">
									 			<span>该视频没有上传缩略图</span>
									 		</c:if> 	
							 	</div>
							</td>
						</tr>												
					</table>
				</div>
			</form>
			<form id="delTMaterialVideoFrom" name="delTMaterialVideoFrom" method="post" action="delTMaterialVideoAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${out_tMaterialVideoDto.video_id}" />
						<input type="hidden" id="file_id" name="file_id" value="${out_tMaterialVideoDto.file_id}" />
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
		//*****     初始化预览视频    *****//
		$(document).ready(function(){
					var videoSrc ='${out_tMaterialVideoDto.video_url}';
					//ie
			       	if (window.navigator.userAgent.indexOf("MSIE") >= 0) { 
				      	//预览本地文件
				    	$("#localVideo").html('<object width="100%" height="100%" type="video/x-ms-asf" ' 
				    								+' url="'+videoSrc+'" data="'+videoSrc+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">'
													+' <param id="param_url" name="url" value="'+videoSrc+'">'
													+' <param id="param_filename" name="filename" value="'+videoSrc+'" >'
													+' <param name="autostart" value="0">'
													+' <param name="uiMode" value="full" />'
													+' <param name="autosize" value="1">'
													+' <param name="playcount" value="1">'
													+' <embed type="application/x-mplayer2" src="'+videoSrc+'" width="100%" height="100%" ' 
													+' autostart="true" showcontrols="true" '
													+' pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"></embed></object>');	
		    		//支持HTML5标签的 浏览器
		    		} else {
				    		$('#localVideo').panel({content:'<video src="'+videoSrc+'" height="220px;" controls="controls">'
							+'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'}); 
		    		}
		});
</script>