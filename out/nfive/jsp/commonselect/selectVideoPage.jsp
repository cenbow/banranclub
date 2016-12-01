<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_tMaterialVideo_list" class="container">
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="selectVideoForm" name="selectVideoForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>视频名称</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_name" name="search_video_name"  style="width:150px;"/>
										</td>
										<th class="wd-10"><label>是否缓存</label></th>
										<td>
											<ldui:select items="${cache_flag_Select}" id="search_is_cache_Select" name="search_is_cache" class="easyui-combobox" style="width:150px;" />
										</td>
										<th class="wd-15"><label>视频描述</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_video_desc" style="width:150px;" />
										</td>
									</tr>
									 <tr>
									 <th class="wd-10"><label>创建人</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_created_user_name"  style="width:150px;"/>
										</td>
									 <th class="wd-10"><label>更新人</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_updated_user_name" style="width:150px;"/>
										</td>
										<th class="wd-15"><label>最后更新时间</label></th>
										<td colspan="3">
											<input type="text" id="tMaterialVideoDtoCxStartdate" name="search_startdate" validType="againFocus['#tMaterialVideoDtoCxEnddate']" style="width:100px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="tMaterialVideoDtoCxEnddate" name="search_enddate" validType="minFirstDate['#tMaterialVideoDtoCxStartdate']" style="width:100px;" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
									  </tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" value="查 询" />
							<input id="clear_btn" type="button" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMaterialVideo" class="easyui-datagrid" title="视频资源一览列表" style="width:auto;height:335px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTMaterialVideoAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'VIDEO_ID',width:120,sortable:'true',align:'right',hidden:'true'">video_id</th>
						 	<th data-options="field:'RADIO',width:60,align:'center'">选择</th>
							<th data-options="field:'VIDEO_NAME',width:180,sortable:'true',align:'center'">视频名称</th>
							<th data-options="field:'VIDEO_DESC',width:260,sortable:'true',align:'center'">视频描述</th>
							<th data-options="field:'CONTENT_SIZE',width:80,sortable:'true',align:'center'">视频大小</th>
							<th data-options="field:'CACHE_FLAG',width:60,sortable:'true',align:'center'">是否缓存</th>
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
var tMaterialVideo_list ={};
    	//引用视频项
     tMaterialVideo_list.doSelect = function(mt_id,mt_name,mt_url){
          ${jsCallback}(mt_id,mt_name,mt_url);
    }
jQuery(function($){
	//定义清空日期
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
	//定义构造查询
	tMaterialVideo_list.buildQueryParams=function(){
	     $('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid("options")
			.queryParams = {
					'in_tMaterialVideoQueryBean.video_id':$("input[name='search_video_id']").val(),
					'in_tMaterialVideoQueryBean.video_name':$("input[name='search_video_name']").val(),
					'in_tMaterialVideoQueryBean.video_url':$("input[name='search_video_url']").val(),
					'in_tMaterialVideoQueryBean.video_desc':$("input[name='search_video_desc']").val(),
					'in_tMaterialVideoQueryBean.file_id':$("input[name='search_file_id']").val(),
					
					'in_tMaterialVideoQueryBean.cache_flag':$.trim($("#search_is_cache_Select").combobox('getValue')),//是否缓存					
					'in_tMaterialVideoQueryBean.cxstartdate':$('#tMaterialVideoDtoCxStartdate').datebox('getValue'),//开始时间
					'in_tMaterialVideoQueryBean.cxenddate':$('#tMaterialVideoDtoCxEnddate').datebox('getValue'),//结束时间
					'in_tMaterialVideoQueryBean.created_user_cd':$("input[name='search_created_user_name']").val(),//创建人
					'in_tMaterialVideoQueryBean.updated_user_cd':$("input[name='search_updated_user_name']").val(),//更新人
					'in_tMaterialVideoQueryBean.created_date':$("input[name='search_created_date']").val()//创建时间
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tMaterialVideo_list #search_btn').click(function(){
	  if($('#selectVideoForm').form("validate")==true){ 
		tMaterialVideo_list.buildQueryParams();
		$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialVideo_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(selectVideoForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialVideo_list.buildQueryParams();
		$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
	});

});

</script>
