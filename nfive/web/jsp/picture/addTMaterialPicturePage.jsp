<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
	.stRed{color: red}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTMaterialPictureFrom" name="addTMaterialPictureFrom" method="post" action="addTMaterialPictureAction.action" enctype="multipart/form-data">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>图片名称<span class="stRed">*</span>:</label></th>
							<td>
								<input type="text" maxlength="50" class="easyui-validatebox" id="tMaterialPictureDto.picture_name" name="tMaterialPictureDto.picture_name"  data-options="required:true,invalidMessage:'图片名称不能为空！',missingMessage:'图片名称不能为空！'" style="width:195px;"/>
							</td>
							<th class="wd-20"><label>是否缓存<span class="stRed">*</span>:</label></th>
							<td>
								<ldui:select items="${cache_FlagList}" id="add_effect_flag_select" name="tMaterialPictureDto.cache_flag" class="easyui-combobox" style="width:200px;" 
									required="true" validType="select['#add_effect_flag_select']" invalidMessage="请选择是否缓存！" missingMessage="请选择是否缓存！"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片规格（宽）:</label></th>
							<td>
								<input type="text" maxlength="3" id="tMaterialPictureDto.picture_wsize" name="tMaterialPictureDto.picture_wsize" value="" class="easyui-validatebox" style="width:195px;line-width:190px;" data-options="validType:['pureNumber','image_width']"/>
							</td>
							<th class="wd-20"><label>图片规格（高）:</label></th>
							<td>
								<input type="text" maxlength="3" id="tMaterialPictureDto.picture_hsize" name="tMaterialPictureDto.picture_hsize" value="" class="easyui-validatebox" style="width:195px;line-width:190px;" data-options="validType:['pureNumber','image_height']"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片描述:</label></th>
							<td colspan="3">
								<textarea   style="width:577px; max-width: 610px;max-height: 50px;height: 30px;" class="easyui-validatebox" id="tMaterialPictureDto.picture_desc" name="tMaterialPictureDto.picture_desc"  data-options="validType:'length[0,100]',invalidMessage:'图片描述不能超过100个字符！'"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 20px;height: 220px;">
							 	<s:file   theme="simple"  id="imgFile" name="imgFile" onchange="setImagePreview();" ></s:file>
							 	<label style="float: right;margin-right: 26px;">文件限制：格式只能为jpg/JPG，大小不超过1M</label>
							 	<div id="localImag" class="easyui-panel" style="width:706px;height: 180px;" >							 	
							 		<img alt="" src="" id="preview">
							 	</div>
							 </td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<!--搜索栏结束-->
</div>
<script type="text/javascript">

	//设置图片本地预览   
	function setImagePreview() {  
		
		var docObj=$("#imgFile")[0];  							//获取文件对象
    	var imgObjPreview=document.getElementById("preview");   //预览块
    	
    	//取消选择文件时清空上次预览
    	if(''	==	$("#imgFile").val()){
    		$("#localImag").html('');
    		return ;
    	}
    	
    	// 文件格式校验
    	if( !docObj.value.match( /.jpg|.JPG/i ) ){  
   			$.messager.alert('提示','图片格式无效！');  
   			return false;  
  		}    	
    	
    	var src;
    	if(docObj.files && docObj.files[0]){ 	
    		
    		if(docObj.files[0].size>1*1024*1024){					// 支持html5标签的文件大小校验
    			$.messager.alert("提示","图片大小不能超过1M！");
    			return ;
    		}   		
    		
    		src=window.URL.createObjectURL(docObj.files[0]); 		//支持html5标签的本地预览
    		$('#localImag').panel({
				content:'<img id="img" src="'+src+'"></img>'
			}); 
    	}else{  
    		
    		// IE10以下版本浏览器文件大小校验										
			var ImgObj=new Image();   //建立一个图像对象	
			ImgObj.src=$("#imgFile").val();
			ImgObj.onreadystatechange=function(){
				if(ImgObj.readyState=="complete")
				{
					var ImgFileSize=Math.round(ImgObj.fileSize/1024*100)/100;//取得图片文件的大小		
					if(ImgFileSize!=0){
						if(ImgFileSize>1*1024*1024){
							$.messager.alert("提示","图片文件大小不能超过1M！");
							return false;
						}
						if( !docObj.value.match( /.jpg|.JPG/i ) ){  
	   						$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
	   						return ;  
	  					}	
					}																																	}											
			}
    	
       		//IE10以下，使用滤镜 制作本地预览
        	docObj.select(); 
        	docObj.blur();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");  
        	  
        	//图片异常的捕捉，防止用户修改后缀来伪造图片  
        	try{  
           		localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
            	localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;             							
            	
        	}catch(e){  
           		alert("您上传的图片格式不正确，请重新选择!");  
            	return false;  
       		 }  
        	imgObjPreview.style.display = 'none';  
        	document.selection.empty();  
   	 	} 
   	 	
 	}
</script>
