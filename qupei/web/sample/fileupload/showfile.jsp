<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<script type="text/javascript" src="<%=jsPath%>/jquery/ajaxfileupload.js"></script>
<script type="text/javascript">

	 /**
	 *ajax图片上传 
	 */
	 function ajaxFileUpload() {
            $.ajaxFileUpload
            (
                {
                	
                    url: '/ld_wechart/ajaxFileUploadAction!execute.action?method=post', //用于文件上传的服务器端请求地址
                    type: 'post',
                    data: { pack: $("#pack").val(),imgFileFileName:$("#imgFileFileName").val() }, //此参数非常严谨，写错一个引号都不行
                    secureuri: false, //一般设置为false
                    fileElementId: 'imgFile', //文件上传空间的id属性  <input type="file" id="file" name="file" />
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数,data已经是json对象
                    {                   
                    	alert(data.msg);                  
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    { 
                    	alert(e);
                    }
                }
            )
            return false;
        }
        
 

</script>
</head>
<title>模板内容页</title>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>

<!--内容-->
<div id="cacheTest" class="container" style="margin-left: 200px;;">
		<form enctype="multipart/form-data" name="cacheTestFrom" method="post" ><br/>
				文件包名<input type="text" id="pack" name="pack"><br/><br/>
				文件<s:file name="imgFile" id="imgFile" ></s:file><br/><br/>
				
				<input type="button"  value="submit" onclick="ajaxFileUpload();"/><br/><br/>
				
			</form>
			
</div>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>