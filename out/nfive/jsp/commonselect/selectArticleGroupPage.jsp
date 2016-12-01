<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>

<!--内容-->
<div id="div_tArticleGroup_list" class="container">
    <article id="content" class="content">
        <div class="content-body">
            <!--搜索栏开始-->
            <div class="search-panel toggle-panel">
                <div class="panel-header" data-role="toggle-handle">
                    <h2 class="panel-title">查询条件</h2>
                </div>
                <div class="search-panel-content">
                            <form id="selectArticleGroupForm" name="selectArticleGroupForm" method="post" >
                        <div class="search-panel-bd">
                            <table class="search-table">
                                <tr>
                                    <th class="wd-20"><label>图文组编号</label></th>
                                    <td>
                                        <input type="text" id="search_ag_code" name="search_ag_code" class="easyui-validatebox" style="width:120px;" />
                                    </td>
                                    <th class="wd-20"><label>图文组名称</label></th>
                                    <td>
                                        <input type="text" id="search_ag_name" name="search_ag_name" class="easyui-validatebox" style="width:120px;" />
                                    </td>
                                    <th class="wd-20"><label>图文组类型</label></th>
                                    <td>
                                        <ldui:select items="${articleTypes}" id="search_article_type" name="search_article_type" class="easyui-combobox" style="width:120px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-20"><label>启用标志</label></th>
                                    <td>
                                        <ldui:select items="${enableFlags}" id="search_enable_agflag" name="search_enable_agflag" class="easyui-combobox" style="width:120px;" />
                                    </td>
                                    <th class="wd-20"><label>图文组管理人</label></th>
                                    <td>
                                        <input type="text" id="search_ag_manager_name" name="search_ag_manager_name" class="easyui-validatebox" style="width:120px;" />
                                    </td>
                                    <th class="wd-20"><label>创建人</label></th>
                                    <td>
                                        <input type="text" id="search_created_user_name" name="search_created_user_name"  class="easyui-validatebox" style="width:120px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-20"><label>更新人</label></th>
                                    <td>
                                        <input type="text" id="search_updated_user_name" name="search_updated_user_name"  class="easyui-validatebox" style="width:120px;" />
                                    </td>
                                    <th class="wd-20"><label>最后更新时间</label></th>
                                    <td colspan="3">
                                        <input type="text" id="search_updated_date_start" name="search_updated_date_start" validType="againFocus['#search_updated_date_end']"  style="width:140px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~
                                        <input type="text" id="search_updated_date_end" name="search_updated_date_end"  validType="minFirstDate['#search_updated_date_start']"  style="width:140px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
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
                <table  id="dg_tArticleGroup" class="easyui-datagrid" title="查询结果" style="width:auto;height:335px"
                    data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTArticleGroupAction!getListData.action',method:'post'">
                    <thead>
                        <tr>
                           <th data-options="field:'RADIO',width:60,align:'center'">选择</th>
                            <th data-options="field:'ARTICLE_GROUP_ID',width:120,sortable:'true',align:'right',hidden:'true'">ARTICLE_GROUP_ID</th>
                            <th data-options="field:'AG_CODE',width:200,sortable:'true',align:'center'">图文组编号</th>
                            <th data-options="field:'AG_NAME',width:200,sortable:'true',align:'center'">图文组名称</th>
                            <th data-options="field:'ARTICLE_TYPE',width:110,sortable:'true',align:'center'">图文组类型</th>
                            <th data-options="field:'ENABLE_AGFLAG',width:100,sortable:'true',align:'center'">启用标志</th>
                            <th data-options="field:'AG_MANAGER_NAME',width:110,sortable:'true',align:'center'">图文组管理人</th>
                            <th data-options="field:'UPDATED_DATE',width:164,sortable:'true',align:'center'">最后更新时间</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <!--搜索栏结果end-->
        </div>
    </article>
</div>

<!--图文组详细-->
<form id="editTArticleGroupFrom" name="editTArticleGroupFrom" method="post" action="searchTArticleItemAction.action" >
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" />
</form>

<script>
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
    var tArticleGroup_list ={};

	//引用图文组
    tArticleGroup_list.doSelect = function(mt_id,mt_name,mt_url){
        //调用回调函数
        ${jsCallback}(mt_id,mt_name,mt_url);
    }
 
    
    jQuery(function($){

        //定义构造查询
        tArticleGroup_list.buildQueryParams = function() {

            $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid("options").queryParams = {
                'tArticleGroupQueryBean.ag_code':$("input[name='search_ag_code']").val(),//图文组编号
                'tArticleGroupQueryBean.ag_name':$("input[name='search_ag_name']").val(),//图文组名称
                'tArticleGroupQueryBean.ag_manager_name':$("input[name='search_ag_manager_name']").val(),//图文组管理人
                'tArticleGroupQueryBean.created_user_name':$("input[name='search_created_user_name']").val(),//创建人
                'tArticleGroupQueryBean.updated_user_name':$("input[name='search_updated_user_name']").val(),//更新人
                'tArticleGroupQueryBean.article_type':$('#search_article_type').combo("getValue"),//图文组类型
                'tArticleGroupQueryBean.enable_agflag':$('#search_enable_agflag').combo("getValue"),//启用标志
                'tArticleGroupQueryBean.updated_date_start':$('#search_updated_date_start').datebox("getValue"),//最后更新时间
                'tArticleGroupQueryBean.updated_date_end':$('#search_updated_date_end').datebox("getValue")//最后更新时间
            };
        };

        //查询
        $('div#div_tArticleGroup_list #search_btn').click(function() {
          if($('#selectArticleGroupForm').form("validate")==true){ 
            tArticleGroup_list.buildQueryParams();
            $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid('load');
            }
        });

        //重置查询条件并刷新内容
        $('div#div_tArticleGroup_list #clear_btn').click(function(){
            var form = $(selectArticleGroupForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("clear");
            form.find(":input[name]").not(":hidden").val("");

            tArticleGroup_list.buildQueryParams();
            $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid('load');
        });
});   
</script>
