<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_tMaterialVoice_list" class="container">

	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="selectVoiceForm" name="selectVoiceForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>音频名称</label></th>
										<td>
											<input type="text" id="search_voice_name" name="search_voice_name"  value="${tMaterialVoiceDto.voice_name}" style="width:120px;"/>
										</td>
										   <th class="wd-10"><label>是否缓存</label></th>
									  <td>
									   <ldui:select items="${cache_flagList}" class="easyui-combobox"  id="search_cache_flag" name="search_cache_flag" style="width:120px;"/>	
									  </td>	
										<th class="wd-15"><label>音频描述</label></th>
										<td>
											<input type="text" id="search_voice_desc" name="search_voice_desc"  value="${tMaterialVoiceDto.voice_desc}" style="width:150px;"/>
										</td>
									  </tr>
									 <tr>
									  	<th class="wd-10"><label>创建人:</label></th>
										<td>
											<input type="text" id="search_voice_created_user_cd"  class="easyui-validatebox" name="search_voice_created_user_cd" style="width:120px;"/>  
										</td>
										<th class="wd-10"><label>更新人:</label></th>
										<td>
											<input type="text" id="search_voice_updated_user_cd"  class="easyui-validatebox" name="search_voice_updated_user_cd" style="width:120px;"/>  
										</td>
									
										<th class="wd-15"><label>最后更新时间:</label></th>
										<td colspan="3">
											<input type="text" id="search_voice_updated_date_start"  class="easyui-datebox" name="search_voice_updated_date_start" validType="againFocus['#search_voice_updated_date_end']" style="width: 100px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
											<input type="text" id="search_voice_updated_date_end"  class="easyui-datebox" name="search_voice_updated_date_end" validType="minFirstDate['#search_voice_updated_date_start']" style="width: 100px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
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
				<table  id="dg_tMaterialVoice" class="easyui-datagrid" title="音频一览表" style="width:auto;height:335px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTMaterialVoiceAction!getListData.action',method:'post'">
					<thead>
						<tr>
						    <th data-options="field:'RADIO',width:60,align:'center'">选择</th>
								<th data-options="field:'VOICE_NAME',width:180,sortable:'true',align:'center'">音频名称</th>
								<th data-options="field:'VOICE_DESC',width:260,sortable:'true',align:'center'">音频描述</th>
								<th data-options="field:'VOICE_SIZE',width:80,sortable:'true',align:'center'">音频大小</th>
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
var tMaterialVoice_list ={};
         	//引用音频项
     tMaterialVoice_list.doSelect = function(mt_id,mt_name,mt_url){
          ${jsCallback}(mt_id,mt_name,mt_url);
    }
 
jQuery(function($){
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
	//定义构造查询
	tMaterialVoice_list.buildQueryParams=function(){
	     $('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid("options")
			.queryParams = {
					'tMaterialVoiceQueryBean.voice_name':$("input[name='search_voice_name']").val(),
					'tMaterialVoiceQueryBean.voice_desc':$("input[name='search_voice_desc']").val(),
				    'tMaterialVoiceQueryBean.updated_date_start':$("input[name='search_voice_updated_date_start']").val(),
					'tMaterialVoiceQueryBean.updated_date_end':$("input[name='search_voice_updated_date_end']").val(),
					'tMaterialVoiceQueryBean.created_user_cd':$("input[name='search_voice_created_user_cd']").val(),
					'tMaterialVoiceQueryBean.updated_user_cd':$("input[name='search_voice_updated_user_cd']").val(),
					'tMaterialVoiceQueryBean.cache_flag':$("input[name='search_cache_flag']").val()
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tMaterialVoice_list #search_btn').click(function(){
	   if($('#selectVoiceForm').form("validate")==true){ 
		tMaterialVoice_list.buildQueryParams();
		$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialVoice_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(selectVoiceForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialVoice_list.buildQueryParams();
		$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
	});

});
</script>
