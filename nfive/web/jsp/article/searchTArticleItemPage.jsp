<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>图文组详细</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tArticleItem_list" class="container">
    <header class="page-title">
        <h1>图文组详细</h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li><a href="javascript:;" id="back_btn"><i class="icon-back"></i>返回</a></li>
            <li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增图文项</a></li>
            <li><a href="javascript:;" id="sel_btn"><i class="icon-filter"></i>引用图文项</a></li>
            <li><a href="javascript:;" id="edit_btn"><i class="icon-edit"></i>编辑图文组</a></li>
            <li><a href="javascript:;" id="preview_btn"><i class="icon-search"></i>预览图文组</a></li>
        </ul>
    </div>
    <article id="content" class="content">
        <div class="content-body">
            <!--图文组基本信息 START-->
            <div class="search-panel toggle-panel">
                <div class="panel-header" data-role="toggle-handle">
                    <h2 class="panel-title">图文组基本信息</h2>
                </div>
                <div class="search-panel-content">
                    <div class="search-panel-bd">
                        <table class="search-table">
                            <tr>
                                <th class="wd-10"><label>图文组编号</label></th>
                                <td class="wd-20">${outArticleGroupQueryBean.ag_code}</td>
                                <th class="wd-10"><label>图文组名称</label></th>
                                <td class="wd-20">${outArticleGroupQueryBean.ag_name}</td>
                                <th class="wd-10"><label>图文组管理人</label></th>
                                <td class="wd-20">${outArticleGroupQueryBean.ag_manager_name}</td>
                            </tr>
                            <tr>
                                <th class="wd-10"><label>启用标志</label></th>
                                <td>${outArticleGroupQueryBean.enable_agflag}</td>
                                <th class="wd-10"><label>最大条目数</label></th>
                                <td>${outArticleGroupQueryBean.max_items_count}</td>
                                <th class="wd-10"></th>
                                <td></td>
                            </tr>
                            <tr>
                                <th class="wd-10"><label>创建人</label></th>
                                <td>${outArticleGroupQueryBean.created_user_name}</td>
                                <th class="wd-10"><label>更新人</label></th>
                                <td>${outArticleGroupQueryBean.updated_user_name}</td>
                                <th class="wd-10"><label>最后更新时间</label></th>
                                <td><fmt:formatDate value="${outArticleGroupQueryBean.updated_date}" type="date"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <!--图文组基本信息 END-->

            <!--图文项 START-->
            <div class="result-content">
                <table  id="dg_tArticleItem" title="图文项列表" style="width:auto;height:300px"
                    data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:true,url:'<%=path%>/searchTArticleItemAction!getListData.action',method:'post'">
                    <thead>
                        <tr>
                            <th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
                            <th data-options="field:'EDIT',width:50,align:'center'">编辑</th>

                            <th data-options="field:'RELA_ID',width:120,align:'right',hidden:'true'">RELA_ID</th>
                            <th data-options="field:'ARTICLE_GROUP_ID',width:120,align:'right',hidden:'true'">ARTICLE_GROUP_ID</th>
                            <th data-options="field:'ARTICLE_ITEM_ID',width:120,align:'right',hidden:'true'">ARTICLE_ITEM_ID</th>

                            <th data-options="field:'TITLE',width:200,align:'left',sortable:'true'">标题</th>
                            <th data-options="field:'RELA_SORT',width:50,align:'center',sortable:'true'">权重</th>
                            <th data-options="field:'AUTHOR',width:120,align:'center',sortable:'true'">作者</th>
                            <th data-options="field:'ARTICLE_TYPE',width:120,align:'center',sortable:'true'">图文项类型</th>
                            <th data-options="field:'ARTICLE_STATE',width:100,align:'center',sortable:'true'">图文项状态</th>
                            <th data-options="field:'PUB_STARTDATE',width:100,align:'center',sortable:'true'">发布开始时间</th>
                            <th data-options="field:'PUB_ENDDATE',width:120,align:'center',sortable:'true'">发布结束时间</th>
                            <th data-options="field:'CREATED_USER_NAME',width:100,align:'center',sortable:'true'">创建人</th>
                            <th data-options="field:'UPDATED_USER_NAME',width:100,align:'center',sortable:'true'">更新人</th>
                            <th data-options="field:'UPDATED_DATE',width:130,align:'center',sortable:'true'">最后更新时间</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <!--图文项 END-->
        </div>
    </article>
</div>

<!--返回图文组列表-->
<form id="backFrom" name="backFrom" method="post" action="searchTArticleGroupAction.action" >
    <!--画面保持参数-->
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
</form>

<!--新增图文项-->
<form id="addTArticleItemFrom" name="addTArticleItemFrom" method="post" action="addTArticleItemPage.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
</form>

<!--编辑图文项-->
<form id="editTArticleItemFrom" name="editTArticleItemFrom" method="post" action="editTArticleItemPage.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文关系-->
    <input type="hidden" id="tArticleRelaQueryBean.rela_id" name="tArticleRelaQueryBean.rela_id" />
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" />
    <input type="hidden" id="tArticleRelaQueryBean.article_item_id" name="tArticleRelaQueryBean.article_item_id" />
</form>

<!--详细图文项-->
<form id="detailTArticleItemFrom" name="detailTArticleItemFrom" method="post" action="detailTArticleItemPage.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文关系-->
    <input type="hidden" id="tArticleRelaQueryBean.rela_id" name="tArticleRelaQueryBean.rela_id" />
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" />
    <input type="hidden" id="tArticleRelaQueryBean.article_item_id" name="tArticleRelaQueryBean.article_item_id" />
</form>

<!--引用图文项-->
<form id="refTArticleItemFrom" name="refTArticleItemFrom" method="post" action="refTArticleItemPage.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文关系-->
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleRelaQueryBean.article_item_id" name="tArticleRelaQueryBean.article_item_id" />
</form>

<!--刷新-->
<form id="refreshFrom" name="refreshFrom" method="post" action="searchTArticleItemAction.action" >
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
</form>

<!--预览图文组-->
<form id="previewFrom" name="previewFrom" method="post" action="previewArticleGroupPage.action" target="_blank" >
    <input type="hidden" id="articleGroupVo.article_type" name="articleGroupVo.article_type" value="${outArticleGroupQueryBean.article_type}" />
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
</form>

<script type="text/javascript">
    var tArticleItem_list ={};

    //编辑图文项
    tArticleItem_list.updateFormSubmit = function(rela_id, article_group_id, article_item_id){
        $("input[name='tArticleRelaQueryBean.rela_id']").val(rela_id);
        $("input[name='tArticleRelaQueryBean.article_group_id']").val(article_group_id);
        $("input[name='tArticleRelaQueryBean.article_item_id']").val(article_item_id);
        $('#editTArticleItemFrom').submit();
    }

    //详细图文项
    tArticleItem_list.detailFormSubmit = function(rela_id, article_group_id, article_item_id){
        $("input[name='tArticleRelaQueryBean.rela_id']").val(rela_id);
        $("input[name='tArticleRelaQueryBean.article_group_id']").val(article_group_id);
        $("input[name='tArticleRelaQueryBean.article_item_id']").val(article_item_id);
        $('#detailTArticleItemFrom').submit();
    }

    //引用图文项
    tArticleItem_list.selArticleItemCallback = function(article_item_id){
        //alert(article_item_id);
        $('#dialog_holder').dialog('close');
        $("input[name='tArticleRelaQueryBean.article_item_id']").val(article_item_id);
        $('#refTArticleItemFrom').submit();
    }

    //去除前后空格
    tArticleItem_list.trimAll = function() {
        $("input[type='text'], textarea").each(function(){
            $(this).val($.trim($(this).val()));
        });
    }

    jQuery(function($){

        //初始化数据列表
        $('div#div_tArticleItem_list #dg_tArticleItem').datagrid({
            queryParams:{'tArticleItemQueryBean.article_group_id':'${tArticleGroupQueryBean.article_group_id}'}
        });

        //新增 图文项
        $('div#div_tArticleItem_list #add_btn').click(function(){

            $("#addTArticleItemFrom").submit();
        });

        //返回 图文组列表
        $('div#div_tArticleItem_list #back_btn').click(function(){

            $("#backFrom").submit();
        });

        //编辑 图文组信息
        $('div#div_tArticleItem_list #edit_btn').click(function(){
            var edit_form_id ='#editTArticleGroupFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: '编辑图文组',
                width: 800,
                height: 500,
                href: 'editTArticleGroupPage.action',
                modal: true,
                method: "POST",
                params:{'inArticleGroupQueryBean.article_group_id':'${tArticleGroupQueryBean.article_group_id}'},
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "保  存",
                    handler: function(e){
                        $('#editTArticleGroupFrom').form({
                            url:'editTArticleGroupAction.action',
                            onSubmit: function(){
                                $.messager.progress();
                                // do some check
                                // return false to prevent submit;
                            },
                            success:function(data){
                                //do some
                                $.messager.progress('close');
                                var result = JSON.parse(data);
                                if (result.success) {
                                    //刷新本画面
                                    $('#refreshFrom').submit();
                                    $('#dialog_holder').dialog('close');
                                } else {
                                    $.messager.alert('错误', result.message);
                                }
                            }
                        });
                        tArticleItem_list.trimAll();
                        //validate and sbumit
                        if($(edit_form_id).form("validate")){
                            $.messager.progress();
                            $(edit_form_id).submit();
                        };
                    }
                },{
                    text: "删  除",
                    handler: function(e){
                        $.messager.confirm('确认', '确认删除当前图文组吗？<br/><br/>提示：删除图文组并不会删除关联的图文项', function(r){
                            if (r){//OK
                                $('#delTArticleGroupFrom').form({
                                        url:'delTArticleGroupAction.action',
                                        onSubmit: function(){
                                            // do some check
                                            // return false to prevent submit;
                                        },
                                        success:function(data){
                                            //do some
                                            $.messager.progress('close');
                                            var result = JSON.parse(data);
                                            if (result.success) {
                                                //跳转到图文组列表画面
                                                $("#backFrom").submit();
                                                $('#dialog_holder').dialog('close');
                                            } else {
                                                $.messager.alert('错误', result.message);
                                            }
                                        }
                                });
                                $('#delTArticleGroupFrom').submit();
                            }
                        });
                    }
                },{
                    text: "取 消",
                    handler: function(e){
                        $(this).dialog("close");
                    }
                }]
            });
        });

        //选择图文项
        $('div#div_tArticleItem_list #sel_btn').click(function(){
            $('<div id="dialog_holder"></div>').dialog({
                title: '引用图文项',
                width: 850,
                height: 600,
                href: 'selectTArticleItemAction.action',
                modal: true,
                method: "POST",
                params:{'jsCallback':'tArticleItem_list.selArticleItemCallback'},//页面回调
                onClose: function(){
                    $(this).dialog("destroy");
                }
            });
        });

        //预览图文组
        $('div#div_tArticleItem_list #preview_btn').click(function(){

            $("#previewFrom").submit();
        });
    });

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>