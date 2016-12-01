<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String jsPath = path + "/js";
%>
<style>
.stRed{color: red;}
.keyDiv{display: none;}
.borDiv{border: 1px solid blue;height: 300px;margin-top: 5px;}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTMaterialVoiceFrom" name="addTMaterialVoiceFrom" method="post" enctype="multipart/form-data" action="addTMaterialVoiceAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>音频名称<span class="stRed">*</span>:</label></th>
							<td>
								<input type="text" id="add_voice_name" name="tMaterialVoiceDto.voice_name" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]',invalidMessage:'音频名称不能超过50个字',missingMessage:'音频名称不能为空'" style="width:200px;"/>
							</td>
							<th class="wd-20"><label>是否缓存<span class="stRed">*</span>:</label></th>
							<td>
							   <ldui:select items="${cache_flagList}" class="easyui-combobox"  id="add_cache_flag" name="tMaterialVoiceDto.cache_flag"  required="true" validType="select['#add_cache_flag']" invalidMessage="该值不能为空" missingMessage="请填写此值"  style="width:140px;"/>	
							</td>						 
						  </tr>
						  	 <tr>
							<th class="wd-20"><label>音频描述</label></th>
							<td colspan="3">
							   <textarea style="width:577px; max-width: 610px;max-height: 80px;height: 60px;" class="easyui-validatebox" id="add_voice_desc" name="tMaterialVoiceDto.voice_desc"  data-options="validType:'length[0,100]',invalidMessage:'音频描述不能超过100个字符！',missingMessage:'音频描述不能为空！'"></textarea>
							</td>					
						</tr>
						  	<tr>
						  	   <th class="wd-20"><label>音频文件<span class="stRed">*</span>:</label></th>	
								<td colspan="4" style="padding-left: 60px;padding-top: 8px;height: 150px;">
								<s:file   theme="simple"  id="voiceFile" name="voiceFile" onchange="setVoicePreview();" ></s:file><label>文件限制：格式只能为mp3/MP3，大小不超过2M</label></br>
							 	<div id="localvioce" style="width:510px;height: 36px;" >							 	
							 		<audio id="add_voiceViewURL" controls="controls" src="">
										    <span id="sound" class="mp3"></span>
								 		</audio>
							        </audio>
							 	</div>
					      	</td>
						</tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
//设置音频本地预览   
	function setVoicePreview() {  
	
		var docObj=document.getElementById("voiceFile");  
    	var VoiceObjPreview=document.getElementById("preview");  
    	var src;
    		//取消选择文件时清空上次预览
    	if('' == $("#voiceFile").val()){
    		$("#add_voiceViewURL").attr('src','');
    		return false;
    	}
    	// 文件格式校验
    	if( !docObj.value.match(/.mp3|.ogg|.wav/i)){
   			$.messager.alert('提示','音频格式无效！'); 
   			$("#add_voiceViewURL").attr('src','');
   			return false;  
  		}    	
    	 if(docObj.files && docObj.files[0]){
	    		if(docObj.files[0].size >  2*1024*1024){
	    			$.messager.alert("提示信息","请你上传小于2MB的音频!","info");
	    			$("#edit_voiceViewURL").attr('src','');
	    			return false;
	    		} 
	    		if( docObj.files[0].duration > 60){
	    		$.messager.alert("提示信息","请你上传播放时间小于60秒的音频!","info");
	    			$("#edit_voiceViewURL").attr('src','');
	    			return false;
	    		}
	    		src=window.URL.createObjectURL(docObj.files[0]); 
	    		$('#localvioce').empty(); 
	    		$('#localvioce').panel({
				content:'<audio id="edit_voiceViewURL" controls="controls" src="'+src+'" playing="mginfo();"></audio>'
			});   
    	 }else{
    			//IE10以下，使用滤镜 制作本地预览
        	docObj.select(); 
        	docObj.blur();
			src = document.selection.createRange().text;
			
    		//src=window.URL.createObjectURL(docObj.files[0]); 
    		$('#localvioce').empty(); 
    		
    			//IE10以下，使用滤镜 制作本地预览
        	docObj.select(); 
        	docObj.blur();
			src = document.selection.createRange().text;
			
    		//src=window.URL.createObjectURL(docObj.files[0]); 
    		$('#localvioce').empty(); 
    
    		$('#localvioce').html( '<object height=100 width=500 classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6>'
													+'<param name="URL" value="'+src+'" /> ' 
													+'<param name="currentPosition" value="0" />'
													+'<param name="playCount" value="1" />'
													+'<param name="autoStart" value="0" />'
													+'<param name="currentMarker" value="0" />'
			                                       +' </object>'
		                  ); 
	       }
   }


</script>