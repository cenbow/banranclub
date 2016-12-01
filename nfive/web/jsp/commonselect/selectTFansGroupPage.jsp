<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_tFansGroup_list" class="container" style="min-height: 10px;min-width: 10px;">
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="selectFansGroupForm" name="selectFansGroupForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>群组名称</label></th>
										<td style="width:250px">
											<input type="text" id="select_group_name" name="search_group_name"  value="${tFansGroupDto.group_name}" style="width:200px;"/>
										</td>
										<th class="wd-15"><label>创建人</label></th>
										<td>
											<input type="text" id="select_created_user_cd" name="search_created_user_cd"  value="${tFansGroupDto.created_user_cd}" style="width:200px;"/>
										</td>
									  </tr>
									<tr>
									   	<th class="wd-10"><label>更新人</label></th>
										<td style="width:250px">
											<input type="text" id="select_updated_user_cd" name="search_updated_user_cd"  value="${tFansGroupDto.updated_user_cd}" style="width:200px;"/>
										</td>
										<th class="wd-15"><label>最后更新时间</label></th>
										<td colspan="2">
										<input type="text" class="easyui-datebox" id="select_pupdated_date_start"   name="search_pupdated_date_start" validType="againFocus['#select_pupdated_date_end']" style="width:110px;"  class="easyui-validatebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp; 
										<input type="text" class="easyui-datebox" id="select_pupdated_date_end"  name="search_pupdated_date_end" validType="minFirstDate['#select_pupdated_date_start']" style="width:110px;"  class="easyui-validatebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
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
				<table  id="dg_tFansGroup" class="easyui-datagrid" title="粉丝群一览" style="width:auto;height:335px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTFansGroupAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'RADIO',width:40,align:'center'">选择</th>
							<th data-options="field:'FANS_GROUP_ID',width:120,hidden:'true',align:'center'">fans_group_id</th>
							<th data-options="field:'GROUP_NAME',width:160,sortable:'true',align:'center'">群组名称</th>
							<th data-options="field:'REMARK',width:280,sortable:'true',align:'center'">群组说明</th>
							<th data-options="field:'CREATED_USER_CD',width:100,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:100,sortable:'true',align:'center'">更新人</th>
						   <th data-options="field:'UPDATED_DATE',width:140,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tFansGroup_list ={};

//引用粉丝群项
   tFansGroup_list.doSelect = function(fansgroup_id,group_name){
        //调用回调函数
        ${jsCallback}(fansgroup_id,group_name);
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
	tFansGroup_list.buildQueryParams=function(){
	     $('div#div_tFansGroup_list #dg_tFansGroup').datagrid("options")
			.queryParams = {
					'tFansGroupQueryBean.group_name':$("input[name='search_group_name']").val(),
					'tFansGroupQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val(),
					'tFansGroupQueryBean.updated_user_cd':$("input[name='search_updated_user_cd']").val(),
					'tFansGroupQueryBean.cxstartdate':$("input[name='search_pupdated_date_start']").val(),
					'tFansGroupQueryBean.cxenddate':$("input[name='search_pupdated_date_end']").val()
			};
	};


    //定义构造查询JSON
    tFansGroup_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('options').pageNumber,
		  		//页面查询框部分
					fans_group_id :$("input[name='search_fans_group_id']").val(), 
					group_name :$("input[name='search_group_name']").val(), 
					remark :$("input[name='search_remark']").val(), 
					updated_date :$("input[name='search_updated_date']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					updated_user_cd :$("input[name='search_updated_user_cd']").val(), 
					platform_id :$("input[name='search_platform_id']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tFansGroup_list #search_btn').click(function(){
	  if($('#selectFansGroupForm').form("validate")==true){ 
		tFansGroup_list.buildQueryParams();
		$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tFansGroup_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(selectFansGroupForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tFansGroup_list.buildQueryParams();
		$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
	});

});
</script>
