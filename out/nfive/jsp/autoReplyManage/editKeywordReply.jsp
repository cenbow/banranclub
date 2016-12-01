<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.stRed{color: red;}
.keyDiv{display: none;}
.borDiv{border: 1px solid blue;height: 300px;margin-top: 5px;}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addkeywordFrom" name="addkeywordFrom" method="post" action="">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20" height="30"><label>关键字<span class="stRed">*</span>：</label></th>
							<td>
								<input type="text" id="f1" name="f1" class="easyui-validatebox" data-options="required:true,invalidMessage:'不能为空',missingMessage:'不能为空'" style="width:200px;"/>
							</td>
						 
							<th class="wd-20" width="100" height="30"><label>匹配类型:</label></th>
							<td>
								<input type="text" class="easyui-combobox" id="f2" name="f2" data-options="
												textField:'label',
												valueField:'value',
												data:[{
													value:'未输入',
													label:'未输入'
												},{
													value:'完全匹配',
													label:'完全匹配'
												},{
													value:'模糊匹配',
													label:'模糊匹配'
												}],value:'未输入'
											"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20" height="30"><label>回复类型:</label></th>
							<td colspan="3">
								<input type="text" class="easyui-combobox" id="replyType" name="f3" data-options="
												textField:'label',
												valueField:'value',
												data:[{
													value:'0',
													label:'未输入'
												},{
													value:'1',
													label:'图文消息'
												},{
													value:'2',
													label:'文本消息'
												},{
													value:'3',
													label:'图片消息'
												},{
													value:'4',
													label:'音频消息'
												},{
													value:'5',
													label:'视频消息'
												}],value:'图文消息',onSelect:keySelect
											"/>
							</td>
						</tr>
				  </table>
				   <div style="margin-top: 5px;" id="divz">
						<!--链接页面 -->
<!--						<table id="div1"  class="search-table" style="display:block;">-->
<!--							<tr>-->
<!--								<th class="wd-20"><label>页面链接:</label></th>-->
<!--								<td >-->
<!--									<input type="text" class="easyui-validatebox" id="lf" name="lf" style="width:700px;"/>-->
<!--								</td>-->
<!--							</tr>-->
<!--						</table>-->
						<!-- 图文消息 -->
						<div  id="div1" class="keyDiv" style="display:block;">
							<table class="search-table">
								<tr>
									<th class="wd-20">图文组名称<span class="stRed">*</span><i class="icon-search" onclick="selectImgGroup()"></i></th>
									<td>
										<input type="text" value=""  style="width:400px;"  />
									</td>
								</tr>
							</table>
							<div class="borDiv">
								
							</div>
						</div>
						<!-- 文本消息 -->
						<div id="div2" class="keyDiv">
							<input type="checkbox" />动态模板
							<div class="borDiv">
								
							</div>
						</div>
						<!-- 图片消息 -->
						<div id="div3" class="keyDiv">
							<table class="search-table">
								<tr>
									<th class="wd-20">图片名称<span class="stRed">*</span><i class="icon-search" onclick="selectImages()"></i></th>
									<td>
										<input type="text" value=""  style="width:400px;"  />
									</td>
								</tr>
							</table>
							<div class="borDiv">
								
							</div>
						</div>
						<!-- 音频消息 -->
						<div id="div4" class="keyDiv">
							<div class="borDiv">
								5音频消息
							</div>
						</div>
						<!-- 视频消息 -->
						<div id="div5" class="keyDiv">
							<div class="borDiv">
								6视频消息
							</div>
						</div>
				  </div>
			</form>
		</div>
	</div>
</div>

<script>
		//回复类型效果切换
	function keySelect(){
		var va=$("#replyType").combobox("getValue");
		if(va==0){
			$("#divz").hide();
			return;
		}
		$("#divz").show();
		$("#div"+va).show().siblings().hide();
	}
	//弹出图文组选择页面
	function selectImgGroup(){
		$('<div id="horder_dialog"></div>').dialog({
			title:'图文级选择列表',
			width:950,
			height:600,
			href:'quoteImgGroupPage.action',
			onClose:function(){
				$(this).dialog("destroy");
			}
		});
	}
	//弹出图片选择页面
	function selectImages(){
		$('<div id="horder_dialog"></div>').dialog({
			title:'图片选择列表',
			width:950,
			height:600,
			href:'quoteImagesPage.action',
			onClose:function(){
				$(this).dialog("destroy");
			}
		});
	}
</script>