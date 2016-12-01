<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>

<!--内容-->
<div id="div_tMaterialPicture_list" class="container minmenu">	
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="selectPictureForm" name="selectPictureForm" method="post" >
					<input type="hidden" id="search_picture_id"  name="search_picture_id"  value="${tMaterialPictureQueryBean.picture_id}"/>
						<div class="search-panel-bd">
							<table class="search-table">	
									<tr>
									<th class="wd-10"><label>图片名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_picture_name"  name="search_picture_name" style="width:120px;"/>
									</td>
									<th class="wd-10"><label>是否缓存:</label></th>
									<td>
										<ldui:select items="${cache_FlagList}" id="search_effect_flag_select" name="search_effect_flag_select" class="easyui-combobox" style="width:120px;" />
									</td>
									<th class="wd-15"><label>图片描述:</label></th>
									<td >
										<input type="text" class="easyui-validatebox" id="search_picture_desc"  name="search_picture_desc" style="width:150px;"/>
									</td>
								</tr>
								<tr>
								   <th class="wd-10"><label>创建人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_created_user_cd" name="search_created_user_name"  style="width:120px;"/>
									</td>
									<th class="wd-10"><label>更新人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_updated_user_cd"  name="search_updated_user_name" style="width:120px;"/>
									</td>
									<th class="wd-15"><label>最后更新时间:</label></th>
									<td colspan="3">
										<input type="text" class="easyui-datebox" id="search_pupdated_date_start"   name="search_pupdated_date_start" validType="againFocus['#search_pupdated_date_end']" style="width:100px;"  class="easyui-validatebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp; 
										<input type="text" class="easyui-datebox" id="search_pupdated_date_end"  name="search_pupdated_date_end" validType="minFirstDate['#search_pupdated_date_start']" style="width:100px;"  class="easyui-validatebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
									</td>
								</tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" class="input-btn-small" value="查 询" />
							<input id="clear_btn" type="button" class="input-btn-small" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMaterialPicture" class="easyui-datagrid" title="图片一览表" style="width:auto;height:335px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=pathUrl%>/selectTMaterialPictureAction!getListData.action',method:'post'">
					<thead>
						<tr>  
						    <th data-options="field:'RADIO',width:60,align:'center'">选择</th>
						    <th data-options="field:'PICTURE_NAME',width:180,sortable:'true',align:'center'">图片名称</th>
							<th data-options="field:'PICTURE_DESC',width:260,sortable:'true',align:'center'">图片描述</th>
							<th data-options="field:'PICTURE_SIZE',width:80,sortable:'true',align:'center'">图片大小</th>
							<th data-options="field:'CACHE_FLAG',width:60,align:'center'">是否缓存</th>							
							<th data-options="field:'CREATED_USER_CD',width:80,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:80,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'center'">最后更新时间</th>										
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tMaterialPicture_list ={};

	//引用图片项
    tMaterialPicture_list.doSelect = function(mt_id,mt_name,mt_url){
        //调用回调函数
        ${jsCallback}(mt_id,mt_name,mt_url);
    }
//清除日期
  $(".icon-clear-date").mousedown(function(){
		var id1=$(this).prev().prev().attr("id");
	   $("#"+id1).datebox("clear");
       var id2=$(this).next().attr("id");
       if(id2 != undefined){
	       $("#"+id2).datebox("validate");
       }
       if(id2 == undefined){
          $("#"+id1).datebox("validate");
       }
		
	});
jQuery(function($){
	//定义构造查询
	tMaterialPicture_list.buildQueryParams=function(){
	     $('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid("options")
			.queryParams = {
			        'tMaterialPictureQueryBean.picture_id':$("input[name='search_picture_id']").val(),
					'tMaterialPictureQueryBean.picture_name':$("input[name='search_picture_name']").val(),
					'tMaterialPictureQueryBean.picture_desc':$("input[name='search_picture_desc']").val(),
					'tMaterialPictureQueryBean.search_updated_date':$("input[name='search_pupdated_date_start']").val(),
					'tMaterialPictureQueryBean.search_updated_date1':$("input[name='search_pupdated_date_end']").val(),
					'tMaterialPictureQueryBean.created_user_cd':$("input[name='search_created_user_name']").val(),
					'tMaterialPictureQueryBean.updated_user_cd':$("input[name='search_updated_user_name']").val(),
			        'tMaterialPictureQueryBean.cache_flag':$("#search_effect_flag_select").combobox("getValue")
			};
	}


	//重新按照条件刷新查询内容
	$('div#div_tMaterialPicture_list #search_btn').click(function(){
	      if($('#selectPictureForm').form("validate")==true){ 
		tMaterialPicture_list.buildQueryParams();
		$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
	    }
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialPicture_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(selectPictureForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialPicture_list.buildQueryParams();
		$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
	});
});
