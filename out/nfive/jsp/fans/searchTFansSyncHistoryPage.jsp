<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>粉丝数据同步</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tFansSyncHistory_list" class="container">
	<header class="page-title">
		<h1>粉丝数据同步</h1>
	</header>
	<article id="content" class="content">
		<div class="content-body">
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tFansSyncHistory" class="easyui-datagrid" title="同步履历" style="width:auto;height:334px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'SYNC_START_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTFansSyncHistoryAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'HISTORY_ID',width:120,hidden:'true',align:'center'">history_id</th>
							<th data-options="field:'SYNC_STATE',width:160,sortable:'true',align:'center'">同步状态</th>
							<th data-options="field:'SYNC_START_DATE',width:160,sortable:'true',align:'center'">同步开始时间</th>
							<th data-options="field:'SYNC_END_DATE',width:160,sortable:'true',align:'center'">同步结束时间</th>
							<th data-options="field:'USER_NAME',width:160,sortable:'true',align:'center'">同步执行人</th>
							<th data-options="field:'REMARK',width:650,sortable:'true',align:'center'">备注说明</th>
						</tr>
					</thead>
				</table>
			</div>
			<div style="padding:10px 0;text-align: center;">		
			   <p>同步履历结果每隔一分钟自动刷新一次,你也可以按F5进行手动刷新</p>		
			</div>
			<div style="padding:20px 0;text-align: center;">
		     	<input type="hidden" id="syncHistory_flag" value="${sync_flag}">			
			    <a id="syncbutton" class="easyui-linkbutton" style="width:100px" onclick="tFansSyncHistory_list.sync();">执行同步</a>			
			</div>
			
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tFansSyncHistory_list ={};
 	$(function(){  
	 	if($("#syncHistory_flag").val()=='506500000001'){
	       $('#syncbutton').linkbutton('disable');
		}   
 	  });

function  synchistory(){
       var trobj = document.getElementById("datagrid-row-r1-2-0");
       if(trobj != null){
        var tbobj = trobj.childNodes[1];
        if($.trim(tbobj.innerText) == "同步中" || tbobj.textContent == "同步中"){
          $('#syncbutton').linkbutton('disable');
        }else{
	      $('#syncbutton').linkbutton('enable');
	    }
      }
    }
    function snycfans(){
       $('div#div_tFansSyncHistory_list #dg_tFansSyncHistory').datagrid('load');
       setTimeout("synchistory()",100);
    }
tFansSyncHistory_list.sync=function(){
      $('#syncbutton').linkbutton('disable');
      $.post("addTFansSyncHistoryAction.action",function(data){   
             snycfans();     
          setInterval("snycfans()",1000*60*1);   
      });  
 }
tFansSyncHistory_list.detailRemark=function(remark){
	$.messager.alert('备注','<div style="word-wrap: break-word; word-break: normal;text-indent: 2em; ">'+remark+'</div>');
}
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>