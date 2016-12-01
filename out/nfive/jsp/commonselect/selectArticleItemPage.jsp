<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ include file="/jsp/common/commonhead.jsp"%>

<!-- 图文项选择弹窗 -->
<div id="div_articleItemSelectPage" class="content-body">
    <!--搜索栏开始-->
    <div class="search-panel toggle-panel">
        <div class="panel-header" data-role="toggle-handle">
            <h2 class="panel-title">查询条件</h2>
        </div>
        <div class="search-panel-content">
            <form id="searchForm" name="searchForm" method="post" >
            <div class="search-panel-bd">
                <table class="search-table">
                    <tr>
                        <th class="wd-15"><label>标题</label></th>
                        <td>
                            <input type="text" id="search_title" name="search_title" class="easyui-validatebox" style="width:200px;"/>
                        </td>
                        <th class="wd-15"><label>发布状态</label></th>
                        <td>
                            <ldui:select items="${articleStates}" id="search_article_state" name="search_article_state" class="easyui-combobox" style="width:200px;" />
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-15"><label>项目类型</label></th>
                        <td>
                            <ldui:select items="${articleTypes}" id="search_article_type" name="search_article_type" class="easyui-combobox" style="width:200px;" />
                        </td>
                        <th class="wd-15"><label>创建人</label></th>
                        <td>
                            <input type="text" id="search_created_user_name" name="search_created_user_name" class="easyui-validatebox" style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-15"><label>更新人</label></th>
                        <td>
                        <input type="text" id="search_updated_user_name" name="search_updated_user_name" class="easyui-validatebox" style="width:200px;"/>
                        </td>
                        <th class="wd-15"><label>最后更新时间</label></th>
                        <td>
                        <input type="text" id="search_updated_date_start" name="search_updated_date_start"  style="width:120px;" class="easyui-datebox" validType="againFocus['#search_updated_date_end']" />
                        &nbsp;<a href="javascript:;" id="clear_updated_date_start" class="icon-clear-date"></a>
                        ~
                        <input type="text" id="search_updated_date_end" name="search_updated_date_end"  style="width:120px;" class="easyui-datebox" validType="minFirstDate['#search_updated_date_start']" />
                        &nbsp;<a href="javascript:;" id="clear_updated_date_end" class="icon-clear-date"></a>
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
        <table id="dg_articleItemSelect" title="查询结果" class="easyui-datagrid" style="width:auto;height:350px"
            data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:true,url:'<%=path%>/selectTArticleItemAction!getListData.action',method:'post'" >
            <thead>
                <tr>
                    <th data-options="field:'RADIO',width:50,align:'center'">选择</th>
                    <th data-options="field:'ARTICLE_ITEM_ID',width:120,sortable:'true',align:'right',hidden:'true'">ARTICLE_ITEM_ID</th>
                    <th data-options="field:'TITLE',width:170,sortable:'true',align:'left'">标题</th>
                    <th data-options="field:'ARTICLE_STATE',width:100,sortable:'true',align:'center'">发布状态</th>
                    <th data-options="field:'ARTICLE_TYPE',width:100,sortable:'true',align:'center'">项目类型</th>
                    <th data-options="field:'CREATED_USER_NAME',width:100,sortable:'true',align:'center'">创建人</th>
                    <th data-options="field:'UPDATED_USER_NAME',width:100,sortable:'true',align:'center'">更新人</th>
                    <th data-options="field:'UPDATED_DATE',width:140,sortable:'true',align:'center'">最后更新时间</th>
                </tr>
            </thead>
        </table>
    </div>
    <!--搜索栏结果end-->
</div>

<script type="text/javascript">
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
    var articleItemSelectPage = {};

    //去除前后空格
    articleItemSelectPage.trimAll = function() {
        $("input[type='text'], textarea").each(function(){
            $(this).val($.trim($(this).val()));
        });
    }

    //定义构造查询
    articleItemSelectPage.buildQueryParams = function() {
        $('div#div_articleItemSelectPage #dg_articleItemSelect').datagrid("options").queryParams = {
            'tArticleItemQueryBean.title':$("input[name='search_title']").val(),//标题
            'tArticleItemQueryBean.article_state':$("input[name='search_article_state']").val(),//发布状态
            'tArticleItemQueryBean.article_type':$("input[name='search_article_type']").val(),//项目类型
            'tArticleItemQueryBean.created_user_name':$("input[name='search_created_user_name']").val(),//创建人
            'tArticleItemQueryBean.updated_user_name':$("input[name='search_updated_user_name']").val(),//更新人
            'tArticleItemQueryBean.updated_date_start':$('#search_updated_date_start').datebox("getValue"),//最后更新时间
            'tArticleItemQueryBean.updated_date_end':$('#search_updated_date_end').datebox("getValue")//最后更新时间
        };
    };

    //定义选择方法
    articleItemSelectPage.doSelect = function(article_item_id) {
        //调用回调函数
        ${jsCallback}(article_item_id);
    };

    jQuery(function($){
        //查询
        $('div#div_articleItemSelectPage #search_btn').click(function() {

            articleItemSelectPage.trimAll();
            articleItemSelectPage.buildQueryParams();
            $('div#div_articleItemSelectPage #dg_articleItemSelect').datagrid('load');
        });

        //重置查询条件并刷新内容
        $('div#div_articleItemSelectPage #clear_btn').click(function(){
            var form = $(searchForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("reset");
            $("#search_updated_date_end").datebox("validate");
            form.find(":input[name]").not(":hidden").val("");

            articleItemSelectPage.buildQueryParams();
            $('div#div_articleItemSelectPage #dg_articleItemSelect').datagrid('load');
        });
    });
</script>