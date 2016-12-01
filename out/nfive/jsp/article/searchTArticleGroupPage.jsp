<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>图文组列表</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tArticleGroup_list" class="container">
    <header class="page-title">
        <h1>图文组列表</h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
        </ul>
    </div>
    <article id="content" class="content">
        <div class="content-body">
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
                                    <th style="width:13%;"><label>图文组编号</label></th>
                                    <td>
                                        <input type="text" id="search_ag_code" name="search_ag_code" class="easyui-validatebox" style="width:200px;" />
                                    </td>
                                    <th style="width:13%;"><label>图文组名称</label></th>
                                    <td>
                                        <input type="text" id="search_ag_name" name="search_ag_name" class="easyui-validatebox" style="width:200px;" />
                                    </td>
                                    <th style="width:13%;"><label>启用标志</label></th>
                                    <td>
                                        <ldui:select items="${enableFlags}" id="search_enable_agflag" name="search_enable_agflag" class="easyui-combobox" style="width:200px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:13%;"><label>图文组管理人</label></th>
                                    <td>
                                        <input type="text" id="search_ag_manager_name" name="search_ag_manager_name" class="easyui-validatebox" style="width:200px;" />
                                    </td>
                                    <th style="width:13%;"><label></label></th>
                                    <td>
                                    </td>
                                    <th style="width:13%;"><label></label></th>
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:13%;"><label>创建人</label></th>
                                    <td>
                                        <input type="text" id="search_created_user_name" name="search_created_user_name"  class="easyui-validatebox" style="width:200px;" />
                                    </td>
                                    <th style="width:13%;"><label>更新人</label></th>
                                    <td>
                                        <input type="text" id="search_updated_user_name" name="search_updated_user_name"  class="easyui-validatebox" style="width:200px;" />
                                    </td>
                                    <th style="width:13%;"><label>最后更新时间</label></th>
                                    <td>
                                        <input type="text" id="search_updated_date_start" name="search_updated_date_start"  style="width:100px;" class="easyui-datebox" validType="againFocus['#search_updated_date_end']" />
                                        &nbsp;<a href="javascript:;" id="clear_updated_date_start" class="icon-clear-date"></a>
                                        ~
                                        <input type="text" id="search_updated_date_end" name="search_updated_date_end"  style="width:100px;" class="easyui-datebox" validType="minFirstDate['#search_updated_date_start']" />
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
                <table id="dg_tArticleGroup" title="查询结果" style="width:auto;height:300px"
                    data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:true,url:'<%=path%>/searchTArticleGroupAction!getListData.action',method:'post'" >
                    <thead>
                        <tr>
                            <th data-options="field:'DETAIL',width:50,align:'center'">预览</th>
                            <th data-options="field:'EDIT',width:50,align:'center'">编辑</th>

                            <th data-options="field:'ARTICLE_GROUP_ID',width:120,sortable:'true',align:'right',hidden:'true'">ARTICLE_GROUP_ID</th>
                            <th data-options="field:'AG_CODE',width:200,sortable:'true',align:'center'">图文组编号</th>
                            <th data-options="field:'AG_NAME',width:250,sortable:'true',align:'left'">图文组名称</th>
                            <th data-options="field:'MAX_ITEMS_COUNT',width:100,sortable:'true',align:'center'">最大条目数</th>
                            <th data-options="field:'ENABLE_AGFLAG',width:100,sortable:'true',align:'center'">启用标志</th>
                            <th data-options="field:'AG_MANAGER_NAME',width:120,sortable:'true',align:'center'">图文组管理人</th>
                            <th data-options="field:'CREATED_USER_NAME',width:120,sortable:'true',align:'center'">创建人</th>
                            <th data-options="field:'UPDATED_USER_NAME',width:120,sortable:'true',align:'center'">更新人</th>
                            <th data-options="field:'UPDATED_DATE',width:140,sortable:'true',align:'center'">最后更新时间</th>
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
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" />
</form>

<!--预览图文组-->
<form id="previewFrom" name="previewFrom" method="post" action="previewArticleGroupPage.action" target="_blank" >
    <input type="hidden" id="articleGroupVo.article_type" name="articleGroupVo.article_type" />
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" />
</form>

<script type="text/javascript">
    var tArticleGroup_list = {
        //查询参数
        //使用内存对象而不是从画面取值，保证查询参数是用户执行了查询动作后的参数
        queryParam:{
            pageNumber: 1,
            ag_code:"",
            ag_name:"",
            ag_manager_name:"",
            created_user_name:"",
            updated_user_name:"",
            article_type:"",
            enable_agflag:"",
            updated_date_start:"",
            updated_date_end:""
        }
    };

    //生成查询参数
    //这种方法不能保证初始化时也能成功传值
    tArticleGroup_list.buildQueryParams = function() {
        $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid("options").queryParams = {
            'tArticleGroupQueryBean.ag_code':$("input[name='search_ag_code']").val(),//图文组编号
            'tArticleGroupQueryBean.ag_name':$("input[name='search_ag_name']").val(),//图文组名称
            'tArticleGroupQueryBean.ag_manager_name':$("input[name='search_ag_manager_name']").val(),//图文组管理人
            'tArticleGroupQueryBean.created_user_name':$("input[name='search_created_user_name']").val(),//创建人
            'tArticleGroupQueryBean.updated_user_name':$("input[name='search_updated_user_name']").val(),//更新人
            //'tArticleGroupQueryBean.article_type':$('#search_article_type').combobox("getValue"),//图文组类型
            'tArticleGroupQueryBean.enable_agflag':$('#search_enable_agflag').combobox("getValue"),//启用标志
            'tArticleGroupQueryBean.updated_date_start':$('#search_updated_date_start').datebox("getValue"),//最后更新时间
            'tArticleGroupQueryBean.updated_date_end':$('#search_updated_date_end').datebox("getValue")//最后更新时间
        };
        //保存查询参数
        tArticleGroup_list.queryParam.ag_code = $("input[name='search_ag_code']").val();
        tArticleGroup_list.queryParam.ag_name = $("input[name='search_ag_name']").val();
        tArticleGroup_list.queryParam.ag_manager_name = $("input[name='search_ag_manager_name']").val();
        tArticleGroup_list.queryParam.created_user_name = $("input[name='search_created_user_name']").val();
        tArticleGroup_list.queryParam.updated_user_name = $("input[name='search_updated_user_name']").val();
        //tArticleGroup_list.queryParam.article_type = $("#search_article_type").combobox("getValue");
        tArticleGroup_list.queryParam.enable_agflag = $("#search_enable_agflag").combobox("getValue");
        tArticleGroup_list.queryParam.updated_date_start = $('#search_updated_date_start').datebox("getValue");
        tArticleGroup_list.queryParam.updated_date_end = $('#search_updated_date_end').datebox("getValue");
    }

    //生成查询参数JSON串
    tArticleGroup_list.buildJsonQueryParams = function() {
        tArticleGroup_list.queryParam.pageNumber = $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid('options').pageNumber;
        var searchContentStr  =JSON.stringify(tArticleGroup_list.queryParam);
        //alert(searchContentStr);
        //传到到后台的URL 必须先编码化
        return encodeURI(searchContentStr);
    }

    //恢复查询参数
    tArticleGroup_list.resoreQueryParams = function() {
        var searchContent = '${tArticleGroupQueryBean.searchContent}';
        if ("" != searchContent) {
            searchContent = JSON.parse(decodeURI(searchContent));
            //画面控件
            $("input[name='search_ag_code']").val(searchContent.ag_code);//图文组编号
            $("input[name='search_ag_name']").val(searchContent.ag_name);//图文组名称
            $("input[name='search_ag_manager_name']").val(searchContent.ag_manager_name);//图文组管理人
            $("input[name='search_created_user_name']").val(searchContent.created_user_name);//创建人
            $("input[name='search_updated_user_name']").val(searchContent.updated_user_name);//更新人
            //$("#search_article_type").combobox("setValue", searchContent.article_type);//图文组类型
            $("#search_enable_agflag").combobox("setValue", searchContent.enable_agflag);//启用标志
            $('#search_updated_date_start').datebox("setValue", searchContent.updated_date_start);//最后更新时间
            $('#search_updated_date_end').datebox("setValue", searchContent.updated_date_end);//最后更新时间

            //参数对象
            tArticleGroup_list.queryParam.ag_code = searchContent.ag_code;
            tArticleGroup_list.queryParam.ag_name = searchContent.ag_name;
            tArticleGroup_list.queryParam.ag_manager_name = searchContent.ag_manager_name;
            tArticleGroup_list.queryParam.created_user_name = searchContent.created_user_name;
            tArticleGroup_list.queryParam.updated_user_name = searchContent.updated_user_name;
            tArticleGroup_list.queryParam.article_type = searchContent.article_type;
            tArticleGroup_list.queryParam.enable_agflag = searchContent.enable_agflag;
            tArticleGroup_list.queryParam.updated_date_start = searchContent.updated_date_start;
            tArticleGroup_list.queryParam.updated_date_end = searchContent.updated_date_end;
            tArticleGroup_list.queryParam.pageNumber = searchContent.pageNumber;//当前页数
        }
    }

    //编辑 图文组详细
    tArticleGroup_list.updateFormSubmit = function(pkid) {
        //保存查询参数
        $("input[name='tArticleGroupQueryBean.searchContent']").val(tArticleGroup_list.buildJsonQueryParams());
        //图文组ID
        $("input[name='tArticleGroupQueryBean.article_group_id']").val(pkid);

        $("#editTArticleGroupFrom").submit();
    }

    //预览 图文组
    tArticleGroup_list.previewFormSubmit = function(article_group_id, article_type) {
        $("input[name='articleGroupVo.article_type']").val(article_type);
        $("input[name='tArticleRelaQueryBean.article_group_id']").val(article_group_id);

        $("#previewFrom").submit();
    }

    //初始化datagrid
    tArticleGroup_list.iniDatagrid = function() {
        //console.debug(tArticleGroup_list.queryParam);
        $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid({
            queryParams: {
                'tArticleGroupQueryBean.ag_code': tArticleGroup_list.queryParam.ag_code,
                'tArticleGroupQueryBean.ag_name': tArticleGroup_list.queryParam.ag_name,
                'tArticleGroupQueryBean.ag_manager_name': tArticleGroup_list.queryParam.ag_manager_name,
                'tArticleGroupQueryBean.created_user_name': tArticleGroup_list.queryParam.created_user_name,
                'tArticleGroupQueryBean.updated_user_name': tArticleGroup_list.queryParam.updated_user_name,
                'tArticleGroupQueryBean.article_type': tArticleGroup_list.queryParam.article_type,
                'tArticleGroupQueryBean.enable_agflag': tArticleGroup_list.queryParam.enable_agflag,
                'tArticleGroupQueryBean.updated_date_start': tArticleGroup_list.queryParam.updated_date_start,
                'tArticleGroupQueryBean.updated_date_end': tArticleGroup_list.queryParam.updated_date_end
            },
            pageNumber: tArticleGroup_list.queryParam.pageNumber
        });
    }

    //去除前后空格
    tArticleGroup_list.trimAll = function() {
        $("input[type='text'], textarea").each(function(){
            $(this).val($.trim($(this).val()));
        });
    }

    jQuery(function($){

        //恢复查询参数
        tArticleGroup_list.resoreQueryParams();

        //初始化datagrid
        tArticleGroup_list.iniDatagrid();

        //清除日期
        $("#clear_updated_date_start").click(function(){
            $("#search_updated_date_start").datebox("clear");
            $("#search_updated_date_end").datebox("validate");
        });
        $("#clear_updated_date_end").click(function(){
            $("#search_updated_date_end").datebox("clear");
            $("#search_updated_date_end").datebox("validate");
        });

        //查询
        $('div#div_tArticleGroup_list #search_btn').click(function() {
            tArticleGroup_list.trimAll();
            tArticleGroup_list.buildQueryParams();
            $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid('load');
        });

        //清除 重置查询条件并刷新内容
        $('div#div_tArticleGroup_list #clear_btn').click(function(){
            var form = $(searchForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("clear");
            $("#search_updated_date_end").datebox("validate");
            form.find(":input[name]").not(":hidden").val("");
            tArticleGroup_list.buildQueryParams();
            $('div#div_tArticleGroup_list #dg_tArticleGroup').datagrid('load');
        });

        //新增
        $('div#div_tArticleGroup_list #add_btn').click(function(){
            var add_form_id ='#addTArticleGroupFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: '新增图文组',
                width: 800,
                height: 500,
                href: 'addTArticleGroupPage.action',
                modal: true,
                method: "POST",
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "保 存",
                    handler: function(e){
                        $('#addTArticleGroupFrom').form({
                            url:'addTArticleGroupAction.action',
                            onSubmit: function(){
                                $.messager.progress();
                                // do some check
                                // return false to prevent submit;
                            },
                            success:function(data){
                                //console.debug(data);
                                $.messager.progress('close');
                                var result = JSON.parse(data);
                                if (result.success) {
                                    tArticleGroup_list.updateFormSubmit(result.data);
                                    $('#dialog_holder').dialog('close');
                                } else {
                                    $.messager.alert('错误', result.message);
                                }
                            }
                        });
                        tArticleGroup_list.trimAll();
                        //validate and sbumit
                        if($(add_form_id).form("validate")){
                            $.messager.progress();
                            $(add_form_id).submit();
                        };
                    }
                },{
                    text: "取 消",
                    handler: function(e){
                        $(this).dialog("close");
                    }
                }]
            });
        });

    });
</script>

<%@ include file="/jsp/common/footer.jsp"%>

</body>
</html>