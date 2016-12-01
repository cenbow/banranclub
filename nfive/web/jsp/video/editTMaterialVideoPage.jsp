<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
	.stRed{color: red}
</style>
<!DOCTYPE html>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMaterialVideoFrom" name="editTMaterialVideoFrom" method="post" action="editTMaterialVideoAction.action" enctype="multipart/form-data">
				<div class="search-panel-bd">
							<input type="hidden" id="tMaterialVideoDto.video_id" name="in_tMaterialVideoDto.video_id" value="${out_tMaterialVideoDto.video_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-15"><label>视频名称</label><span class="stRed">*</span></th>
									<td>
										<input style="width: 200px;" type="text" id="tMaterialVideoDto.video_name" name="in_tMaterialVideoDto.video_name" value="${out_tMaterialVideoDto.video_name}" class="easyui-validatebox" maxlength="50" data-options="validType:'length[0,50]',required:true,invalidMessage:'视频名称不能超过50个字符',missingMessage:'视频名称不能为空'"/>
									</td>
									<th class="wd-15"><label>是否缓存</label><span class="stRed">*</span></th>
									<td>
										<ldui:select items="${cache_flag_Select}" id="edit_is_cache_Select" name="in_tMaterialVideoDto.cache_flag" class="easyui-combobox" value="${out_tMaterialVideoDto.cache_flag}"
										 	 style="width: 200px;" required="true" validType="select['#edit_is_cache_Select']" invalidMessage="是否缓存不能为空" missingMessage="请填写是否缓存" />
									</td>
								  </tr>
								 <tr>
									<th class="wd-15"><label>视频描述</label></th>
									<td colspan="3">
										<textarea style="width:577px; max-width: 610px;max-height: 50px;height: 30px;" 
										class="easyui-validatebox" id="in_tMaterialVideoDto_video_desc" name="in_tMaterialVideoDto.video_desc"  
										data-options="validType:'length[0,100]',invalidMessage:'视频描述不能超过100个字符！'">${out_tMaterialVideoDto.video_desc}</textarea>
									</td>
								  </tr>
								   <tr>
									<th colspan="2"><label>视频<span class="stRed">*</span></label>
									</th>
									<th  colspan="2"><label>缩略图</label>
									</th>
								  </tr>
								   <tr>
									<td colspan="2" style="height: 220px;">
									 	<s:file theme="simple"  id="videoFile" name="videoFile" onchange="setVideoPreview();" ></s:file><br /><label style="float: right;margin-right: 5px;">文件限制：格式只能为mp4/MP4，大小不超过10M</label></br>
									 	<div class="easyui-panel" id="localVideo" style="text-align: center; width: 385px;height: 200px;border:solid 1px #95B8E7;">
									 	</div>
									 </td>
									  <td colspan="2" style="height: 220px;">
									 	<s:file theme="simple"  id="thumbFile" name="thumbFile" onchange="setThumbPreview();" ></s:file><br /><label style="float: right;margin-right: 5px;">缩略图文件限制：格式只能为jpg/JPG，大小不超过1MB</label></br>
									 	<div class="easyui-panel" id="localThumb" style="text-align: center; width: 345px;height: 200px;border:solid 1px #95B8E7;">
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
		    		$('#localVideo').panel({content:'<video src="'+videoSrc+'" height="190px;" controls="controls">'
					+'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'}); 
    		}
});


//选择视频预览
function setVideoPreview() {  
			var docObj=document.getElementById("videoFile"); 
    		var videoObjPreview=document.getElementById("preview"); 
    		 
    		// 文件格式校验
	    	if( !docObj.value.match( /.mp4|.MP4/i ) ){  
	   			$.messager.alert('提示','视频格式无效，请上传mp4格式'); 
	   			docObj.value=''; 
	   			return false;  
	  		} 
	  		
			//ie
	       	if (window.navigator.userAgent.indexOf("MSIE")>= 0) {
		        docObj.select(); 
		        docObj.blur();
		      	videoSrc =  document.selection.createRange().text; 
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
		    		if(docObj.files != 'undefined' && docObj.files[0] != 'undefined'){
		    		
		    			if(docObj.files[0].size>10*1024*1024){					// 支持html5标签的文件大小校验
			    			$.messager.alert("提示","上传视频文件不得大于10MB!");
			    			docObj.value=''; 
			    			return false;
		    			} 
		    			
		    			videoSrc=window.URL.createObjectURL(docObj.files[0]);
			    		$('#localVideo').panel({content:'<video src="'+videoSrc+'" height="190px;" controls="controls">'
						+'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'}); 
		    		} 
    		}
 	}
 	
 	
 		//设置缩略图本地预览   
	function setThumbPreview() {  
		var docObj=$("#thumbFile")[0];  							//获取文件对象
    	var thumbObjPreview=document.getElementById("preview");   //预览块
    	
    	// 文件格式校验
    	if( !docObj.value.match( /.jpg|.JPG/i ) ){  
   			$.messager.alert('提示','缩略图格式无效！');  
   			docObj.value=''; 
   			return false;  
  		}    	
    	
    	var src;
    	if(docObj.files && docObj.files[0]){ 	
    		
    		if(docObj.files[0].size > 1024*1024){					// 支持html5标签的文件大小校验
    			$.messager.alert("提示","缩略图大小不能超过1MB！");
    			docObj.value=''; 
    			return false;
    		}   		
    		
    		src=window.URL.createObjectURL(docObj.files[0]); 		//支持html5标签的本地预览
    		$('#localThumb').html('<img id="img" alt="图片不能正常显示" src="'+src+'" width="200" height="200">');
    	}else{  
    		
    		// IE10以下版本浏览器文件大小校验										
			var thumbObj=new Image();   //建立一个图像对象	
			thumbObj.src=$("#thumbFile").val();
			thumbObj.onreadystatechange=function(){
				if(thumbObj.readyState=="complete")
				{
					var thumbFileSize=Math.round(thumbObj.fileSize/1024*100)/100;//取得图片文件的大小		
					if(thumbFileSize!=0){
						if(thumbFileSize > 1024*1024){
							$.messager.alert("提示","缩略图文件大小不能超过1MB！");
							docObj.value=''; 
							return false;
						}
						if( !docObj.value.match( /.jpg|.JPG/i ) ){  
	   						$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
	   						docObj.value=''; 
	   						return false;  
	  					}	
					}																																	}											
			}
    	
       		//IE10以下，使用滤镜 制作本地预览
        	docObj.select(); 
        	docObj.blur();
			var thumbSrc = document.selection.createRange().text;
			var localThumbId = document.getElementById("localThumb");  
        	  
        	//图片异常的捕捉，防止用户修改后缀来伪造图片  
        	try{  
           		localThumbId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
            	localThumbId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = thumbSrc;             							
            	
        	}catch(e){  
           		alert("您上传的图片格式不正确，请重新选择!");  
           		docObj.value=''; 
            	return false;  
       		 }  
        	thumbObjPreview.style.display = 'none';  
        	document.selection.empty();  
   	 	} 
   	 	
 	}
</script>