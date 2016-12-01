<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file="/jsp/common/easyui_head.jsp"%>
    <title>图文素材一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tWxNews_list" class="container">
    <header class="page-title">
        <h1>图文素材一览</h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>同步微信图文素材</a></li>
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
                                    <th class="wd-20"><label>标题</label></th>
                                    <td>
                                        <input type="text" id="tWxNewsDto.title" name="search_title"  value="${tWxNewsDto.title}" style="width:200px;" data-options="required:true" />
                                    </td>
                                    <th class="wd-20"><label>作者</label></th>
                                    <td>
                                        <input type="text" id="tWxNewsDto.author" name="search_author"  value="${tWxNewsDto.author}" style="width:200px;" data-options="required:true" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-20"><label>摘要</label></th>
                                    <td>
                                        <input type="text" id="tWxNewsDto.digest" name="search_digest"  value="${tWxNewsDto.digest}" style="width:200px;" data-options="required:true" />
                                    </td>
                                    <th class="wd-20"><label>更新时间</label></th>
                                    <td>
                                        <input type="text" id="tWxNewsDto.update_time" name="search_update_time"  value="${tWxNewsDto.update_time}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
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
                <table  id="dg_tWxNews" class="easyui-datagrid" title="图文素材一览" style="width:auto;height:300px"
                        data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTWxNewsAction!getListData.action',method:'post'">
                    <thead>
                    <tr>
                        <th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
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

<script>
    var tWxNews_list ={};
    jQuery(function($){
        //定义构造查询
        tWxNews_list.buildQueryParams=function(){
            $('div#div_tWxNews_list #dg_tWxNews').datagrid("options")
                    .queryParams = {
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


        //定义构造查询JSON
        tWxNews_list.buildJsonQueryParams = function(){
            var searchContent =	{
                //标准查询部分
                pageNumber:$('div#div_tWxNews_list #dg_tWxNews').datagrid('options').pageNumber,
                //页面查询框部分
                news_id :$("input[name='search_news_id']").val(),
                media_id :$("input[name='search_media_id']").val(),
                title :$("input[name='search_title']").val(),
                thumb_media_id :$("input[name='search_thumb_media_id']").val(),
                show_cover_pic :$("input[name='search_show_cover_pic']").val(),
                author :$("input[name='search_author']").val(),
                digest :$("input[name='search_digest']").val(),
                url :$("input[name='search_url']").val(),
                content_source_url :$("input[name='search_content_source_url']").val(),
                update_time :$("input[name='search_update_time']").val(),
            };
            var searchContentStr  =JSON.stringify(searchContent);
            //alert(searchContentStr);
            //传到到后台的URL 必须先编码化
            return encodeURI(searchContentStr);
        }


        //重新按照条件刷新查询内容
        $('div#div_tWxNews_list #search_btn').click(function(){
            tWxNews_list.buildQueryParams();
            $('div#div_tWxNews_list #dg_tWxNews').datagrid('load');
        });

        //重置查询条件并刷新内容
        $('div#div_tWxNews_list #clear_btn').click(function(){
            //清空查询条件
            $("input[name='search_news_id']").val("");
            $("input[name='search_media_id']").val("");
            $("input[name='search_title']").val("");
            $("input[name='search_thumb_media_id']").val("");
            $("input[name='search_show_cover_pic']").val("");
            $("input[name='search_author']").val("");
            $("input[name='search_digest']").val("");
            $("input[name='search_url']").val("");
            $("input[name='search_content_source_url']").val("");
            $("input[name='search_update_time']").val("");
            var form = $(searchForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("clear");
            form.find(":input[name]").not(":hidden").val("");
            tWxNews_list.buildQueryParams();
            $('div#div_tWxNews_list #dg_tWxNews').datagrid('load');
        });

        var intervalObject; //定时器
        //新增
        $('div#div_tWxNews_list #add_btn').click(function(){
            var add_form_id ='#addTWxNewsFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: '微信图文素材同步',
                width: 800,
                height: 500,
                href: 'addTWxNewsPage.action',
                modal: true,
                method: "POST",
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "开 始 同 步",
                    handler: function(e){
                        $('#addTWxNewsFrom').form({
                            url:'addTWxNewsAction.action',
                            onSubmit: function(){
                                $.messager.progress();
                                // do some check
                                // return false to prevent submit;
                            },
                            success:function(data){
                                //do some
                                console.log(data)
                                $.messager.progress('close');
                                var map = $.parseJSON(data);
                                if(map.syncprogress>-1||map.newscount>-1){
                                    $("#syncinfo").html("同步中....")
                                    $.messager.alert("提示信息","当前正在同步图文素材","info")
                                }
                                intervalObject = window.setInterval(tWxNews_list.syncprogress, 1000); // 启动计时器，1秒执行一次获取同步结果
                            }
                        });//
                        //validate and sbumit
                        if($(add_form_id).form("validate")==true){
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

        //同步进度
        tWxNews_list.syncprogress = function(){
            $.get("addTWxNewsAction!syncProgress.action?time=" + new Date().getTime(), {}, function (result) {
                //设置进度
                if(result.syncprogress==-1&&result.newscount==-1){
                    $("#syncinfo").html("同步完成....")
                    $("#syncprogress").html($("#newscount").html())
                    window.clearInterval(intervalObject); //停止计时器
                }else{
                    $("#syncinfo").html("同步中....")
                    $("#syncprogress").html(result.syncprogress);
                    $("#newscount").html(result.newscount);
                }
            },"json");
        }

        //更新
        tWxNews_list.updateFormSubmit = function(pkid){
            var edit_form_id ='#editTWxNewsFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: 'TWxNews',
                width: 800,
                height: 500,
                href: 'editTWxNewsPage.action',
                modal: true,
                method: "POST",
                params:{pkid:pkid},
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                        text: "删  除",
                        handler: function(e){
                            $('#delTWxNewsFrom').form({
                                url:'delTWxNewsAction.action',
                                onSubmit: function(){
                                    $.messager.progress();
                                    // do some check
                                    // return false to prevent submit;
                                },
                                success:function(data){
                                    //do some
                                    $.messager.progress('close');
                                    tWxNews_list.buildQueryParams();
                                    $('div#div_tWxNews_list #dg_tWxNews').datagrid('load');
                                    $('#dialog_holder').dialog('close');
                                }
                            });//
                            $('#delTWxNewsFrom').submit();
                        }
                    }
                    ,{
                        text: "取 消",
                        handler: function(e){
                            $(this).dialog("close");
                        }
                    }]
            });
        }

        //详细
        tWxNews_list.detailFormSubmit = function(pkid){
            $('<div id="dialog_holder"></div>').dialog({
                title: '详细TWxNews',
                width: 800,
                height: 500,
                href: 'detailTWxNewsPage.action',
                modal: true,
                method: "POST",
                params:{pkid:pkid},
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "提  交",
                    handler: function(e){
                        var ThisForm = $(this).find("form");
                    }
                },{
                    text: "关闭",
                    handler: function(e){
                        $(this).dialog("close");
                    }
                }]
            });
        }


    });

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>