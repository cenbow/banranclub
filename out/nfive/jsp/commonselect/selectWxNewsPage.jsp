<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
    String path = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath();
%>

<!--内容-->
<div id="div_tWxNews_list" class="container">
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
                                    <th class="wd-20"><label>标题</label></th>
                                    <td>
                                        <input type="text" id="tWxNewsDto.title" name="search_title"  value="${tWxNewsDto.title}" style="width:200px;" data-options="required:true" />
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
                <table  id="dg_tWxNews" class="easyui-datagrid" title="查询结果" style="width:auto;height:335px"
                        data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTWxNewsAction!getListData.action',method:'post'">
                    <thead>
                    <tr>
                        <th data-options="field:'RADIO',width:60,align:'center'">选择</th>
                        <th data-options="field:'MEDIA_ID',width:120,sortable:'true',align:'right'">media_id</th>
                        <%--<th data-options="field:'THUMB_URL',width:120,sortable:'true',align:'right'">封面</th>--%>
                        <th data-options="field:'TITLE',width:300,sortable:'true',align:'right'">标题</th>
                        <th data-options="field:'AUTHOR',width:100,sortable:'true',align:'right'">作者</th>
                        <th data-options="field:'DIGEST',width:300,sortable:'true',align:'right'">摘要</th>
                        <th data-options="field:'URL',width:100,sortable:'true',align:'right'">图文链接</th>
                        <th data-options="field:'CHILD_NEWS',width:100,sortable:'true',align:'right'">子图文素材数</th>
                        <th data-options="field:'UPDATE_TIME',width:120,sortable:'true',align:'right'">最新更新时间</th>
                        <th data-options="field:'UPDATE_BATCH',width:150,sortable:'true',align:'right'">最新更新批次</th>
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
    var tWxNews_list ={};



    jQuery(function($){
        //引用图文组
        tWxNews_list.doSelect = function(mt_id,mt_name,mt_url){
            //调用回调函数
            console.log(${jsCallback});
            ${jsCallback}(mt_id,mt_name,mt_url);
        }


        //定义构造查询
        tWxNews_list.buildQueryParams = function() {

            $('div#div_tWxNews_list #dg_tWxNews').datagrid("options").queryParams = {
                'tWxNewsQueryBean.news_id':$("input[name='search_news_id']").val(),
                'tWxNewsQueryBean.media_id':$("input[name='search_media_id']").val(),
                'tWxNewsQueryBean.title':$("input[name='search_title']").val(),
                'tWxNewsQueryBean.thumb_media_id':$("input[name='search_thumb_media_id']").val(),
                'tWxNewsQueryBean.show_cover_pic':$("input[name='search_show_cover_pic']").val(),
                'tWxNewsQueryBean.author':$("input[name='search_author']").val(),
                'tWxNewsQueryBean.digest':$("input[name='search_digest']").val(),
                'tWxNewsQueryBean.url':$("input[name='search_url']").val(),
                'tWxNewsQueryBean.content_source_url':$("input[name='search_content_source_url']").val(),
                'tWxNewsQueryBean.update_time':$("input[name='search_update_time']").val(),
            };
        };

        //查询
        $('div#div_tWxNews_list #search_btn').click(function() {
            tWxNews_list.buildQueryParams();
            $('div#div_tWxNews_list #dg_tWxNews').datagrid('load');
        });

        //重置查询条件并刷新内容
        $('div#div_tWxNews_list #clear_btn').click(function(){
            var form = $(selectArticleGroupForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("clear");
            form.find(":input[name]").not(":hidden").val("");

            tWxNews_list.buildQueryParams();
            $('div#div_tWxNews_list #dg_tWxNews').datagrid('load');
        });
    });
</script>
