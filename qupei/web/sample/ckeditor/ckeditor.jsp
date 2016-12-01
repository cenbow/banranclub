<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/jsp/common/easyui_head.jsp"%>
		<script type="text/javascript" src="<%=jsPath%>/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=jsPath%>/ckeditor/adapters/jquery.js"></script>
	</head>
	<body>
		<!--菜单-->
		<%@ include file="/jsp/common/topmenu.jsp"%>
		<form>

			<table border="2">
				<tr>
					<td colspan="2">
						手机版 	<input id="btn_get1" type="button" value="Get(HTML)">	
						        <input id="btn_set1" type="button" value="Set(HTML)">
						        <input id="btn_readonly1" type="button" value="ReadOnly">
					</td>
				</tr>
				<tr>
					<td width="200">
						手机版  
					
					</td>
					<td>
						<textarea id="editor1" cols="20" rows="2" /></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						文本版      <input id="btn_get2" type="button" value="Get(HTML)">	
						        <input id="btn_set2" type="button" value="Set(HTML)">
						        <input id="btn_readonly2" type="button" value="ReadOnly">
					</td>
				</tr>
				<tr>
					<td  width="200">
						文本版
					</td>
					<td>
						<textarea id="editor2" cols="20" rows="2" /></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						原生版   <input id="btn_get2" type="button" value="Get(HTML)">	
						        <input id="btn_set3" type="button" value="Set(HTML)">
						        <input id="btn_readonly3" type="button" value="ReadOnly">
					</td>
				</tr>
				<tr>
					<td  width="200">
						原生版
					</td>
					<td>
						<textarea id="editor3" cols="20" rows="2" /></textarea>
					</td>
				</tr> 
				<tr>
					<td  width="200">
						原生版 	
					</td>
				</tr> 
				<tr>
					<td  width="200">
						HTML:设置编辑器只读模式
					</td>
					<td>
						<textarea id="editor4" cols="20" rows="2" disabled="disabled"/></textarea>
					</td>
				</tr> 
				<tr>
					<td  width="200">
						JS:设置编辑器只读模式
					</td>
					<td>
						<textarea id="editor5" cols="20" rows="2"/></textarea>
					</td>
				</tr> 
			</table>
		</form>

<script type="text/javascript">		
	$(document).ready(function() {
	


	//设置CKEDITOR属性1      配置文件方式
	var  ck1 = CKEDITOR.replace('editor1', {width:320,height:568,customConfig : 'custom/mobile_html_cfg.js'});
	
    //设置CKEDITOR属性2  默认
	CKEDITOR.replace('editor2',{width:450,height:200,customConfig : 'custom/mobile_txt_cfg.js'});

    //设置CKEDITOR属性3 JS配置 方原生态配置
	CKEDITOR.replace('editor3',{customConfig : 'config.js',
										removePlugins : 'bidi,font,forms,flash,horizontalrule,iframe,justify,table,tabletools,smiley',
										removeButtons : 'Anchor,Underline,Strike,Subscript,Superscript,Image',
										format_tags : 'p;h1;h2;h3;pre;address'
									});
	//ok	
	
	//将文本编辑器模式设置为只读（初始化情况下）   可用(不推荐使用)  此方法会将页面内所有的编辑器控件设置为只读	（4，5，6）						
	//设置CKEDITOR属性4  只读模式（HTML）
	CKEDITOR.replace('editor4',{width:450,height:200,customConfig : 'custom/mobile_txt_cfg.js'});
	
	
	 //设置CKEDITOR属性5  只读模式（JS）
	$("#editor5").attr("disabled",true)
    CKEDITOR.replace('editor5',{width:450,height:200,customConfig : 'custom/mobile_txt_cfg.js'});
    
	
	
	//设置CKEDITOR属性5  只读模式（原生）
	/***
	CKEDITOR.on('instanceReady', function (ck1) {
              editor = ck1.editor;
              editor.setReadOnly(true); 
    });
    **/
    
    //设置只读模式   错误代码不可用（初始化情况下）
    //CKEDITOR.instances.editor1.setReadOnly(true)	
	
	//editor1
	$('#btn_get1').click(function(){
		var editor = CKEDITOR.instances.editor1;
		alert(editor.getData());	
	

	}
	);
	
	$('#btn_set1').click(function(){
				 var editor = CKEDITOR.instances.editor1;
		 var value = "hello world";
			 editor.setData( value); 
	});
	
	$('#btn_readonly1').click(function(){
				 var editor = CKEDITOR.instances.editor1;
	             editor.setReadOnly(true);
	});

	
	//editor2
	$('#btn_get2').click(function(){
		var editor = CKEDITOR.instances.editor2;
		alert(editor.getData());	
	}
	);
	
	$('#btn_set2').click(function(){
				 var editor = CKEDITOR.instances.editor2;
		 var value = "hello world";
			 editor.setData( value); 
	});
	
	
		$('#btn_readonly2').click(function(){
				 var editor = CKEDITOR.instances.editor2;
	             editor.setReadOnly(true);
	});
	
	//editor3
	$('#btn_get3').click(function(){
		var editor = CKEDITOR.instances.editor3;
		alert(editor.getData());	
	}
	);
	
	$('#btn_set3').click(function(){
				 var editor = CKEDITOR.instances.editor3;
		 var value = "hello world";
			 editor.setData( value); 
	});
	
		$('#btn_readonly3').click(function(){
				 var editor = CKEDITOR.instances.editor3;
	             editor.setReadOnly(true);
	});
							
	
  });
		
</script>
</body>
</html>
